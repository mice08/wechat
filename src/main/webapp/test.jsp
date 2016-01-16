<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <title>在线支付</title>
    <script>
        function onBridgeReady() {
            
            WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId": "wx2d9d3daf15496f60",     //公众号名称，由商户传入
                        "timeStamp": "1452953483",         //时间戳，自1970年以来的秒数
                        "nonceStr": "wkiashis1k9wvkp1olupzyc6926no2di", //随机串
                        "package": "prepay_id=wx201601162211237109b41a760268140425",
                        "signType": "MD5",         //微信签名方式：
                        "paySign": "78927F590EBEEB5343569F542E7E0E6D" //微信签名
                    },
                    function (res) {
                        if (res.err_msg == "get_brand_wcpay_request：ok") {
                            //window.location("http://${orderDetailUrl}/${orderid}");
                            alert("test11");
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
    在线支付页面<br/>
    在线支付页面<br/>
    "appId": "wx83cc02790df41a2b", //公众号名称，由商户传入<br/>
    "timeStamp": "1452940678", //时间戳，自1970年以来的秒数<br/>
    "nonceStr": "ow542c8h8jpdj8678sgelmex8lkkh3j5", //随机串<br/>
    "package": "prepay_id=wx201601161837584aec88632e0174281463",<br/>
    "signType": "MD5", //微信签名方式：<br/>
    "paySign": "5CDD866FF124E5241CC6FAF8746E84C0" //微信签名<br/>
    url:<a href="weixin://wap/pay?appid%3Dwx83cc02790df41a2b%26noncestr%3Dow542c8h8jpdj8678sgelmex8lkkh3j5%26package%3DWAP%26prepayid%3Dwx201601161837584aec88632e0174281463%26sign%3D5CDD866FF124E5241CC6FAF8746E84C0%26timestamp%3D1452940678">weixin://wap/pay?appid%3Dwx83cc02790df41a2b%26noncestr%3Dow542c8h8jpdj8678sgelmex8lkkh3j5%26package%3DWAP%26prepayid%3Dwx201601161837584aec88632e0174281463%26sign%3D5CDD866FF124E5241CC6FAF8746E84C0%26timestamp%3D1452940678</a>
</header>
</body>
</html>