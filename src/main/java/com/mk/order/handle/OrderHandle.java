package com.mk.order.handle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mk.common.toolutils.*;
import com.mk.enums.CallMethodEnum;
import com.mk.enums.OrderTypenum;
import com.mk.enums.PayTypeEnum;
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
            hotelid = "2230";
        }
        if (StringUtils.isEmpty(hotelid)) {
            return null;
        }

        //
        String roomtypeid = request.getParameter("roomtypeid");
        if ("true".equals(debug)) {
            roomtypeid = "29840";
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

        String  timeintervaltype= request.getParameter("timeintervaltype");
        if ("true".equals(debug)) {
            timeintervaltype = "2";
        }
        if (StringUtils.isEmpty(timeintervaltype)) {
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
            token = "83d7c5ee-ab61-4436-8538-2f52b16dcf4d";
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
        String  backStr = "";
        try{
             backStr = SmsHttpClient.post(url, parmeter);
        }catch(Exception e ){

            return null;
        }




//        if ("true".equals(debug)) {
//            request.setAttribute("orderid", "orderid");
//            request.setAttribute("hotelname", "hotelname33");
//            request.setAttribute("begintime", "begintime");
//            request.setAttribute("endtime", "endtime");
//            request.setAttribute("orderday", "orderday");
//            request.setAttribute("roomtypename", "roomtypename");
//            request.setAttribute("contacts", "contacts");
//            request.setAttribute("contactsphone", "contactsphone");
//            request.setAttribute("usermessage", "usermessage");
//            request.setAttribute("onlinepay", "onlinepay");
//            request.setAttribute("payprice", "payprice");
//            request.setAttribute("redpacket", "redpacket");
//            request.setAttribute("timeouttime", "100000");
//            return JSONObject.parseObject("{}");
//        } else

        if (StringUtils.isNotEmpty(backStr)) {
            JSONObject object = this.parseObject(backStr);
            Object orderid = object.get("orderid");
            Object hotelname = object.get("hotelname");
            Object begintime = object.get("begintime");
            Object endtime = object.get("endtime");
            Object contacts = object.get("contacts");
            Object contactsphone = object.get("contactsphone");
            Object usermessage = object.get("usermessage");
            Object onlinepay = object.get("onlinepay");
            Object payprice = object.get("payprice");
            Object redpacket = object.get("redpacket");
            Object timeouttime = object.get("timeouttime");


            //roomorder
            JSONArray roomOrders = object.getJSONArray("roomorder");
            JSONObject roomOrder = roomOrders.getJSONObject(0);
            Object orderday = roomOrder.get("orderday");
            Object roomtypename = roomOrder.get("roomtypename");

            //
            request.setAttribute("orderid", DataHander.checkStringNull(object,"orderid","0"));
            request.setAttribute("hotelname",DataHander.checkStringNull(object,"hotelname",""));

            //
            String  begintimeOri = DataHander.checkStringNull(object,"begintime","");
            begintimeOri=  DateUtil.getStrFormart(begintimeOri,"yyyyMMhh");

            String  endtimeOri = DataHander.checkStringNull(object,"endtime","");
            endtimeOri=  DateUtil.getStrFormart(endtimeOri,"yyyyMMhh");

            request.setAttribute("begintime",begintimeOri);
            request.setAttribute("endtime", endtimeOri);

            try{
                request.setAttribute("orderday", DateUtil.daysBetween(endtimeOri,begintimeOri,"yyyyMMdd"));
            }catch (Exception e){
                e.printStackTrace();
            }
            request.setAttribute("roomtypename", DataHander.checkStringNull(object,"roomorder","roomtypename",""));
            request.setAttribute("contacts", DataHander.checkStringNull(object,"contacts",""));
            request.setAttribute("contactsphone", DataHander.checkStringNull(object,"contactsphone",""));
            request.setAttribute("usermessage", DataHander.checkStringNull(object,"usermessage",""));
            request.setAttribute("onlinepay", DataHander.checkStringNull(object,"usermessage",""));
            request.setAttribute("price", DataHander.checkStringNull(object,"roomorder","payprice","price",""));
            request.setAttribute("maxuserwalletcost", DataHander.checkStringNull(object,"maxuserwalletcost",""));
            request.setAttribute("timeintervalstart", DataHander.checkStringNull(object,"timeintervalstart",""));
            request.setAttribute("timeintervalend", DataHander.checkStringNull(object,"timeintervalend",""));
            request.setAttribute("timeouttime", DataHander.checkStringNull(object,"timeouttime","0"));

            return object;
        }

        return null;
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
        hmap.put("callmethod", CallMethodEnum.WEIXIN.getId());

        //
        String url = UrlUtil.getValue(BaseData.queryWXUserWallet);
        String backStr = SmsHttpClient.post(url, hmap);
        if(StringUtils.isEmpty(backStr)){
            return null;
        }
        JSONObject jso = this.parseObject(backStr);
        if(null==jso){
            return null;
        }
        ;
        request.setAttribute("balance", jso.getString("balance"));
        if ("true".equals(debug)) {
            request.setAttribute("balance", "99999999");
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

        String orderId = request.getParameter("orderid");
        String userName = request.getParameter("username");
        String userMobile = request.getParameter("usermobile");
        String walletCost = request.getParameter("walletcost");

        if (StringUtils.isEmpty(orderId)) {
            return "toCreate";
        }

        //
        String debug = UrlUtil.getValue(BaseData.debug);
        if ("true".equals(debug)) {
            orderId = "1282388";
            userName = "userNameTest";
            userMobile = "123456789";
            walletCost = "10";
        }

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userMobile) || StringUtils.isEmpty(orderId)) {
            return "error";
        }
        //
        HashMap parmeter = new HashMap();
        parmeter.put("username", userName);
        parmeter.put("usermobile", userMobile);
        parmeter.put("orderid", orderId);
        parmeter.put("walletcost", walletCost);

        //token
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return "error";
        }
        String token = null;
        for (int i = 0; i < cookies.length; i++) {
            if ("token".equals(cookies[i].getName())) {
                token = cookies[i].getValue();
                break;
            }
        }
        if ("true".equals(debug)) {
            token = "a3fea418-c922-4781-a2be-2b8474d5dde0";
        }
        if (StringUtils.isEmpty(token)) {
            return "error";
        }

        parmeter.put("token", token);
        parmeter.put("callmethod", CallMethodEnum.WEIXIN.getId());

        //
        String backStr = SmsHttpClient.post(UrlUtil.getValue(BaseData.modifyOrderUrl), parmeter);
        if (StringUtils.isEmpty(backStr)) {
            return "error";
        }

