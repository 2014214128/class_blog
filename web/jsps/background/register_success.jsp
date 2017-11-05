<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhengguo
  Date: 2017/5/28
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>注册成功</title>
    <meta name="keywords" content="班级博客"/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/register_success.css"/>
    <script src="${pageContext.request.contextPath}/src/lib/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                location.href = "/blog/login";
            });
        })
    </script>
</head>
<body>
<div id="header">
    <h1>班级博客</h1>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;梦想之于人，犹翅膀之于鸟，梦想是飞翔的翅膀。</p>
</div>
<div id="nav">
    <ul>
        <li><a href="#">首页</a></li>
        <li><a href="#">关于我们</a></li>
        <li><a href="#">碎言碎语</a></li>
        <li><a href="#">班级日记</a></li>
        <li><a href="#">相册展示</a></li>
        <li><a href="#">学无止境</a></li>
        <li><a href="#">回音墙</a></li>
        <div class="clear"></div>
    </ul>
</div>
<div id="content">
    <div id="box">
        <p class="p_underline"><a href="${pageContext.request.contextPath}/jsps/background/register.jsp">注册账号</a><span
                style="padding-left:500px">
      <a href="${pageContext.request.contextPath}/jsps/background/login.jsp">已有账号，直接登录</a></span></p>
        <div id="register">
            <img src="${pageContext.request.contextPath}/src/img/success.jpg">
            <h1 style="position: relative;top:108px;"><c:if test="${empty mes}">请前往邮箱进行验证</c:if>
                <c:if test="${not empty mes}">${mes}</c:if>
            </h1>
        </div>
        <p><input type="button" id="btn" value="马上登陆" class="btn"></p>
    </div>
</div>
</body>

</html>