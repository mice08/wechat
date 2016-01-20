<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.mk.common.toolutils.BaseData" %>
<%@ page import="com.mk.order.handle.OrderHandle" %>
<%@ page import="com.mk.common.toolutils.UrlUtil" %>

<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);

    OrderHandle ho = new OrderHandle();
    String  orderid = request.getParameter("orderid");

    String  result =  ho.orderRoute(request);

    if (BaseData.RESULT_EDIT_SUCCESS.equals(result)) {
            request.getRequestDispatcher("pay.jsp").forward(request, response);
            return;
    }
    if (BaseData.RESULT_QUERY_SUCCESS.equals(result)) {
        ho.getUserWXwallet(request);
    }
    if (BaseData.RESULT_ADD_SUCCESS.equals(result)) {
        ho.getUserWXwallet(request);
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="styles/normalize.css"/>
    <link rel="stylesheet" href="styles/common.css"/>
    <link rel="stylesheet" href="styles/myDialog.css"/>
    <link rel="stylesheet" type="text/css" href="styles/pay.css"/>

    <script type="text/javascript" src="scripts/myDialog.js"></script>
    <title>到店支付</title>
</head>
<script type="text/javascript">
    function   showMessage(message){
        alert(message,{
            clickFn: function(e){
                e.preventDefault();
                if(e.target.tagName=='BUTTON'){
                    window.location.href=<%=UrlUtil.getValue(BaseData.wechatMyOrder)%>;
                }
            }
        })
    }
</script>
<body>
    <header class="header">
        <a class="back-icon" href="javascript:;"></a>
        <span class="title brown">支付订单'${timeouttime}'</span>
    </header>
    <%  if(BaseData.RESULT_BAD.equals(result)){ %>
        <div>

            <h1>请求不合法</h1>
        </div>
    <%}else if(BaseData.RESULT_EXCEPTION.endsWith(result)){
        Object obj =request.getAttribute("errormsg");
        String  msg = "请重试.";
        if(null!=obj){
            msg = obj.toString();
        }
    %>
        <script>
            showMessage("<%=msg%>");
        </script>
    <%
        }else if(BaseData.RESULT_QUERY_SUCCESS.equals(result)||(BaseData.RESULT_ADD_SUCCESS.equals(result))){
    %>
    <div class="main">
        <div class="t-tips"><i class="icon timer-icon"></i>请在<span class="yellow">15分钟</span>确认订单!距结束<em class="p-timer">00</em>时<em class="p-timer">00</em>分</div>
        <section class="o-d-info ">
            <div class="h-info bg-white brown">
                <div class="h-name">${hotelname}</div>
                <div class="o-date">
                    <span>${begintime}入住</span>|
                    <span>${endtime}离店</span>|
                    <span>共${orderday}晚</span>
                </div>
                <div class="h-type row">
                    <div class="col text-left">${roomtypename}</div>
                    <div class="col text-right">房款 ￥<span class="orange">${totalprice}</span></div>
                </div>
            </div>
            <form id="userInfo_form" method="post" name="userInfo_form">
                <div class="h-person bg-white">
                    <ul class="p-items">
                        <li><span class="item-left gray-s">入住人</span><input type="text" class="i-p-input js_order_concact gray" placeholder="入住人" name="username" value="${contacts}"></li>
                        <li><span class="item-left gray-s">手机号</span><input type="text" class="i-p-input js_order_phone gray" placeholder="联系电话"  name="usermobile"  value="${contactsphone}"></li>
                    </ul>
                </div>

                <input type="hidden" name="orderid"  value="${orderid}"/>
                <input type="hidden" name="ordertype"  value="2"/>
            </form>
            <div class="pay-items">
                <div class="pay-tips">
                    <span>温馨提示：</span>
                    <div>${usermessage}</div>
                </div>
            </div>
        </section>
    </div>
    <footer class="footer bg-white row row-no-padding" style="width:100%;">
        <div class="col">
            还需支付：<span class="orange f-cost">￥${onlinepay}</span>
        </div>
        <div class="col text-cut text-right">
            <a href="javascript:;" class="js_slideUp gray pay-detail">
                明细<i class="icon up-icon"></i>
            </a>
            <a href="javascript:;" class="order-btn bg-orange white text-center  js_submit_order">提交订单</a>
        </div>
    </footer>
    <div class="mask_layer js_slide_layer"></div>
    <div class="footer_layer bg-white js_slide_layer">
        <ul class="p-items">
            <li>
                <p class="d-gray">房费</p>
                <div class="row row-no-padding gray">
                    <div class="col">${hotelname}</div>
                    <div class="col text-right">￥${totalprice}</div>
                </div>
            </li>
            <li>
                <p class="d-gray">优惠</p>
                <div class="row row-no-padding gray">
                    <div class="col">红包</div>
                    <div class="col text-right">￥${walletcost}</div>
                </div>
            </li>
        </ul>
    </div>
<%
} else {
%>
<div>
    <h1>请求不合法!</h1>
</div>
<%} %>



<script src="scripts/zepto.min.js?v=3"></script>
<script src="scripts/countdown.js?v=2"></script>
<script>
    var  orderid = ${orderid};
    $(function () {
        countdomn.init({
            time: parseInt('${timeouttime}'),
            onStop: function (data) {
                // 倒计时停止时触发
            },
            onChange: function (data) {
                $('.js_time_min').text(data.m);
                $('.js_time_ss').text(data.s);
            }
        });

        $('.back-icon').tap(function(){
            history.go(-1);
        })

        $('.js_slideUp').tap(function (event) {
            slideUp(event);
        });
        $('.js_pay_check').tap(function (event) {
            $(this).toggleClass('on');
        });
        $('.js_submit_order').tap(function (event) {
            var contact = $('.js_order_concact').val();
            if ($.trim(contact).length == 0) {
                alert("请输入联系人。");
                return;
            }
            var phone = $('.js_order_phone').val();
            if ($.trim(phone).length == 0) {
                alert("请输入手机号。");
                return;
            }

            $('#userInfo_form').submit();

            //this.onBridgeReady(i.weinxinpay.appid, i.weinxinpay.timestamp, i.weinxinpay.noncestr, i.weinxinpay.packagevalue, 'MD5', i.weinxinpay.sign);

        });
        var slideUp = function (e) {
            var $target = $(e.target);
            $('.js_slide_layer').toggleClass('on');
            $target.toggleClass('on');
        }

    });

</script>
</body>

</html>