<%@ page import="com.mk.order.handle.OrderHandle" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <title>在线支付</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <link rel="stylesheet" href="../src/commons/styles/normalize.css">
    <link rel="stylesheet" href="../src/commons/styles/common.css">
    <link rel="stylesheet" href="../src/commons/styles/redPackage.css">
    <script src="scripts/zepto.min.js?v=3"></script>

    <%

        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);

        //
        OrderHandle ho = new OrderHandle();
        String m = ho.pay(request,response);
        if ("error".equals(m)) {
            request.getRequestDispatcher("500.jsp").forward(request, response);
            return;
        }
        if ("redirect".equals(m)) {
    //        request.getRequestDispatcher("success.jsp").forward(request, response);
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
                        console.log(res);
                        alert(res.err_msg + '----');
                        debugger;
                        if (res.err_msg == "get_brand_wcpay_request:ok") {
                           show();
                        }else{
                            //返回跳转到酒店详情页面
                            alert("支付失败");
                            window.location.href="${hotelDetailUrl}";
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
        function show(){
            console.log('succ');
            debugger;

            $('.js_redPackage_layer').show();
        }
        function hide(){
            $('.js_redPackage_layer').hide();
        }
    </script>
</head>
<body>

    <section class="redpackageCoverLayer js_redPackage_layer hide">
        <div class="redpackageCoverLayer-mask"></div>
        <div class="redpackageCoverLayer-container">
            <section>
                <img src="images/redpackage_bg.png">
                <div>
                    <p>恭喜您</p>
                    <p>获得眯客分享红包</p>
                    <footer class="row ">
                        <a href="/wallet.html#/walletshare?orderid=${orderid}" class="col">分享</a>
                        <a href="${hotelDetailUrl}" class="col">取消</a>
                    </footer>

                </div>
            </section>
        </div>
    </section>
</body>
</html>