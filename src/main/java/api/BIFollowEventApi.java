package api;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mi on 16/1/19.
 */
public class BIFollowEventApi {
    private static String apiUrl = "http://wechatfollowlog.imike.com:8000";

    public BIFollowEventApi() {
    }

    public static ApiResult sendFollowEvent(InFollowEvent inFollowEvent) {
        //请求
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("toUserName", inFollowEvent.getToUserName());
        params.put("fromUserName", inFollowEvent.getFromUserName());
        params.put("createTime", inFollowEvent.getCreateTime());
        params.put("msgType", inFollowEvent.getMsgType());
        params.put("event", inFollowEvent.getEvent());
        params.put("eventType", "followservice");
        String apiStringResult = HttpUtils.post(apiUrl,JsonUtils.toJson(params));
        ApiResult apiResult= new ApiResult(apiStringResult);
        return new ApiResult(apiResult.getJson());
    }
}
