<%@ page import="com.mk.order.handle.OrderHandle" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <title>在线支付</title>
<%

    OrderHandle ho = new OrderHandle();
    String m = ho.pay(request,response);
    if ("error".equals(m)) {
        request.getRequestDispatcher("500.jsp").forward(request, response);
        return;
    }
    if ("redirect".equals(m)) {
        return;
    }
%>
    <script>
        function onBridgeReady() {
            WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId": "${appId}",     //公众号名称，由商户传入
                        "timeStamp": "${timeStamp}",         //时间戳，自1970年以来的秒数
                        "nonceStr": "${nonceStr}", //随机串
                        "package": "prepay_id=${prepayid}",
                        "signType": "MD5",         //微信签名方式：
                        "paySign": "${paySign}" //微信签名
                    },
                    function (res) {
                        if (res.err_msg == "get_brand_wcpay_request：ok") {
                            window.location("http://${orderDetailUrl}/${orderid}");
                        }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                    }
            );
        }
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        } else {
            onBridgeReady();
        }
    </script>
</head>
<body>
<header class="header">
</header>
</body>
</html>