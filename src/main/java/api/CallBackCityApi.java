package api;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import tool.Coordinate;
import tool.LocationConvert;

import java.util.*;

/**
 * Created by Mi on 16/1/12.
 */
public class CallBackCityApi {
//    static LinkedList<String> keys =  new LinkedList<String>();
//    static {
//        keys.add("98e241d687a3ebf02ac2af0a8bbee272");
//
//        keys.add("297acef28cce6c832242ffda80c13f8b");
//
//        keys.add("000a4347e075614fb2501f5930b7124e");
//
//        keys.add("685900fcfc9a689a6a6dc7444d513ff1");
//
//        keys.add("37c3b7fed2f0d2b7fbcc42a07c2a0604");
//        keys.add("df3cf793df1bd2c257483c11e7176923");
//
//        keys.add("f8f3fd85e51b484bd18de839f1082429");
//        keys.add("43465b9cd5ff31de9c4f5a47e981b6fd");
//        keys.add("06a34962107188c622392ceeb27a6849");
//        keys.add("fea61f633aef5e9af931e50d2ea12e83");
//        keys.add("d2c05024133dc31177881ee197b5ed8e");
//        keys.add("874cfa06550374027ce5945efb1bb1e8");
//        keys.add("1556b081ffa67208fb83605ec9b0efd0");
//        keys.add("d999626cc4c6a88398df4ca639310eab");
//        keys.add("8ead0e1d5e17a7280ea2bfe59acf5f15");
//        keys.add("12d675509592022d9ee8f26ca583740d");
//        keys.add("44a24b5e9f5ff7ad5ddc05c05038309a");
//        keys.add("a382a5fa86e38ccd2ee8d7e947834d3d");
//        keys.add("c29444d43423c9702864f6c38d29b71f");
//        keys.add("3b4eff7eebd0b66eb82af56553db865c");
//
//        keys.add("2a5454ea62d0641cc09675a9a276d693");
//        keys.add("043780f0250f8c507061cfdfcf47cdec");
//        keys.add("876632264b93a53559c5eb23a815e492");
//        keys.add("83a19a22b96e591d17052d4df44c3ac3");
//    }

    private static String key = "37c3b7fed2f0d2b7fbcc42a07c2a0604";
//    private static String geocodeApiUrl = "http://restapi.amap.com/v3/geocode/regeo?key=297acef28cce6c832242ffda80c13f8b&location=";
//    private static String searchApiUrl = "http://restapi.amap.com/v3/place/text?key=297acef28cce6c832242ffda80c13f8b&extensions=all&keywords=";


    public CallBackCityApi() {
    }

    public static ApiResult getCallBackCity(String longitude,String latitude) {
        Coordinate coordinate= LocationConvert.wgsTogcj(new Coordinate(Double.valueOf(latitude),Double.valueOf(longitude)));
        //初始化
        String status="0";
        String infoCode="0";
        String cityName="";
        String cityCode="";

        Map<String,Object>  regeocode = CallBackCityApi.getRegeCode(getGeocodeApiUrl() , longitude,latitude);

        if (null == regeocode) {
            regeocode = CallBackCityApi.getRegeCode(getGeocodeApiUrl() , longitude,latitude);
        }

        if (null == regeocode) {
            regeocode = CallBackCityApi.getRegeCode(getGeocodeApiUrl() , longitude,latitude);
        }

        Map<String,Object> addressComponent = (Map<String,Object> )regeocode.get("addressComponent");
        Object city = addressComponent.get("city");
        Object province = addressComponent.get("province");
        //直辖市处理
        if (city instanceof String){
            cityName = (String) city;
        }else{
            if (province instanceof String ){
                cityName = (String) province;
            }
        }
        //无城市名称
        if (cityName.length()==0){
            status = "0";
        }else {

            List<Object> poiList = getPoiList(searchApiUrl(),cityName);
            if (null == poiList) {
                poiList = getPoiList(searchApiUrl(),cityName);
            }

            if (null == poiList) {
                poiList = getPoiList(searchApiUrl(),cityName);
            }


            if (poiList.size() > 0) {
                Map<String, String> poi = (Map<String, String>) poiList.get(0);
                cityCode = poi.get("pcode");
            }
            //无poi数据
            else {
                status = "0";
            }
        }
        JSONObject cityJsonResult=new JSONObject();
        cityJsonResult.put("citycode",cityCode);
        cityJsonResult.put("cityname",cityName);
        cityJsonResult.put("status",status);
        cityJsonResult.put("statuscode",infoCode);
        cityJsonResult.put("latitude",coordinate.latitude);
        cityJsonResult.put("longitude",coordinate.longitude);
        return new ApiResult(cityJsonResult.toString());
    }

    public static Map<String,Object> getRegeCode (String url , String longitude,String latitude) {
        Coordinate coordinate= LocationConvert.wgsTogcj(new Coordinate(Double.valueOf(latitude),Double.valueOf(longitude)));
        //逆地理位置编码
        String geocodeStringResult = HttpUtils.get(url + coordinate.longitude + "," +coordinate.latitude);
        ApiResult geocodeJsonResult= new ApiResult(geocodeStringResult);
//        //数据处理
//        status = geocodeJsonResult.getStr("status");
//        infoCode = geocodeJsonResult.getStr("infocode");


        Map<String,Object> regeocode = geocodeJsonResult.getMap("regeocode");
        return regeocode;
    }

    public static List<Object> getPoiList(String url, String cityName){

        //搜索位置
        String searchStringResult = HttpUtils.get(url + cityName);
        ApiResult searchJsonResult = new ApiResult(searchStringResult);
        //数据处理
//        status = searchJsonResult.getStr("status");
//        infoCode = searchJsonResult.getStr("infocode");
        List<Object> poiList = (List<Object>) searchJsonResult.getList("pois");

        return poiList;
    }

    public static String getGeocodeApiUrl() {
//        int size =  keys.size();
//        Random r = new Random();
//        Integer pos = r.nextInt(size);
//
//        String key = keys.get(pos );
        String url = "http://restapi.amap.com/v3/geocode/regeo?key="+ key +"&location=";
        return url;
    }

    public static String searchApiUrl() {
//        int size =  keys.size();
//        Random r = new Random();
//        Integer pos = r.nextInt(size);
//
//        String key = keys.get(pos );
        String url = "http://restapi.amap.com/v3/place/text?key=" + key + "&extensions=all&keywords=";
        return url;
    }

    public static void main(String []arg) {
        for (int i =0;i<100;i++) {
            String s = CallBackCityApi.getGeocodeApiUrl();
            System.out.println(s);
        }
    }
}
