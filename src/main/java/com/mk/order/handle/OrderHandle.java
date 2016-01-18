package com.mk.order.handle;

import com.alibaba.fastjson.JSON;
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
import java.util.*;

public class OrderHandle {


    public JSONObject createOrder(HttpServletRequest request) throws IOException {


        //
        String debug = UrlUtil.getValue(BaseData.debug);

        String ordertype = request.getParameter("ordertype");
        System.out.println("ordertype:" + ordertype);

        if ("true".equals(debug)) {
            ordertype = "7";
        }
        if (StringUtils.isEmpty(ordertype)) {
            return null;
        }

        String timeintervalstart = "";
        String timeintervalend = "";
        String timeintervaltype = "";
        System.out.println("leixing:" + OrderTypenum.YF.getId().equals(ordertype));
        //预付
        if (OrderTypenum.YF.getId().equals(ordertype)) {
            timeintervalstart = request.getParameter("timeintervalstart");
            System.out.println("timeintervalstart:" + timeintervalstart);

            if ("true".equals(debug)) {
                timeintervalstart = "7";
            }
            if ("undefined".equals(timeintervalstart)) {
                timeintervalstart = null;
            }


            //
            timeintervalend = request.getParameter("timeintervalend");
            System.out.println("timeintervalend:" + timeintervalend);

            if ("true".equals(debug)) {
                timeintervalend = "19";
            }
            if ("undefined".equals(timeintervalend)) {
                timeintervalend = null;
            }

            timeintervaltype = request.getParameter("timeintervaltype");
            System.out.println("timeintervaltype:" + timeintervaltype);

            if ("true".equals(debug)) {
                timeintervaltype = "1";
            }
            if (StringUtils.isEmpty(timeintervaltype)) {
                return null;
            }

        }

        //
        String startdateday = request.getParameter("startdateday");
        System.out.println("startdateday:" + startdateday);

        if ("true".equals(debug)) {
            startdateday = "20160116";
        }
        if (StringUtils.isEmpty(startdateday)) {
            return null;
        }

        //
        String enddateday = request.getParameter("enddateday");
        System.out.println("enddateday:" + enddateday);

        if ("true".equals(debug)) {
            enddateday = "20160117";
        }
        if (StringUtils.isEmpty(enddateday)) {
            return null;
        }

        //
        String hotelid = request.getParameter("hotelid");
        System.out.println("hotelid:" + hotelid);

        if ("true".equals(debug)) {
            hotelid = "2230";
        }
        if (StringUtils.isEmpty(hotelid)) {
            return null;
        }

        //
        String roomtypeid = request.getParameter("roomtypeid");
        System.out.println("roomtypeid:" + roomtypeid);

        if ("true".equals(debug)) {
            roomtypeid = "29885";
        }
        if (StringUtils.isEmpty(roomtypeid)) {
            return null;
        }

        //


        //
        String pricetype = request.getParameter("pricetype");
        System.out.println("pricetype:" + pricetype);

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
        token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";

        if ("true".equals(debug)) {
            token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
        }

        System.out.println("token:" + token);

        if (StringUtils.isEmpty(token)) {
            return null;
        }


        //
        String userlongitude = request.getParameter("userlongitude");
        if ("true".equals(debug)) {
            userlongitude = "121";
        }
        System.out.println("userlongitude:" + userlongitude);

        String userlatitude = request.getParameter("userlatitude");
        if ("true".equals(debug)) {
            userlatitude = "31";
        }
        System.out.println("userlatitude:" + userlatitude);

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
        parmeter.put("ordertype", ordertype);
        parmeter.put("callmethod", CallMethodEnum.WEIXIN.getId());
        parmeter.put("ordermethod", CallMethodEnum.WEIXIN.getId());
        parmeter.put("callversion", "3.5");

        System.out.println(123);


        //
        String url = UrlUtil.getValue(BaseData.creatOrderUrl);
        System.out.println("url:" + url);

        String backStr = "";
        try {
            backStr = SmsHttpClient.post(url, parmeter);

            System.out.println("backStr:" + backStr);

        } catch (Exception e) {

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

            if (!"true".equals(object.getString("success"))) {
                request.setAttribute("errmsg", DataHander.checkStringNull(object, "errmsg", ""));
                return null;
            }

            //
            request.setAttribute("orderid", DataHander.checkStringNull(object, "orderid", "0"));
            request.setAttribute("hotelname", DataHander.checkStringNull(object, "hotelname", ""));

            //
            String begintimeOri = DataHander.checkStringNull(object, "begintime", "");
            begintimeOri = DateUtil.getStrFormart(begintimeOri, "yyyyMMhh");

            String endtimeOri = DataHander.checkStringNull(object, "endtime", "");
            endtimeOri = DateUtil.getStrFormart(endtimeOri, "yyyyMMhh");

            request.setAttribute("begintime", begintimeOri);
            request.setAttribute("endtime", endtimeOri);

            try {
                request.setAttribute("orderday", DateUtil.daysBetween(endtimeOri, begintimeOri, "yyyyMMdd"));
            } catch (Exception e) {
                e.printStackTrace();
            }


            request.setAttribute("roomtypename", DataHander.checkStringNull(object, "roomorder", "roomtypename", ""));
            request.setAttribute("walletcost", DataHander.checkStringNull(object, "walletcost", "0"));
            request.setAttribute("contacts", DataHander.checkStringNull(object, "contacts", ""));
            request.setAttribute("contactsphone", DataHander.checkStringNull(object, "contactsphone", ""));
            request.setAttribute("usermessage", DataHander.checkStringNull(object, "usermessage", ""));
            request.setAttribute("onlinepay", DataHander.checkStringNull(object, "onlinepay", "0"));
            request.setAttribute("price", DataHander.checkStringNull(object, "roomorder", "payprice", "price", "0"));
            request.setAttribute("totalprice", DataHander.checkStringNull(object, "roomorder", "totalprice", "0"));
            request.setAttribute("maxuserwalletcost", DataHander.checkStringNull(object, "maxuserwalletcost", "0"));
            request.setAttribute("timeintervalstart", DataHander.checkStringNull(object, "timeintervalstart", ""));
            request.setAttribute("timeintervalend", DataHander.checkStringNull(object, "timeintervalend", ""));
            String backtimeouttime = DataHander.checkStringNull(object, "timeouttime", "0");
            if (!"0".equals(backtimeouttime)) {
                try {
                    System.out.println("backtimeouttime:" + DateUtil.timesBetween(DateUtil.getStringDate("yyyyMMddHHmmss"),backtimeouttime, "yyyyMMddHHmmss"));
                    request.setAttribute("timeouttime", DateUtil.timesBetween(DateUtil.getStringDate("yyyyMMddHHmmss"),backtimeouttime, "yyyyMMddHHmmss"));
                } catch (Exception e) {
                    System.out.println("时间处理错误");
                    e.printStackTrace();
                }
            }


            return object;
        }

        return null;
    }

