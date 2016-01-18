<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.mk.order.handle.OrderHandle" %>

<%

    boolean bl = true;
    OrderHandle ho = new OrderHandle();

    //更新
    String m = ho.modify(request);
    System.out.println("m:"+m);
    //是否支付
    if ("error".equals(m)) {
        request.getRequestDispatcher("500.jsp").forward(request, response);
        return;
    }
    if ("success".equals(m)) {
        request.getRequestDispatcher("pay.jsp").forward(request, response);
        return;
    }

    //创建订单
    JSONObject jsonObject = ho.createOrder(request);
    if (null == jsonObject) {
        bl = false;
    }
    //红包
    ho.getUserWXwallet(request);

    Long random = new Random().nextLong();
    request.setAttribute("random",random);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="styles/normalize.css"/>
    <link rel="stylesheet" href="styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="styles/pay.css?${random}"/>
    <title>在线支付</title>
</head>
<body>

<header class="header">
    <a class="back-icon"  href="javascript:;"></a> <span class="title brown">支付订单</span>
</header>
<%
    if (bl) {
%>
<div class="main">

    <div class="t-tips">
        <i class="icon timer-icon"></i>请在<span class="yellow">15分钟</span>确认订单及付款!距结束<em
            class="p-timer js_time_min">02</em>分<em class="p-timer js_time_ss">29</em>秒
    </div>
    <section class="o-d-info ">
        <div class="h-info bg-white brown">
            <div class="h-name">${hotelname}</div>
            <div class="o-date">
                <span>入住：${begintime}</span> <span>离店：${endtime}</span> <span>共${orderday}天</span>
            </div>
            <div class="h-type row">
                <div class="col text-left">${roomtypename}</div>
                <div class="col text-right">
                    房款：<span class="orange">${price}</span>
                </div>
            </div>
        </div>
        <form id="userInfo_form" method="post" name="userInfo_form">
            <div class="h-person bg-white">
                <ul class="p-items">
                    <li>
                        <span class="item-left">入住人</span>
                        <input type="text" name="username" class="i-p-input js_order_concact" placeholder="入住人" value="${contacts}"/>
                    </li>
                    <li>
                        <span class="item-left">手机号</span>
                        <input type="tel" name="usermobile" class="i-p-input js_order_phone" placeholder="联系电话" value="${contactsphone}"/>
                    </li>
                </ul>
            </div>
            <div class="discount-items">
                <div class="row discount-title d-gray">
                    <div class="col">使用红包</div>
                    <div class="col text-right  js_order_totalWallet">红包最多可抵${maxuserwalletcost}元
                    </div>
                </div>
                <div class="u-p-item ">
                    <div class="use-package">
                        使用红包<span class="u-p-tip">（红包余额：￥${balance}）</span>
                    </div>
                    <input type="tel" name="walletcost" class="u-p-input" id="user-wallet"/>
                </div>
            </div>
            <input type="hidden" name="orderid"  value="${orderid}"/>
            <input type="hidden" name="ordertype"  value="1"/>
        </form>
        <div class="pay-items">
            <div class="c-title">选择支付方式</div>
            <ul class="pay-item bg-white">
                <li>
                    <i class="icon wx-icon"></i>
                    <span>微信支付</span>
                    <span class="u-p-tip gray"></span> 
                    <a href="javascript:;" class="icon check-icon on js_pay_check"></a>
                </li>
            </ul>
            <div class="pay-tips">
                <span>温馨提示：</span>
                <div>${usermessage}</div>
            </div>
        </div>
    </section>
</div>
<footer class="footer bg-white row" style="width:100%;">
    <div class="col">
        还需支付：<span id="all_cost" class="orange ">
            ￥${onlinepay}</span>
    </div>
    <div class="col text-right">
        <a href="javascript:;" id="show-detail">
            明细<i class="icon up-icon"></i>
        </a>
        <a href="javascript:;" class="order-btn bg-orange white text-center " id="btn-submit">提交订单</a>
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
                <div class="col text-right">
                    ￥<span id="wallet-layer">${walletcost}</span>
                </div>
            </div>
        </li>
    </ul>
</div>
<%
} else {
%>
<div>
    <h1>请求不合法</h1>
</div>
<%} %>

<script src="scripts/zepto.min.js?v=3"></script>
<script src="scripts/countdown.js?v=1"></script>
<script>
    $(function () {
        var minUserCost = parseInt(Math.min('${balance}','${maxuserwalletcost}'));
        var allCost = parseInt('${onlinepay}')
        var $userWallet = $("#user-wallet");
        var $walletLayer = $("#wallet-layer");
        var $allCost = $("#all_cost");
        
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
        });

        $('#show-detail').tap(function (event) {
            $('.js_slide_layer').toggleClass('on');
        });
        
        $('.js_pay_check').tap(function (event) {
            $(this).toggleClass('on');
        });
        
        $userWallet.on('change',function(){
            var val = $(this).val()||'0';
            val = Math.abs(parseInt(val));
            if(val>minUserCost){
                val = minUserCost;
            }
            $(this).val(val);
            $allCost.text('￥'+(allCost - val));
            $walletLayer.text(val);
        });
        
        $userWallet.val(minUserCost).change();
        
        $('#btn-submit').tap(function (event) {
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
            var userWallet = $userWallet.val();
            if (userWallet > minUserCost) {
                alert("红包金额超限!");
                return;
            }

            $('#userInfo_form').submit();

            //this.onBridgeReady(i.weinxinpay.appid, i.weinxinpay.timestamp, i.weinxinpay.noncestr, i.weinxinpay.packagevalue, 'MD5', i.weinxinpay.sign);

        });
        
    });

</script>
</body>
</html>