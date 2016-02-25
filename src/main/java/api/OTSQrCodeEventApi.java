package api;

import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mi on 16/1/28.
 */
public class OTSQrCodeEventApi {
    private static String apiUrl = PropKit.get("otsHttpUrl")+"login/scan";

    static Log logger = Log.getLog(OTSQrCodeEventApi.class);

    public OTSQrCodeEventApi() {
    }

    public static void sendQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        //请求
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("openid", inQrCodeEvent.getFromUserName());
        params.put("ticket", inQrCodeEvent.getTicket());
        params.put("createtime", inQrCodeEvent.getCreateTime());
        params.put("msgtype", inQrCodeEvent.getMsgType());
        params.put("event", inQrCodeEvent.getEvent());
        params.put("eventkey", inQrCodeEvent.getEventKey());

        logger.debug("time：" + inQrCodeEvent.getCreateTime());

        String paramsString = "?";
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            paramsString = paramsString + entry.getKey() + "=" + entry.getValue()+"&";
        }
        HttpUtils.post(apiUrl+paramsString, "");
    }
}


