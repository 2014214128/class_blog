<%--
  Created by IntelliJ IDEA.
  User: zhengguo
  Date: 2017/5/28
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<meta name="keywords" content="用户登录" />
<meta name="description" content="" />
<link href="${pageContext.request.contextPath}/src/css/login.css" rel="stylesheet" type="text/css">

<script src="${pageContext.request.contextPath}/src/lib/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/src/scripts/login.js"></script>
<script src="${pageContext.request.contextPath}/src/lib/layer-v2.2/layer/layer.js"></script>
</head>
<script>
    window.onload = function() {
        document.onkeydown = function(e) {
            e = e || window.event;
            var w = e.keyCode || e.which;
            if (w == 13) {
                document.getElementById("submitButton").click();
            }
        }
    }
</script>
<body>
	<div id="global">
	<div id="top"><img src="${pageContext.request.contextPath}/src/img/login-top.jpg"></div>
	<div>
		<div id="first">
			<p class="Title">我们是信息一班，一群拥有梦想并勇于追求梦想的人，坚信总有一天，自己能像雄鹰一样遨游于天际。如果喜欢我们，就请加入我们吧!</p>
		</div>
		<div id="login">
		 	    <p class="onTitle">登陆</p>
			    <p><input type="text" class="textbox" name="user" id="user" placeholder="请输入手机号或邮箱"></p>
			    <p><input type="password" class="password" name="password" id="password" placeholder="请输入密码"></p>
			    <p><input type="button" value="用户登陆" class="submitButton" id="submitButton"></p>
			    <p>
			    	<input type="checkbox" name="chkSave" id="chkSave" >
					保存密码？
					<a href="${pageContext.request.contextPath}/jsps/background/reset_pass.jsp">忘记密码</a>
				</p>
			    <p><input type="button" value="注册账号" id="registerButton"></p>
		</div>
	</div>
	</div>
</body>
</html>
