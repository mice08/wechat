import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.utils.HttpUtils;

/**
 * Created by Mi on 16/1/12.
 */
public class CallBackCityApi {
    private static String apiUrl = "http://restapi.amap.com/v3/geocode/regeo?key=98e241d687a3ebf02ac2af0a8bbee272&location=";

    public CallBackCityApi() {
    }

    public static ApiResult getCallBackCity(double longitude,double latitude) {
        String jsonResult = HttpUtils.get(apiUrl + longitude + "," +latitude);
        return new ApiResult(jsonResult);
    }
}