//        JSONObject jsonOrder = JSONObject.parseObject(backStr);
//        if (!"true".equals(jsonOrder.getString("success"))) {
//            return "error";
//        } else {
//
//        }

        //
        request.setAttribute("orderid", orderId);
        return "success";
    }

    public String pay(HttpServletRequest request) {

        String orderid = (String)request.getAttribute("orderid");
        //
        String debug = UrlUtil.getValue(BaseData.debug);
        if ("true".equals(debug)) {
            orderid = "1282388";
        }
        HashMap parmeterPay = new HashMap();
        parmeterPay.put("orderid", orderid);
        parmeterPay.put("paytype", OrderTypenum.YF.getId());
        parmeterPay.put("onlinepaytype", PayTypeEnum.WECHAT.getId());
        parmeterPay.put("callmethod", CallMethodEnum.WEIXIN.getId());

        //token
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            return "error";
        }
        String token = null;
        for (int i = 0; i < cookies.length; i++) {
            if ("token".equals(cookies[i].getName())) {
                token = cookies[i].getValue();
                break;
            }
        }
        if ("true".equals(debug)) {
            token = "a3fea418-c922-4781-a2be-2b8474d5dde0";
        }
        if (StringUtils.isEmpty(token)) {
            return "error";
        }

        parmeterPay.put("token", token);

        String backStr = SmsHttpClient.post(UrlUtil.getValue(BaseData.createPayUrl), parmeterPay);
        if (StringUtils.isEmpty(backStr)) {
            return "error";
        }
        JSONObject jsonPay = JSONObject.parseObject(backStr);
        if (!"true".equals(jsonPay.getString("success"))) {
            return "error";
        } else {
            if (!jsonPay.containsKey("weinxinpay")) {
                return "error";
            }
            JSONObject json = jsonPay.getJSONObject("weinxinpay");
            String appid = json.getString("appid");
            String appkey = json.getString("appkey");
            String noncestr = json.getString("noncestr");
            String packagevalue = json.getString("packagevalue");
            String partnerid = json.getString("partnerid");
            String timestamp = json.getString("timestamp");
            String sign = json.getString("sign");

            request.setAttribute("appId", appid);
            request.setAttribute("timeStamp", timestamp);
            request.setAttribute("nonceStr", noncestr);
            request.setAttribute("packageValue", packagevalue);
            request.setAttribute("paySign", sign);
            request.setAttribute("orderDetailUrl", "");
            return "success";
        }
    }

    public static void main(String[] args) {
        String url = "http://huidu.imike.cn/ots/order/create";
        HashMap  hm = new HashMap();
        hm.put("hotelid",2803);
        SmsHttpClient.post(url, hm);
    }
}
