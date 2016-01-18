package api;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.HttpKit;
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
    private static String apiUrl = "http://huidu.imike.cn/ots/unionidandphone/check";

    public CallBackOTSToken() {
    }

    public static ApiResult getCallBackToken(String unionid) {
        //请求
        String apiStringResult = HttpUtils.post(apiUrl+"?ostype=3&unionid="+unionid,"");
        ApiResult apiResult= new ApiResult(apiStringResult);
        return new ApiResult(apiResult.getJson());
    }
}