    public JSONObject getUserWXwallet(HttpServletRequest request) {

        System.out.println("开始查询红包总额");


        //
        String debug = UrlUtil.getValue(BaseData.debug);
        System.out.println("获取cookies:" + request.getCookies());

        //
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            System.out.println("获取cookies为空");
            return null;
        }
        String token = null;
        System.out.println("开始获取cookies");

        for (int i = 0; i < cookies.length; i++) {
            if ("token".equals(cookies[i].getName())) {
                token = cookies[i].getValue();
                break;
            }
        }
        token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
        System.out.println("获取红包:token" + token);

        if (StringUtils.isEmpty(token)) {
            return null;
        }
        HashMap hmap = new HashMap();

        hmap.put("token", token);
        hmap.put("callmethod", CallMethodEnum.WEIXIN.getId());

        //
        String url = UrlUtil.getValue(BaseData.queryWXUserWallet);

        System.out.println("获取红包:url" + url);

        String backStr = SmsHttpClient.post(url, hmap);
        System.out.println("获取红包返回参数:backStr" + backStr);

        if (StringUtils.isEmpty(backStr)) {
            return null;
        }
        JSONObject jso = this.parseObject(backStr);
        if (null == jso) {
            return null;
        }
        System.out.println("balance:" + DataHander.checkStringNull(jso, "balance", "0"));
        request.setAttribute("balance", DataHander.checkStringNull(jso, "balance", "0"));
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
        System.out.println("修改订单开始.");
        String orderId = request.getParameter("orderid");
        String userName = request.getParameter("username");
        String userMobile = request.getParameter("usermobile");
        String walletCost = request.getParameter("walletcost");
        String ordertype = request.getParameter("ordertype");

