import api.CallBackCityApi;
import api.CallBackOTSToken;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.*;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class WeixinApiController extends ApiController {

	public ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();
		// 配置微信 API 相关常量
		ac.setToken(PropKit.get("token"));
		ac.setAppId(PropKit.get("appId"));
		ac.setAppSecret(PropKit.get("appSecret"));
		ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
		ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
		return ac;
	}

	/**
	 * 获取公众号菜单
	 */
	public void getMenu() {
		ApiResult apiResult = MenuApi.getMenu();
		if (apiResult.isSucceed()) {
			renderText(apiResult.getJson());
		}
		else {
			renderText(apiResult.getErrorMsg());
		}
	}

	/**
	 * 创建菜单
	 */
	public void createMenu()
	{
		String str = "{\n" +
				"    \"button\": [\n" +
				"        {\n" +
				"            \"type\": \"view\", \n" +
				"            \"name\": \"我要预订\", \n" +
				"            \"url\": \"http://weixin.imike.cn\"\n" +
				"        }, \n" +
				"        {\n" +
				"            \"type\": \"view\", \n" +
				"            \"name\": \"我的订单\", \n" +
				"            \"url\": \"http://www.imike.com\"\n" +
				"        }, \n" +
				"        {\n" +
				"            \"name\": \"个人中心\", \n" +
				"            \"sub_button\": [\n" +
				"                {\n" +
				"                    \"type\": \"view\", \n" +
				"                    \"name\": \"下载APP\", \n" +
				"                    \"url\": \"http://www.imike.com\"\n" +
				"                }, \n" +
				"                {\n" +
				"                    \"type\": \"view\", \n" +
				"                    \"name\": \"验证手机\", \n" +
				"                    \"url\": \"http://www.imike.com\"\n" +
				"                }, \n" +
				"                {\n" +
				"                    \"type\": \"scancode_push\", \n" +
				"                    \"name\": \"扫一扫\", \n" +
				"                    \"key\": \"scan\"\n" +
				"                }\n" +
				"            ]\n" +
				"        }\n" +
				"    ]\n" +
				"}";
		ApiResult apiResult = MenuApi.createMenu(str);
		if (apiResult.isSucceed())
			renderText(apiResult.getJson());
		else
			renderText(apiResult.getErrorMsg());
	}

	/**
	 * 获取公众号关注用户
	 */
	public void getFollowers()
	{
		ApiResult apiResult = UserApi.getFollows();
		renderText(apiResult.getJson());
	}

	/**
	 * 获取用户信息
	 */
	public void getUserInfo()
	{
		ApiResult apiResult = UserApi.getUserInfo("ohbweuNYB_heu_buiBWZtwgi4xzU");
		renderText(apiResult.getJson());
	}

	/**
	 * 发送模板消息
	 */
	public void sendMsg()
	{
		String str = " {\n" +
				"           \"touser\":\"ohbweuNYB_heu_buiBWZtwgi4xzU\",\n" +
				"           \"template_id\":\"9SIa8ph1403NEM3qk3z9-go-p4kBMeh-HGepQZVdA7w\",\n" +
				"           \"url\":\"http://www.sina.com\",\n" +
				"           \"topcolor\":\"#FF0000\",\n" +
				"           \"data\":{\n" +
				"                   \"first\": {\n" +
				"                       \"value\":\"恭喜你购买成功！\",\n" +
				"                       \"color\":\"#173177\"\n" +
				"                   },\n" +
				"                   \"keyword1\":{\n" +
				"                       \"value\":\"去哪儿网发的酒店红包（1个）\",\n" +
				"                       \"color\":\"#173177\"\n" +
				"                   },\n" +
				"                   \"keyword2\":{\n" +
				"                       \"value\":\"1元\",\n" +
				"                       \"color\":\"#173177\"\n" +
				"                   },\n" +
				"                   \"remark\":{\n" +
				"                       \"value\":\"欢迎再次购买！\",\n" +
				"                       \"color\":\"#173177\"\n" +
				"                   }\n" +
				"           }\n" +
				"       }";
		ApiResult apiResult = TemplateMsgApi.send(str);
		renderText(apiResult.getJson());
	}

	/**
	 * 获取临时参数二维码
	 * expire 时间
	 * sceneid 内容
	 */
	public void getExpireQrcode()
	{
		//临时二维码
		String str = "{\"expire_seconds\": "+getPara("expire")+", \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+getPara("sceneid")+"}}}";
		ApiResult apiResult = QrcodeApi.create(str);
		renderText(apiResult.getJson());
	}

	/**
	 * 获取永久参数二维码
	 * sceneid 内容
	 */
	public void getQrcode()
	{
		//永久二维码
        String str = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+getPara("sceneid")+"}}}";
        ApiResult apiResult = QrcodeApi.create(str);
        renderText(apiResult.getJson());
	}

	/**
	 * 长链接转成短链接
	 */
	public void getShorturl()
	{
		String str = "{\"action\":\"long2short\"," +
				"\"long_url\":\"http://wap.koudaitong.com/v2/showcase/goods?alias=128wi9shh&spm=h56083&redirect_count=1\"}";
		ApiResult apiResult = ShorturlApi.getShorturl(str);
		renderText(apiResult.getJson());
	}

	/**
	 * 获取客服聊天记录
	 */
	public void getRecord()
	{
		String str = "{\n" +
				"    \"endtime\" : 987654321,\n" +
				"    \"pageindex\" : 1,\n" +
				"    \"pagesize\" : 10,\n" +
				"    \"starttime\" : 123456789\n" +
				" }";
		ApiResult apiResult = CustomServiceApi.getRecord(str);
		renderText(apiResult.getJson());
	}

	/**
	 * 获取微信服务器IP地址
	 */
	public void getCallbackIp()
	{
		ApiResult apiResult = CallbackIpApi.getCallbackIp();
		renderText(apiResult.getJson());
	}

	/**
	 * 获取城市名和城市Code
	 * 入参
	 * longitude 经度
	 * latitude 维度
	 */
	public void getCallbackCity()
	{
		ApiResult apiResult = CallBackCityApi.getCallBackCity(getPara("longitude"),getPara("latitude"));
		renderText(apiResult.getJson());
	}

	/**
	 * 获取签名
	 */
	public void getSignature()
	{
//		JsTicket jsTicket = JsTicketApi.getTicket(JsTicketApi.JsApiType.jsapi);
//		String fuckU= DateUtil.getCurMis().toString().substring(0,10);
//
//		String encryptString = new StringBuilder().append("jsapi_ticket=" + jssdkTicket.getTicket() + "&noncestr=t8bI2mW5Mma0I20Y&timestamp=" + fuckU + "&url=" + getRequest().getRequestURL()+"").toString();
//		System.out.println("string1="+tempStr);
//
//		encryptString = SHA1.sha1Encrypt(tempStr);
//		System.out.println("加密后="+tempStr);
//
//		setAttr("timestamp", fuckU);
//		setAttr("signature",tempStr);
		String str = "{\"token\": \""+AccessTokenApi.getAccessTokenStr()+"\"}";
		renderText(str);
	}

	/**
	 * 获取Id
	 */
	public void getIds()
	{
		SnsAccessToken snsResult = SnsAccessTokenApi.getSnsAccessToken(PropKit.get("appId"),PropKit.get("appSecret"),getPara("code"));
		String openid = snsResult.getOpenid();
		String unionid = snsResult.getUnionid();
		if (unionid!=null){
			ApiResult apiResult = CallBackOTSToken.getCallBackToken(unionid);
			//结果
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("unionid", unionid);
			result.put("openid", openid);
			Boolean success = apiResult.getBoolean("success");
			if (success){
				result.put("success",1);
			}else{
				result.put("success",0);
			}
			result.put("phone",apiResult.getStr("phone"));
			result.put("check",apiResult.getStr("check"));
			result.put("token",apiResult.getStr("token"));
			renderText(JsonUtils.toJson(result));
		}else{
			renderText("{\"success\": 0 }");
		}
	}
	
	/**
	 * 获取用户增减数据
	 * begin_date 开始时间
	 * end_date 结束时间
	 */
	public void getUserSummary()
	{
		ApiResult apiResult = DatacubeApi.getUserSummary(getPara("begin_date"),getPara("end_date"));
		renderText(apiResult.getJson());
	}

	/**
	 * 获取累计用户数据
	 * begin_date 开始时间
	 * end_date 结束时间
	 */
	public void getUserCumulate()
	{
		ApiResult apiResult = DatacubeApi.getUserCumulate(getPara("begin_date"),getPara("end_date"));
		renderText(apiResult.getJson());
	}

	/**
	 * 获取图文群发每日数据
	 * begin_date 开始时间
	 * end_date 结束时间
	 */
	public void getArticleSummary()
	{
		ApiResult apiResult = DatacubeApi.getArticleSummary(getPara("begin_date"),getPara("end_date"));
		renderText(apiResult.getJson());
	}

	/**
	 * 获取图文群发总数据
	 * begin_date 开始时间
	 * end_date 结束时间
	 */
	public void getArticlEtotal()
	{
		ApiResult apiResult = DatacubeApi.getArticlEtotal(getPara("begin_date"),getPara("end_date"));
		renderText(apiResult.getJson());
	}

	/**
	 * 获取图文统计数据
	 * begin_date 开始时间
	 * end_date 结束时间
	 */
	public void getUserRead()
	{
		ApiResult apiResult = DatacubeApi.getUserRead(getPara("begin_date"),getPara("end_date"));
		renderText(apiResult.getJson());
	}

	/**
	 * 获取图文统计分时数据
	 * begin_date 开始时间
	 * end_date 结束时间
	 */
	public void getUserReadHour()
	{
		ApiResult apiResult = DatacubeApi.getUserReadHour(getPara("begin_date"),getPara("end_date"));
		renderText(apiResult.getJson());
	}

	/**
	 * 获取图文分享转发数据
	 * begin_date 开始时间
	 * end_date 结束时间
	 */
	public void getUserShare()
	{
		ApiResult apiResult = DatacubeApi.getUserShare(getPara("begin_date"),getPara("end_date"));
		renderText(apiResult.getJson());
	}

	/**
	 * 获取图文分享转发分时数据
	 * begin_date 开始时间
	 * end_date 结束时间
	 */
	public void getUserShareHour()
	{
		ApiResult apiResult = DatacubeApi.getUserShareHour(getPara("begin_date"),getPara("end_date"));
		renderText(apiResult.getJson());
	}
}

