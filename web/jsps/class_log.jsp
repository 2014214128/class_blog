<%--
  Created by IntelliJ IDEA.
  User: zhengguo
  Date: 2017/6/14
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>班级日记</title>
    <meta name="keywords" content="班级日记" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/study.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/jquery1.42.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/jquery.SuperSlide.2.1.1.js"></script>
</head>
<body>
<jsp:include page="audio.jsp"/>
<div id="header">
    <h1>14-信1班级博客
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <a href="${pageContext.request.contextPath}/jsps/background/homepage/publishArticle.jsp" style="font-size: 17px;float: right;color: #664666">欢迎您！${user.loginname}</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/jsps/background/register.jsp" style="font-size: 17px;float: right;color: #664666">注册</a>
                <a href="${pageContext.request.contextPath}/jsps/background/login.jsp" style="font-size: 17px;float: right;color: #664666">登录|</a>
            </c:otherwise>
        </c:choose>
    </h1>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;梦想之于人，犹翅膀之于鸟，梦想是飞翔的翅膀。</p>
</div>
<div id="nav">
    <ul>
        <li><a href="${pageContext.request.contextPath}/showPage?pageNow=1">首页</a></li>
        <li><a href="${pageContext.request.contextPath}/jsps/about_us.jsp">关于我们</a></li>
        <li>
            <a href="
            <c:url value="/show?type=life">
                <c:param name="pageNow" value="1">1</c:param>
            </c:url>
            ">生活感悟</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/jsps/class_log.jsp" class="first-a">班级日记</a></li>
        <li>
            <a href="
            <c:url value="/show?type=study">
                <c:param name="pageNow" value="1">1</c:param>
            </c:url>
            ">学无止境</a>
        <li><a href="${pageContext.request.contextPath}/jsps/whispering_wall.jsp">回音墙</a></li>
        <div class="clear"></div>
    </ul>
</div>
<div id="content">
    <div id="top">
        <ul>
            <li><img src="${pageContext.request.contextPath}/src/images/weizi.png"></li>
            <li>当前位置：</li>
            <li><a href="${pageContext.request.contextPath}/showPage?pageNow=1">首页</a></li>
            <li>></li>
            <li><font color="#F00E71">班级日记</font></li>
        </ul>
    </div>
</div>
<div id="body">
    <ul class="say_box">
        <div class="sy">
            <p> 有一种能力叫坚持。</p>
        </div>
        <span class="dateview">2017-7-6</span>
    </ul>
    <ul class="say_box">
        <div class="sy">
            <p> 那个可以任意挥霍的年纪，人们叫它'青春'。</p>
        </div>
        <span class="dateview">2017-2-11</span>
    </ul>
    <ul class="say_box">
        <div class="sy">
            <p> 过去就像回形针，把青春一页页的固定，然后变成了一本不被出版的书。</p>
        </div>
        <span class="dateview">2017-2-11</span>
    </ul>
    <ul class="say_box">
        <div class="sy">
            <p> 时间好象一把尺子，它能衡量奋斗者前进的进程。
                时间如同一架天平，它能称量奋斗者成果的重量；
                时间就像一把皮鞭，它能鞭策我们追赶人生的目标。时间犹如一面战鼓，它能激励我们加快前进的脚步。</p>
        </div>
        <span class="dateview">2017-2-11</span>
    </ul>
    <ul class="say_box">
        <div class="sy">
            <p>青春，一半明媚，一半忧伤。
                它是一本惊天地泣鬼神的着作，而我们却读的太匆忙。
                于不经意间，青春的书籍悄然合上，以至于我们要重新研读它时，
                却发现青春的字迹早已落满尘埃，模糊不清。</p>
        </div>
        <span class="dateview">2017-2-11</span>
    </ul>
    <div class="footer-wrap">
        <p style="text-align: center;"><span>本网站开发纯属兴趣爱好，不用于任何商业活动，如果本站部分内容侵犯您的权益，请您告知，站长会立即处理</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Designed by：郑国 黄健勇 李玺</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Powered by：合工大14级信计专业</span></p>
        <p style="text-align: center;margin-top: 10px;margin-bottom: 20px"><span>备案号：<strong>皖ICP备17015032号</strong></span></p>
    </div>
</div>
<!--body结束-->
</body>
</html>

