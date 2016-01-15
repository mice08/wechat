<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <title>在线支付</title>

    <script>
        function onBridgeReady() {
            WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId": "${}",     //公众号名称，由商户传入
                        "timeStamp": "${}",         //时间戳，自1970年以来的秒数
                        "nonceStr": "${}", //随机串
                        "package": "prepay_id=${}",
                        "signType": "MD5",         //微信签名方式：
                        "paySign": "${}" //微信签名
                    },
                    function (res) {
                        if (res.err_msg == "get_brand_wcpay_request：ok") {
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
    在线支付页面
</header>
</body>
</html>