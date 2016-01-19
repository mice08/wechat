package api;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mi on 16/1/19.
 */
public class BIQrCodeEventApi {
    private static String apiUrl = "http://wechatfollowlog.imike.com:8000";

    public BIQrCodeEventApi() {
    }

    public static ApiResult sendQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        //请求
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("toUserName", inQrCodeEvent.getToUserName());
        params.put("fromUserName", inQrCodeEvent.getFromUserName());
        params.put("createTime", inQrCodeEvent.getCreateTime());
        params.put("msgType", inQrCodeEvent.getMsgType());
        params.put("event", inQrCodeEvent.getEvent());
        params.put("eventKey", inQrCodeEvent.getEventKey());
        params.put("ticket", inQrCodeEvent.getTicket());
        params.put("eventType", "enterservice");
        String apiStringResult = HttpUtils.post(apiUrl, JsonUtils.toJson(params));
        ApiResult apiResult= new ApiResult(apiStringResult);
        return new ApiResult(apiResult.getJson());
    }
}
