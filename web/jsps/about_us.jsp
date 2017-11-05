<%--
  Created by IntelliJ IDEA.
  User: zhengguo
  Date: 2017/6/14
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>关于我们</title>
    <meta name="keywords" content="关于我们" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/about_us.css">
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
        <li><a href="${pageContext.request.contextPath}/jsps/about_us.jsp" class="first-a">关于我们</a></li>
        <li>
            <a href="
            <c:url value="/show?type=life">
                <c:param name="pageNow" value="1">1</c:param>
            </c:url>
            ">生活感悟</a>
        </li>
        <li><a href="${pageContext.request.contextPath}/jsps/class_log.jsp">班级日记</a></li>
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
            <li><font color="#F00E71">关于我们</font></li>
        </ul>
    </div>
    <div id="body">
        <div class="body-content-top">
            <img src="${pageContext.request.contextPath}/src/img/about_us-1.jpg" width="300" height="400">

            <div class="body-content-top-text">
                <ul>
                    <li>
                        <h2 class="title">
                            一、关于我们
                        </h2>
                        <p class="text">
                            1.我们是合肥工业大学数学学院信息与计算科学14-1班的成员
                        </p>
                        <p class="text">
                            2.我们每个人都是由陌生到熟悉，由小心的交流到坦荡的包容，我们的集体经历的近两年的日丽风雨，已经凝成了一股绳，变成了一个家，个个都是这个家的主人。没有风雨就无谓朗空，没有失败，就无言成功。
                        </p>
                    </li>
                    <li>
                        <br><h2 class="title">
                        二、关于梦想
                    </h2>
                        <p class="text">
                            我们每个人都拥有属于自己的梦想，并不断地为之而努力奋斗。坚信总有一天自己能展翅翱翔。
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        <!--body-content-top结束-->
        <div body-content-text>
            <p class="text">
                在我们认为：不管学习什么东西，一定要有属于自己的“学习方法”和善于“对事物的总结”。
            </p>
            <p class="text">
                俗话说的好：“方向不对，努力白费！”很多人都是盲目的去学习，导致花了大量的时间，在学习一些没有用处的东西，到头来什么都没学会，因此会打击自己的学习欲望，也会让自己处于迷茫当中...一个优秀的传道授业者“不单单传授的是知识，更多传授的是思维和方法”。---授人以鱼不如授人以渔
            </p>
            <p class="text">
                其实，人的一生中都是在不断的学习，不管是技术也好，做人也罢！我觉得都应该以一颗“谦卑的心态来学习，秉持利他之心来帮助他人。”
            </p>
            <p class="text">
                人就得学会感恩，滴水之恩应当涌泉相报。感谢那些曾经帮助过我的人，因为有你们我才会变得如此的优秀。
            </p>
        </div>
    </div>
    <div class="footer-wrap">
        <p style="text-align: center;"><span>本网站开发纯属兴趣爱好，不用于任何商业活动，如果本站部分内容侵犯您的权益，请您告知，站长会立即处理</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Designed by：郑国 黄健勇 李玺</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Powered by：合工大14级信计专业</span></p>
        <p style="text-align: center;margin-top: 10px;margin-bottom: 20px"><span>备案号：<strong>皖ICP备17015032号</strong></span></p>
    </div>
</div>
</body>
</html>

