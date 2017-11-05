<%--
  Created by IntelliJ IDEA.
  User: zhengguo
  Date: 2017/5/28
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>找回密码</title>
<meta name="keywords" content="班级博客" />
<meta name="description" content="找回密码" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/reset_pass.css"/>
    <script src="${pageContext.request.contextPath}/src/lib/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/src/lib/layer-v2.2/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath}/src/scripts/reset_pass.js"></script>
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
      <p class="p_underline"><a href="${pageContext.request.contextPath}/jsps/background/register.jsp">注册账号</a><span style="padding-left:500px">
      <a href="${pageContext.request.contextPath}/jsps/background/login.jsp">已有账号，直接登录</a></span></p>
        <p style="margin-top:50px;"><input type="text" placeholder="请输入手机号" name="phone" id="phone" class-id="txtBox" class="init"></p>
        <p><input type="password" name="password" id="password" placeholder="请输入密码（6-16位英文或数字）" class-id="txtBox" class="init"></p>
        <p><input type="password" name="conf" id="conf" placeholder="请确认密码" class-id="txtBox" class="init"></p>
        <p><input type="submit" name="subBut" id="subBut" value="重置密码" class="btn"></p>
    </div>
  </div>
</body>
</html>
