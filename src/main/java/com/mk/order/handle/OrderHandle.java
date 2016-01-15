package com.mk.order.handle;

import com.alibaba.fastjson.JSONObject;
import com.mk.common.toolutils.BaseData;
import com.mk.common.toolutils.SmsHttpClient;
import com.mk.common.toolutils.UrlUtil;
import com.mk.enums.CallMethodEnum;
import com.mk.enums.OrderTypenum;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class OrderHandle {


    public JSONObject createOrder(HttpServletRequest request) throws IOException {

        //
        String debug = UrlUtil.getValue(BaseData.debug);

        //
        String startdateday = request.getParameter("startdate");
        if ("true".equals(debug)) {
            startdateday = "20160115";
        }
        if (StringUtils.isEmpty(startdateday)) {
            return null;
        }

        //
        String enddateday = request.getParameter("enddate");
        if ("true".equals(debug)) {
            enddateday = "20160116";
        }
        if (StringUtils.isEmpty(enddateday)) {
            return null;
        }

        //
        String hotelid = request.getParameter("hotelid");
        if ("true".equals(debug)) {
            hotelid = "2803";
        }
        if (StringUtils.isEmpty(hotelid)) {
            return null;
        }

        //
        String roomtypeid = request.getParameter("roomtypeid");
        if ("true".equals(debug)) {
            roomtypeid = "29949";
        }
        if (StringUtils.isEmpty(roomtypeid)) {
            return null;
        }

        //
        String timeintervalstart = request.getParameter("timeintervalstart");
        if ("true".equals(debug)) {
            timeintervalstart = "7";
        }
        if (StringUtils.isEmpty(timeintervalstart)) {
            return null;
        }

        //
        String timeintervalend = request.getParameter("timeintervalend");
        if ("true".equals(debug)) {
            timeintervalend = "20";
        }
        if (StringUtils.isEmpty(timeintervalend)) {
            return null;
        }

        //
        String pricetype = request.getParameter("pricetype");
        if ("true".equals(debug)) {
            pricetype = "2";
        }
        //token
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return null;
        }

        //
        String token = null;
        for (int i = 0; i < cookies.length; i++) {
            if ("token".equals(cookies[i].getName())) {
                token = cookies[i].getValue();
                break;
            }
        }

        if ("true".equals(debug)) {
            token = "93bb2aae-7e75-4d01-abea-0fc76bc0d763";
        }
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        //
        String userlongitude = request.getParameter("userlongitude");
        if ("true".equals(debug)) {
            userlongitude = "121";
        }
        String userlatitude = request.getParameter("userlatitude");
        if ("true".equals(debug)) {
            userlatitude = "31";
        }

        //
        HashMap<String, String> parmeter = new HashMap();
        parmeter.put("token", token);
        parmeter.put("hotelid", hotelid);
        parmeter.put("roomtypeid", roomtypeid);
        parmeter.put("pricetype", pricetype);
        parmeter.put("startdateday", startdateday);
        parmeter.put("enddateday", enddateday);
        parmeter.put("timeintervalstart", timeintervalstart);
        parmeter.put("timeintervalend", timeintervalend);

        if (StringUtils.isNotEmpty(userlongitude)) {
            parmeter.put("userlongitude", userlongitude);
        }

        if (StringUtils.isNotEmpty(userlatitude)) {
            parmeter.put("userlatitude", userlatitude);
        }
        parmeter.put("ordertype", OrderTypenum.DF.getId());
        parmeter.put("callmethod", CallMethodEnum.WEIXIN.getId());
        parmeter.put("callversion", "3.5");

        //
        String url = UrlUtil.getValue(BaseData.creatOrderUrl);
        String backStr = SmsHttpClient.post(url, parmeter);
        if ("true".equals(debug)) {
            request.setAttribute("orderid","orderid");
            request.setAttribute("hotelname","hotelname33");
            request.setAttribute("begintime","begintime");
            request.setAttribute("endtime","endtime");
            request.setAttribute("orderday","orderday");
            request.setAttribute("roomtypename","roomtypename");
            request.setAttribute("contacts","contacts");
            request.setAttribute("contactsphone","contactsphone");
            request.setAttribute("usermessage","usermessage");
            request.setAttribute("onlinepay","onlinepay");
            request.setAttribute("payprice","payprice");
            request.setAttribute("redpacket","redpacket");
            request.setAttribute("timeouttime","100000");
        }
        return this.parseObject(backStr);
    }

    public JSONObject getUserWXwallet(HttpServletRequest request) {

        //
        String debug = UrlUtil.getValue(BaseData.debug);
        //
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return null;
        }
        String token = null;
        for (int i = 0; i < cookies.length; i++) {
            if ("token".equals(cookies[i].getName())) {
                token = cookies[i].getValue();
                break;
            }
        }
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        HashMap hmap = new HashMap();
        hmap.put("token", token);

        //
        String url = UrlUtil.getValue(BaseData.queryWXUserWallet);
        String backStr = SmsHttpClient.post(url, hmap);
        if ("true".equals(debug)) {
            request.setAttribute("maxWallet","maxWallet");
            request.setAttribute("wallet","wallet");
        }
        return this.parseObject(backStr);
    }

    private JSONObject parseObject(String str) {

        try {
            JSONObject jsonOb = JSONObject.parseObject(str);
            return jsonOb;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String modify(HttpServletRequest request) {
        HashMap parmeter = new HashMap();
//	   if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(userMobile)||(orderId==0)){
//		   return "error";
//	   }
//	   parmeter.put("username", userName);
//	   parmeter.put("usermobile", userMobile);
//	   parmeter.put("orderid", orderId);
//	   parmeter.put("orderid", orderId);
//
//
//
//	   HttpServletRequest  request =  ServletActionContext.getRequest();
//
//	   Cookie[]  cookies = request.getCookies();
//	    if(null==cookies){
//	    	return  "error";
//	    }
//	    String token = null;
//	    for(int i=0;i<cookies.length;i++){
//	    	if("token".equals(cookies[i].getName())){
//	    		token = cookies[i].getValue();
//	    		break;
//	    	}
//	    }
        /*if(StringUtils.isEmpty(token)){
	        	return  "error";
	    }
	    
	    parmeter.put("token", token);
	    parmeter.put("callmethod",CallMethodEnum.WEIXIN.getId());

	    
	    String  backStr = SmsHttpClient.post(UrlUtil.getValue(BaseData.modifyOrderUrl), parmeter);
	    if(StringUtils.isEmpty(backStr)){
	    	return "error";
	    }

	    JSONObject jsonOrder = JSONObject.parseObject(backStr);
	    if(!"true".equals(jsonOrder.getString("success"))){
	    	return "error";
	    }else{
	    	
	    }
		*/
        return null;
    }

//	protected   String    pay(HashMap hm){
//	   HashMap  parmeterPay = new HashMap();
//	   parmeterPay.put("paytype	", OrderTypenum.YF.getId());
//	   parmeterPay.put("onlinepaytype", PayTypeEnum.WECHAT.getId());
//	   parmeterPay.put("callmethod", CallMethodEnum.WEIXIN.getId());
//	   String  backStr = SmsHttpClient.post(UrlUtil.getValue(BaseData.createPayUrl), parmeterPay);
//	   if(StringUtils.isEmpty(backStr)){
//	    	return "error";
//	    }
//	   JSONObject jsonPay = JSONObject.parseObject(backStr);
//	   if(!"true".equals(jsonPay.getString("success"))){
//	    	return "error";
//	    }else{
//	    	if(!jsonPay.containsKey("weinxinpay")){
//		    	return "error";
//	    	}
//	    	JSONObject json = jsonPay.getJSONObject("weinxinpay");
//	    	String  appid = json.getString("appid");
//	    	String  appkey = json.getString("appkey");
//	    	String  noncestr = json.getString("noncestr");
//	    	String  packagevalue = json.getString("packagevalue");
//	    	String  partnerid = json.getString("partnerid");
//	    	String  timestamp = json.getString("timestamp");
//	    	String  sign = json.getString("sign");
//
//	    }
//
//		return  null;
//	}

    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        System.out.println(json.getString("sing"));
    }
}
