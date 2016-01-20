package com.mk.order.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.mk.common.toolutils.*;
import com.mk.enums.CallMethodEnum;
import com.mk.enums.OrderTypenum;
import com.mk.enums.PayTypeEnum;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class OrderHandle {

    static Log logger = Log.getLog(OrderHandle.class);

    //订单路由
    public  String  orderRoute(HttpServletRequest request){
        String  result = BaseData.RESULT_INIT;
        String  orderid = request.getParameter("orderid");
        //判断为修改操作
        if(StringUtils.isNotEmpty(orderid)){
            logger.debug("获取id,判断为修改订单orderid"+orderid);
            result = this.modify(request);
        }else{
            String  qorderid = request.getParameter("qorderid");
            if(StringUtils.isNotEmpty(qorderid)){
                logger.debug("获取id,判断为查询订单orderid"+qorderid);
                result = this.queryOrder(request);
            }else{
                logger.debug("获取id,判断为创建订单orderid"+qorderid);
                try{
                    result = this.createOrder(request);
                }catch(Exception e){
                    result = BaseData.RESULT_EXCEPTION;  //创建订单出现异常
                    logger.debug("获取id,判断为创建订单orderid,"+qorderid +"创建订单出现异常");
                    e.printStackTrace();
                }
            }

        }

        return  result;
    }

    private String getParam(HttpServletRequest request,String key) {
        if (null == request || StringUtils.isEmpty(key)) {
            return null;
        }


        //token
        Cookie[] cookies = request.getCookies();

        if (null == cookies) {
            logger.debug("获取cookie参数:"+key+",错误信息:获取cookies失败");
        } else {
            //
            String result = null;
            for (int i = 0; i < cookies.length; i++) {
                logger.debug("name :" + cookies[i].getName() + result);
                if (key.equals(cookies[i].getName())) {
                    result = cookies[i].getValue();
                    logger.debug("获取cookie参数:" + key + " : " + result);
                    return result;
                }
            }
        }
        //
        String result = (String) request.getParameter(key);
        logger.debug("获取get参数:"+key+" : "+result);

        return result;
    }

    public String createOrder(HttpServletRequest request) throws IOException {
        logger.debug("准备创建订单--执行 [OrderHandle : createOrder] ");
        //
        String debug = UrlUtil.getValue(BaseData.debug);

        String ordertype = request.getParameter("ordertype");

        if ("true".equals(debug)) {
            ordertype = "7";
        }
        if (StringUtils.isEmpty(ordertype)) {
            logger.debug("准备创建订单--执行 [OrderHandle : createOrder] 出现错误,错误信息:ordertype为空");
            return BaseData.RESULT_BAD;
        }

        String timeintervalstart = "";
        String timeintervalend = "";
        String timeintervaltype = "";
        //预付
        if (OrderTypenum.YF.getId().equals(ordertype)) {
            logger.debug("准备创建订单--执行 [OrderHandle : createOrder],当前订单为预付订单");
            timeintervalstart = request.getParameter("timeintervalstart");

            if ("true".equals(debug)) {
                timeintervalstart = "7";
            }
            if ("undefined".equals(timeintervalstart)) {
                timeintervalstart = null;
            }

            //
            timeintervalend = request.getParameter("timeintervalend");

            if ("true".equals(debug)) {
                timeintervalend = "19";
            }
            if ("undefined".equals(timeintervalend)) {
                timeintervalend = null;
            }

            timeintervaltype = request.getParameter("timeintervaltype");

            if ("true".equals(debug)) {
                timeintervaltype = "1";
            }
            if (StringUtils.isEmpty(timeintervaltype)) {
                logger.debug("准备创建订单--执行 [OrderHandle : createOrder],当前订单为预付订单,出现错误,错误信息:timeintervaltype为空");
                return BaseData.RESULT_BAD;
            }

        }

        //
        String startdateday = request.getParameter("startdateday");

        if ("true".equals(debug)) {
            startdateday = "20160116";
        }
        if (StringUtils.isEmpty(startdateday)) {
            logger.debug("准备创建订单--执行 [OrderHandle : createOrder],出现错误,错误信息:startdateday为空");
            return BaseData.RESULT_BAD;
        }

        //
        String enddateday = request.getParameter("enddateday");

        if ("true".equals(debug)) {
            enddateday = "20160117";
        }
        if (StringUtils.isEmpty(enddateday)) {
            logger.debug("准备创建订单--执行 [OrderHandle : createOrder],出现错误,错误信息:enddateday为空");
            return BaseData.RESULT_BAD;
        }

        //
        String hotelid = request.getParameter("hotelid");

        if ("true".equals(debug)) {
            hotelid = "2230";
        }
        if (StringUtils.isEmpty(hotelid)) {
            logger.debug("准备创建订单--执行 [OrderHandle : createOrder],出现错误,错误信息:hotelid为空");
            return BaseData.RESULT_BAD;
        }

        //
        String roomtypeid = request.getParameter("roomtypeid");

        if ("true".equals(debug)) {
            roomtypeid = "29885";
        }
        if (StringUtils.isEmpty(roomtypeid)) {
            logger.debug("准备创建订单--执行 [OrderHandle : createOrder],出现错误,错误信息:roomtypeid为空");
            return BaseData.RESULT_BAD;
        }

        //


        //
        String pricetype = request.getParameter("pricetype");

        if ("true".equals(debug)) {
            pricetype = "2";
        }


        //token
        String token = this.getParam(request,"m28");
        logger.debug("准备创建订单--执行 [OrderHandle : createOrder],获取token:"+token);

        if ("true".equals(debug)) {
            token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
        }

        if (StringUtils.isEmpty(token)) {
            logger.debug("准备创建订单--执行 [OrderHandle : createOrder],获取token为空");
            return BaseData.RESULT_BAD;
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
        parmeter.put("ordertype", ordertype);
        parmeter.put("callmethod", CallMethodEnum.WEIXIN.getId());
        parmeter.put("ordermethod", CallMethodEnum.WEIXIN.getId());
        parmeter.put("callversion", "3.5");


        //
        String url = UrlUtil.getValue(BaseData.creatOrderUrl);

        String backStr = "";
        try {
            logger.debug("准备创建订单--执行 [OrderHandle : createOrder], 请求ots,url:"+url);
            logger.debug("准备创建订单--执行 [OrderHandle : createOrder], 请求ots,parmeter:"+JSONObject.toJSONString(parmeter));
            backStr = SmsHttpClient.post(url, parmeter);

            logger.debug("准备创建订单--执行 [OrderHandle : createOrder], 请求ots,出参"+ backStr);

        } catch (Exception e) {
            logger.error("准备创建订单--执行 [OrderHandle : createOrder], 请求ots,出现错误");
            e.printStackTrace();
            return BaseData.RESULT_BAD;
        }


        if (StringUtils.isNotEmpty(backStr)) {
            JSONObject object = this.parseObject(backStr);

            if (!"true".equals(object.getString("success"))) {
                String errormsg = DataHander.checkStringNull(object, "errormsg", "");
                if(StringUtils.isEmpty(errormsg)){
                    errormsg = DataHander.checkStringNull(object, "errmsg", "");
                }
                request.setAttribute("errormsg",errormsg);
                return BaseData.RESULT_EXCEPTION;
            }

            //
            request.setAttribute("orderid", DataHander.checkStringNull(object, "orderid", "0"));
            request.setAttribute("hotelname", DataHander.checkStringNull(object, "hotelname", ""));

            //
            String begintimeOri = DataHander.checkStringNull(object, "begintime", "");
            begintimeOri = DateUtil.getStrFormart(begintimeOri, "yyyyMMhh");

            String endtimeOri = DataHander.checkStringNull(object, "endtime", "");
            endtimeOri = DateUtil.getStrFormart(endtimeOri, "yyyyMMhh");
            try{
                request.setAttribute("begintime", DateUtil.getMonthAndDay(begintimeOri,"yyyyMMhh"));
                request.setAttribute("endtime", DateUtil.getMonthAndDay(endtimeOri,"yyyyMMhh"));
            }catch (Exception  e){
                e.printStackTrace();
            }

            request.setAttribute("orderday", DataHander.checkStringNull(object, "roomorder", "orderday", ""));
            request.setAttribute("roomtypename", DataHander.checkStringNull(object, "roomorder", "roomtypename", ""));
            request.setAttribute("walletcost", DataHander.checkStringNull(object, "walletcost", "0"));
            request.setAttribute("contacts", DataHander.checkStringNull(object, "contacts", ""));
            request.setAttribute("contactsphone", DataHander.checkStringNull(object, "contactsphone", ""));
            request.setAttribute("usermessage", DataHander.checkStringNull(object, "usermessage", ""));
            request.setAttribute("onlinepay", DataHander.checkStringNull(object, "onlinepay", "0"));
            request.setAttribute("price", DataHander.checkStringNull(object, "roomorder", "payprice", "price", "0"));
            request.setAttribute("totalprice", DataHander.checkStringNull(object, "roomorder", "totalprice", "0"));
            request.setAttribute("maxuserwalletcost", DataHander.checkStringNull(object, "maxuserwalletcost", "0"));

            String  timeintervalStr ="";
            String  timeintervalstartStr = DataHander.checkStringNull(object, "timeintervalstart", "");
            String  timeintervalendStr = DataHander.checkStringNull(object, "timeintervalend", "");
            if(StringUtils.isNotEmpty(timeintervalstartStr)){
                timeintervalstartStr = timeintervalstartStr +":00";
            }
            if(StringUtils.isNotEmpty(timeintervalendStr)){
                timeintervalendStr = timeintervalendStr +":00";
            }

            timeintervalStr = timeintervalstartStr + "-" + timeintervalendStr;
            request.setAttribute("timeintervalstart", timeintervalstartStr);
            request.setAttribute("timeintervalend", timeintervalendStr);
            request.setAttribute("timeintervalStr", timeintervalStr);

            String backtimeouttime = DataHander.checkStringNull(object, "timeouttime", "0");
            System.out.println("backtimeouttime: "+backtimeouttime);
            if (!"0".equals(backtimeouttime)) {
                try {
                    System.out.println("backtimeouttime:" + DateUtil.timesBetween(DateUtil.getStringDate("yyyyMMddHHmmss"),backtimeouttime, "yyyyMMddHHmmss"));
                    request.setAttribute("timeouttime", (DateUtil.timesBetween(DateUtil.getStringDate("yyyyMMddHHmmss"),backtimeouttime, "yyyyMMddHHmmss"))*1000);
                } catch (Exception e) {
                    System.out.println("时间处理错误");
                    e.printStackTrace();
                }
            }


            return BaseData.RESULT_ADD_SUCCESS;
        }

        return BaseData.RESULT_UNKNOW;
    }

    public String getUserWXwallet(HttpServletRequest request) {

        logger.debug("开始查询红包总额--执行 [OrderHandle : getUserWXwallet]");

        String debug = UrlUtil.getValue(BaseData.debug);
        //

        String token = this.getParam(request,"m28");
        logger.debug("准备创建订单--执行 [OrderHandle : createOrder],获取token:"+token);

        if ("true".equals(debug)) {
            token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
        }

        if (StringUtils.isEmpty(token)) {
            logger.debug("开始查询红包总额--执行 [OrderHandle : getUserWXwallet] 获取token为空");
            return BaseData.RESULT_BAD;
        }
        HashMap hmap = new HashMap();

        hmap.put("token", token);
        hmap.put("callmethod", CallMethodEnum.WEIXIN.getId());

        //
        String url = UrlUtil.getValue(BaseData.queryWXUserWallet);

        logger.debug("开始查询红包总额--执行 [OrderHandle : getUserWXwallet] url"+url);

        String backStr = SmsHttpClient.post(url, hmap);

        logger.debug("开始查询红包总额--执行 [OrderHandle : getUserWXwallet] 获取红包返回参数:backStr"+backStr);

        if (StringUtils.isEmpty(backStr)) {
            return BaseData.RESULT_BAD;
        }
        JSONObject jso = this.parseObject(backStr);
        if (null == jso) {
            return BaseData.RESULT_BAD;
        }

        request.setAttribute("balance", DataHander.checkStringNull(jso, "balance", "0"));
        if ("true".equals(debug)) {
            request.setAttribute("balance", "99999999");
        }
        return BaseData.RESULT_SUCCESS;
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
        String ordertype = request.getParameter("ordertype");

        logger.debug("修改订单开始.orderId:" + orderId);

        if (StringUtils.isEmpty(orderId)) {
            request.setAttribute("errormsg","无状态的非法请求");
            return BaseData.RESULT_BAD;
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

        logger.debug("修改订单开始.userName:" + userName + "userMobile:" + userMobile + "orderId:" + orderId);

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userMobile) || StringUtils.isEmpty(orderId)) {
            logger.debug("修改订单开始.userName:" + userName + "userMobile:" + userMobile + "orderId:" + orderId+"参数不正确");
            request.setAttribute("errormsg","有状态的非法请求");
            return BaseData.RESULT_BAD;
        }
        //

        HashMap parmeter = new HashMap();
        parmeter.put("checkinuser", _checkinuser);
        parmeter.put("orderid", orderId);
        if (null != walletCost && new BigDecimal(walletCost).compareTo(BigDecimal.ZERO) > 0) {
            parmeter.put("walletcost", walletCost);
            parmeter.put("isuselewallet", "T");
        }

        parmeter.put("ordertype", ordertype);
        parmeter.put("contacts", userName);
        parmeter.put("contactsphone", userMobile);

        //token
        String token = this.getParam(request,"m28");
        logger.debug("准备创建订单--执行 [OrderHandle : createOrder],获取token:"+token);

        if ("true".equals(debug)) {
            token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
        }

        if (StringUtils.isEmpty(token)) {
            logger.debug("修改订单开始.获取token失败");
            request.setAttribute("errormsg","修改订单,无用户身份");
            return BaseData.RESULT_BAD;
        }

        parmeter.put("token", token);
        parmeter.put("callmethod", CallMethodEnum.WEIXIN.getId());
        parmeter.put("ordermethod", CallMethodEnum.WEIXIN.getId());

        logger.debug("准备创建订单--执行 [OrderHandle : createOrder],请求参数:"+JSONObject.toJSON(parmeter));
        //
        String backStr = SmsHttpClient.post(UrlUtil.getValue(BaseData.modifyOrderUrl), parmeter);
        logger.debug("修改订单开始请求backStr:" + backStr);

        if (StringUtils.isEmpty(backStr)) {
            request.setAttribute("errormsg","修改,无远程结果");
            return BaseData.RESULT_BAD;
        }

        JSONObject jsonOrder = JSONObject.parseObject(backStr);
        if (!"true".equals(jsonOrder.getString("success"))) {
            String errormsg = DataHander.checkStringNull(jsonOrder,"errormsg","");
            if(StringUtils.isEmpty(errormsg)){
                errormsg = DataHander.checkStringNull(jsonOrder,"errmsg", "");
            }
            request.setAttribute("errormsg", errormsg);
            return BaseData.RESULT_EXCEPTION;
        }

        //
        request.setAttribute("orderid", orderId);
        request.setAttribute("ordertype", ordertype);
        return BaseData.RESULT_EDIT_SUCCESS;
    }

    public String pay(HttpServletRequest request, HttpServletResponse response) {

        String orderid = (String) request.getAttribute("orderid");
        String ordertype = (String) request.getAttribute("ordertype");

        logger.debug("支付订单开始请求orderid:" + orderid  +"ordertype:" + ordertype);

        if (null == orderid || null == ordertype) {
            logger.debug("支付订单开始请求orderid:" + orderid  +"ordertype:" + ordertype+"出现错误");
            request.setAttribute("errormsg","非法的支付请求");
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
        String token = this.getParam(request,"m28");
        logger.debug("准备创建订单--执行 [OrderHandle : createOrder],获取token:"+token);
        String openid = this.getParam(request,"m29");
        logger.debug("准备创建订单--执行 [OrderHandle : createOrder],openid:"+openid);
        if ("true".equals(debug)) {
            token = "4d2d9a6b-bf8d-46a8-b883-132bdb4321e7";
            openid = "oOcMFs30gdxVDvCj9QI15SdM8G2A";
        }

        logger.debug("修改订单开始请求token:" + token);
        logger.debug("修改订单开始请求openid:" + openid);

        if (StringUtils.isEmpty(token)) {
            request.setAttribute("errormsg","支付,无用户身份");
            return "error";
        }

        parmeterPay.put("token", token);
        parmeterPay.put("openid",openid);

        String backStr = SmsHttpClient.post(UrlUtil.getValue(BaseData.createPayUrl), parmeterPay);
        logger.debug("修改订单开始请求backStr:" + backStr);

        if (StringUtils.isEmpty(backStr)) {
            request.setAttribute("errormsg","支付,无远程结果");
            return "error";
        }
        JSONObject jsonPay = JSONObject.parseObject(backStr);
        if (!"true".equals(jsonPay.getString("success"))) {
            request.setAttribute("errormsg", DataHander.checkStringNull(jsonPay, "errmsg", ""));
            return "error";
        } else {
            if (OrderTypenum.DF.getId().equals(ordertype)) {
                try {
                    response.sendRedirect(UrlUtil.getValue(BaseData.orderDetailUrl) + orderid);
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                request.setAttribute("orderDetailUrl", UrlUtil.getValue(BaseData.orderDetailUrl) + orderid);
                return "redirect";
            }
            if (!jsonPay.containsKey("weinxinpay")) {
                request.setAttribute("errormsg","无微信支付信息");
                return "error";
            }
            JSONObject json = jsonPay.getJSONObject("weinxinpay");
            String appid = json.getString("appid");
            String noncestr = json.getString("noncestr");
            String packagevalue = json.getString("packagevalue");
            String prepayid = json.getString("prepayid");
            String timestamp = json.getString("timestamp");
            String key = PropKit.get("weixin.key");
            String sign = this.getSign(appid, noncestr, prepayid, timestamp, key);

            //appid=wxf5b5e87a6a0fde94&noncestr=123&package=WAP
            // &prepayid=wx201412101630480281750c890475924233&sign=53D411FB74FE0B0C79CC94F2AB0E2333&timestamp=1417511263
//            StringBuilder stringBuilder = new StringBuilder()
//                    .append("appId=").append(appid)
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


    public  String  queryOrder(HttpServletRequest request){
        String  qorderid = request.getParameter("qorderid");
        logger.debug("查询订单开始请求orderid:" + qorderid);
        if(StringUtils.isEmpty(qorderid)){
            logger.error("查询订单开始请求orderid:" + qorderid);
            return BaseData.RESULT_BAD;
        }

        Cookie[] cookies = request.getCookies();


        String token = this.getParam(request,"28");

        if (StringUtils.isEmpty(token)) {
            logger.error("查询订单开始请求orderid:" + qorderid+"获取token失败");
            return  BaseData.RESULT_BAD;
        }
        HashMap  hm = new HashMap();

        hm.put("token",token);
        hm.put("orderid",qorderid);

        String backStr = SmsHttpClient.post(UrlUtil.getValue(UrlUtil.getValue(BaseData.queryOrderUrl)), hm);
        if (StringUtils.isEmpty(backStr)) {
            logger.error("查询订单开始请求orderid:" + qorderid);
            return BaseData.RESULT_BAD;
        }

        JSONObject object = JSONObject.parseObject(backStr);
        if (!"true".equals(object.getString("success"))) {
            request.setAttribute("errormsg", DataHander.checkStringNull(object, "errmsg", ""));
            logger.error("查询订单开始请求orderid:" + qorderid+"查询订单数量为空");
            return   BaseData.RESULT_EXCEPTION;
        } else {
            JSONArray jsa = object.getJSONArray("order");
            if(null==jsa||jsa.size()==0){
                logger.error("查询订单开始请求orderid:" + qorderid+"查询订单数量为空");
                return BaseData.RESULT_BAD;
            }

            //
            request.setAttribute("orderid", DataHander.checkStringNull(object,"order", "orderid", "0"));
            request.setAttribute("hotelname", DataHander.checkStringNull(object,"order", "hotelname", ""));

            //
            String begintimeOri = DataHander.checkStringNull(object,"order", "begintime", "");
            begintimeOri = DateUtil.getStrFormart(begintimeOri, "yyyyMMhh");

            String endtimeOri = DataHander.checkStringNull(object,"order", "endtime", "");
            endtimeOri = DateUtil.getStrFormart(endtimeOri, "yyyyMMhh");

            try{
                request.setAttribute("begintime", DateUtil.getMonthAndDay(begintimeOri,"yyyyMMhh"));
                request.setAttribute("endtime", DateUtil.getMonthAndDay(endtimeOri,"yyyyMMhh"));
            }catch (Exception  e){
                e.printStackTrace();
            }

            request.setAttribute("orderday", DataHander.checkStringNull(object, "roomorder", "orderday", ""));
            request.setAttribute("roomtypename", DataHander.checkStringNull(object,"order", "roomorder", "roomtypename", ""));
            request.setAttribute("walletcost", DataHander.checkStringNull(object, "order","walletcost", "0"));
            request.setAttribute("contacts", DataHander.checkStringNull(object,"order", "contacts", ""));
            request.setAttribute("contactsphone", DataHander.checkStringNull(object,"order","contactsphone", ""));
            request.setAttribute("usermessage", DataHander.checkStringNull(object,"order", "usermessage", ""));
            request.setAttribute("onlinepay", DataHander.checkStringNull(object,"order", "onlinepay", "0"));
            request.setAttribute("price", DataHander.checkStringNull(object,"order",  "price", "0"));
            request.setAttribute("totalprice", DataHander.checkStringNull(object,"order","totalprice", "0"));
            request.setAttribute("maxuserwalletcost", DataHander.checkStringNull(object,"order", "maxuserwalletcost", "0"));
            String  timeintervalstartStr  = DataHander.checkStringNull(object, "order","timeintervalstart", "");
            String  timeintervalendStr = DataHander.checkStringNull(object,"order", "timeintervalend", "");

            String   timeintervalStr = "";
            if(StringUtils.isNotEmpty(timeintervalstartStr)){
                timeintervalstartStr = timeintervalstartStr +":00";
            }
            if(StringUtils.isNotEmpty(timeintervalendStr)){
                timeintervalendStr = timeintervalendStr +":00";
            }
            request.setAttribute("timeintervalstart", timeintervalstartStr);
            request.setAttribute("timeintervalend", timeintervalendStr);
            timeintervalStr = timeintervalstartStr + "-" + timeintervalendStr;

            String backtimeouttime = DataHander.checkStringNull(object, "timeouttime", "0");
            if (!"0".equals(backtimeouttime)) {
                try {
                    request.setAttribute("timeouttime", (DateUtil.timesBetween(DateUtil.getStringDate("yyyyMMddHHmmss"),backtimeouttime, "yyyyMMddHHmmss"))*1000);
                } catch (Exception e) {
                    logger.error("查询订单开始请求orderid:" + qorderid+"时间处理错误");
                    e.printStackTrace();
                }
            }
            return  BaseData.RESULT_QUERY_SUCCESS;

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