        System.out.println("修改订单开始.orderId:" + orderId);

        if (StringUtils.isEmpty(orderId)) {
            System.out.println("orderId:" + orderId + "跳转 toCreate");
            return "toCreate";
        }


        //
        List<Map<String, String>> checkinuser = new ArrayList<Map<String, String>>();
        Map<String, String> checkinusermap = new HashMap<String, String>();
        checkinusermap.put("name", userName);
        checkinusermap.put("phone", userMobile);
        checkinuser.add(checkinusermap);
        String _checkinuser = String.valueOf(JSON.toJSON(checkinuser));

        //
        String debug = UrlUtil.getValue(BaseData.debug);
        if ("true".equals(debug)) {
            orderId = "1282844";
            //
            checkinuser = new ArrayList<Map<String, String>>();
            checkinusermap = new HashMap<String, String>();
            checkinusermap.put("name", "张三丰");
            checkinusermap.put("phone", "12345678");
            checkinuser.add(checkinusermap);
            _checkinuser = String.valueOf(JSON.toJSON(checkinuser));
            //
            userName = "eeeee";
            userMobile = "111111111";
            walletCost = "10";
            ordertype = "1";
        }
        System.out.println("修改订单开始.ordertype:" + ordertype);

        System.out.println("修改订单开始.userName:" + userName + "userMobile:" + userMobile + "orderId:" + orderId);


        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userMobile) || StringUtils.isEmpty(orderId)) {
            return "error";
        }
        //
        System.out.println("修改订单开始.walletCost:" + walletCost);

        HashMap parmeter = new HashMap();
        parmeter.put("checkinuser", _checkinuser);
        parmeter.put("orderid", orderId);
        parmeter.put("walletcost", walletCost);
        parmeter.put("ordertype", ordertype);
        parmeter.put("contacts", userName);
        parmeter.put("contactsphone", userMobile);

        System.out.println("修改订单开始.准备获取cookies:");

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
        token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
        if ("true".equals(debug)) {
            token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
        }
        System.out.println("修改订单开始.token:" + token);

        if (StringUtils.isEmpty(token)) {
            return "error";
        }

        parmeter.put("token", token);
        parmeter.put("callmethod", CallMethodEnum.WEIXIN.getId());


        //
        String backStr = SmsHttpClient.post(UrlUtil.getValue(BaseData.modifyOrderUrl), parmeter);
        System.out.println("修改订单开始请求backStr:" + backStr);

        if (StringUtils.isEmpty(backStr)) {

            return "error";
        }

        JSONObject jsonOrder = JSONObject.parseObject(backStr);
        if (!"true".equals(jsonOrder.getString("success"))) {
            request.setAttribute("errmsg", DataHander.checkStringNull(jsonOrder, "errmsg", ""));
            return "error";
        }

        //
        request.setAttribute("orderid", orderId);
        request.setAttribute("ordertype", ordertype);
        return "success";
    }

    public String pay(HttpServletRequest request, HttpServletResponse response) {

        String orderid = (String) request.getAttribute("orderid");
        String ordertype = (String) request.getAttribute("ordertype");

        System.out.println("修改订单开始请求orderid:" + orderid);
        System.out.println("修改订单开始请求ordertype:" + ordertype);
        if (null == orderid || null == ordertype) {
            return "error";
        }

        //
        String debug = UrlUtil.getValue(BaseData.debug);
        //
        HashMap parmeterPay = new HashMap();
        parmeterPay.put("orderid", orderid);
        parmeterPay.put("paytype", ordertype);
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
        token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
        if ("true".equals(debug)) {
            token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
        }
        System.out.println("修改订单开始请求token:" + token);

        if (StringUtils.isEmpty(token)) {
            return "error";
        }

        parmeterPay.put("token", token);

        String backStr = SmsHttpClient.post(UrlUtil.getValue(BaseData.createPayUrl), parmeterPay);
        System.out.println("修改订单开始请求backStr:" + backStr);

        if (StringUtils.isEmpty(backStr)) {
            return "error";
        }
        JSONObject jsonPay = JSONObject.parseObject(backStr);
        if (!"true".equals(jsonPay.getString("success"))) {
            request.setAttribute("errmsg", DataHander.checkStringNull(jsonPay, "errmsg", ""));
            return "error";
        } else {
            if (OrderTypenum.DF.getId().equals(ordertype)) {
//                try {
//                    System.out.println("in handle redirect to detail start");
//                    response.sendRedirect(UrlUtil.getValue(BaseData.orderDetailUrl) + orderid);
//                    System.out.println("in handle redirect to detail end");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                request.setAttribute("orderDetailUrl", UrlUtil.getValue(BaseData.orderDetailUrl) + orderid);
                return "redirect";
            }
            if (!jsonPay.containsKey("weinxinpay")) {
                return "error";
            }
            JSONObject json = jsonPay.getJSONObject("weinxinpay");
            String appid = json.getString("appid");
            String noncestr = json.getString("noncestr");
            String packagevalue = json.getString("packagevalue");
            String prepayid = json.getString("prepayid");
            String timestamp = json.getString("timestamp");
            String key = "WAdFh6c24MZ0HB4y0zpSC0zey4vfPZk7";
            String sign = this.getSign(appid, noncestr, prepayid, timestamp, key);

            //appid=wxf5b5e87a6a0fde94&noncestr=123&package=WAP
            // &prepayid=wx201412101630480281750c890475924233&sign=53D411FB74FE0B0C79CC94F2AB0E2333&timestamp=1417511263
//            StringBuilder stringBuilder = new StringBuilder()
//                    .append("appid=").append(appid)
//                    .append("&noncestr=").append(noncestr)
//                    .append("&package=WAP")
//                    .append("&prepayid=").append(prepayid)
//                    .append("&sign=").append(sign)
//                    .append("&timestamp=").append(timestamp);
//            try {
//                String url = URLEncoder.encode(stringBuilder.toString(),"UTF8");
//                request.setAttribute("url", "weixin://wap/pay?" + url);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }

            request.setAttribute("appId", appid);
            request.setAttribute("timeStamp", timestamp);
            request.setAttribute("nonceStr", noncestr);
            request.setAttribute("prepayid", prepayid);
            request.setAttribute("package", packagevalue);
            request.setAttribute("packagevalue", packagevalue);

            request.setAttribute("paySign", sign);
            request.setAttribute("orderDetailUrl", UrlUtil.getValue(BaseData.orderDetailUrl) + orderid);
            return "success";
        }

    }

    public String getSign(String appId, String noncestr, String prepay_id, String timestamp, String key) {
        String keys = "appId=" + appId + "&nonceStr=" + noncestr + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timestamp + "&key=" + key;
        return MD5.MD5Encode(keys).toUpperCase();
    }

    public static void main(String[] args) {
        String url = "http://huidu.imike.cn/ots/order/create";
        HashMap hm = new HashMap();
        hm.put("hotelid", 2803);
        SmsHttpClient.post(url, hm);
    }
}
