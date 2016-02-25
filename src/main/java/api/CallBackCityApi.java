package api;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import tool.Coordinate;
import tool.LocationConvert;

import java.util.List;
import java.util.Map;

/**
 * Created by Mi on 16/1/12.
 */
public class CallBackCityApi {
    private static String geocodeApiUrl = "http://restapi.amap.com/v3/geocode/regeo?key=297acef28cce6c832242ffda80c13f8b&location=";
    private static String searchApiUrl = "http://restapi.amap.com/v3/place/text?key=297acef28cce6c832242ffda80c13f8b&extensions=all&keywords=";

    public CallBackCityApi() {
    }

    public static ApiResult getCallBackCity(String longitude,String latitude) {
        Coordinate coordinate= LocationConvert.wgsTogcj(new Coordinate(Double.valueOf(latitude),Double.valueOf(longitude)));
        //初始化
        String status="0";
        String infoCode="0";
        String cityName="";
        String cityCode="";
        //逆地理位置编码
        String geocodeStringResult = HttpUtils.get(geocodeApiUrl + coordinate.longitude + "," +coordinate.latitude);
        ApiResult geocodeJsonResult= new ApiResult(geocodeStringResult);
        //数据处理
        status = geocodeJsonResult.getStr("status");
        infoCode = geocodeJsonResult.getStr("infocode");
        Map<String,Object> regeocode = geocodeJsonResult.getMap("regeocode");
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
            //搜索位置
            String searchStringResult = HttpUtils.get(searchApiUrl + cityName);
            ApiResult searchJsonResult = new ApiResult(searchStringResult);
            //数据处理
            status = searchJsonResult.getStr("status");
            infoCode = searchJsonResult.getStr("infocode");
            List<Object> poiList = (List<Object>) searchJsonResult.getList("pois");
            if (poiList!= null && poiList.size() > 0) {
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
}
