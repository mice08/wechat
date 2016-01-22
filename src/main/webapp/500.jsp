<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link rel="stylesheet" href="styles/normalize.css"/>
    <link rel="stylesheet" href="styles/common.css"/>
    <link rel="stylesheet" type="text/css" href="styles/pay.css"/>
    <title>500</title>

    <script type="text/javascript">
        function showMessage(message) {
            alert(message, {
                clickFn: function (e) {
                    e.preventDefault();
                    if (e.target.tagName == 'BUTTON') {
                        window.location.href = "/index.jsp";
                    }
                }
            })
        }
    </script>
</head>
<body>

<header class="header">
    <a class="back-icon" href="javascript:;"></a>
    <span class="title brown">支付订单</span></header>
<div>
    <h1>请求错误</h1>
    <%
        Object obj = request.getAttribute("errmsg");
        String msg = "请重试.";
        if (null != obj) {
            msg = obj.toString();
        }
    %>
    <script>
        showMessage("<%=msg%>");
    </script>
</div>

</body>
</html>