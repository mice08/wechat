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
    private static String geocodeApiUrl = "http://restapi.amap.com/v3/geocode/regeo?key=98e241d687a3ebf02ac2af0a8bbee272&location=";
    private static String searchApiUrl = "http://restapi.amap.com/v3/place/text?key=98e241d687a3ebf02ac2af0a8bbee272&extensions=all&keywords=";

    public CallBackCityApi() {
    }

    public static ApiResult getCallBackCity(String longitude,String latitude) {
        Coordinate coordinate= LocationConvert.wgsTogcj(new Coordinate(Double.valueOf(latitude),Double.valueOf(longitude)));
        //逆地理位置编码
        String geocodeStringResult = HttpUtils.get(geocodeApiUrl + coordinate.longitude + "," +coordinate.latitude);
        ApiResult geocodeJsonResult= new ApiResult(geocodeStringResult);
        //数据处理
        String status = geocodeJsonResult.getStr("status");
        String infoCode = geocodeJsonResult.getStr("infocode");
        Map<String,Object> regeocode = geocodeJsonResult.getMap("regeocode");
        Map<String,Object> addressComponent = (Map<String,Object> )regeocode.get("addressComponent");
        List<String> cityList = (List<String>) addressComponent.get("city");
        //直辖市处理
        String cityName="";
        String cityCode="";
        if (cityList.size()==0){
            cityName = (String) addressComponent.get("province");
        }else {
            cityName = cityList.get(0);
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
}
