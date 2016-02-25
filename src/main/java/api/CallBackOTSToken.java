package api;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import net.sf.json.util.JSONUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mi on 16/1/18.
 */
public class CallBackOTSToken {

    static Log logger = Log.getLog(CallBackOTSToken.class);

    private static String apiUrl = PropKit.get("otsHttpUrl")+"unionidandphone/check";

    public CallBackOTSToken() {
    }

    public static ApiResult getCallBackToken(String unionid, String openid) {
        logger.info("CallBackOTSToken.getCallBackToken start");
        logger.info("CallBackOTSToken.getCallBackToken apiUrl:"+apiUrl);
        //请求
        String apiStringResult = HttpUtils.post(apiUrl+"?ostype=3&unionid="+unionid + "&openid=" + openid,"");
        logger.info("CallBackOTSToken.getCallBackToken apiStringResult:"+apiStringResult);
        ApiResult apiResult= new ApiResult(apiStringResult);
        return new ApiResult(apiResult.getJson());
    }
}
