<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <title>在线支付</title>
    <script type="application/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="application/javascript">
        wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wx2d9d3daf15496f60', // 必填，公众号的唯一标识
            timestamp: 1452953483, // 必填，生成签名的时间戳
            nonceStr: 'wkiashis1k9wvkp1olupzyc6926no2di', // 必填，生成签名的随机串
            signature: '78927F590EBEEB5343569F542E7E0E6D',// 必填，签名，见附录1
            jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });

        wx.ready(function(){

        });
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