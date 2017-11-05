<%--
  Created by IntelliJ IDEA.
  User: sheeran
  Date: 2017/6/5
  Time: 17:10
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>班级博客主页</title>
    <meta name="keywords" content="班级博客"/>
    <meta name="description" content=""/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/main.js"></script>
    <script src="${pageContext.request.contextPath}/src/lib/jquery-3.2.1.min.js"></script>
</head>
<body>
<%--<audio autoplay="autoplay" hidden = "hidden" loop = "loop" controls="controls">
<source src="${pageContext.request.contextPath}/src/audio/With Me.flac" type="audio/mpeg">
</audio>--%>
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
        <li><a href="${pageContext.request.contextPath}/showPage?pageNow=1" class="first-a">首页</a></li>
        <li><a href="${pageContext.request.contextPath}/jsps/about_us.jsp">关于我们</a></li>
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
<div class="header">
    <div id="banner" class="banner-wrap clearfix">
        <div class="banner-left fl">
            <div id="bannerContent" class="content">
                    <img src="${pageContext.request.contextPath}/src/images/photo/1.jpg" width="480" height="300" alt=""
                         id="image">
            </div>
        </div>
    </div>
    <div class="switch-btn-box">
        <div class="hover-bg"></div>
        <ul id="switchBtn">
            <li>
                <a href="javascript:;">
                    <img src="${pageContext.request.contextPath}/src/images/photo/1.jpg" width="100" height="50"
                         id="image1">
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <img src="${pageContext.request.contextPath}/src/images/photo/2.jpg" width="100" height="50"
                         id="image2">
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <img src="${pageContext.request.contextPath}/src/images/photo/3.jpg" width="100" height="50"
                         id="image3">
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <img src="${pageContext.request.contextPath}/src/images/photo/4.jpg" width="100" height="50"
                         id="image4">
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <img src="${pageContext.request.contextPath}/src/images/photo/5.jpg" width="100" height="50"
                         id="image5">
                </a>
            </li>
        </ul>
    </div>
</div>
<div class="banner-right">
    <p class="banner-right-text">
        毕竟不是作家，写不出那么好的文章。--因为没有丰富阅历和经验！
    </p>
</div>
</div>
<div id="search-content">
    <form class="formWarpper" method="post" action="${pageContext.request.contextPath}/article?type=search&pageNow=1">
        <div class="formField clearfix">
            <input type="text" required="" name="keyword" placeholder="请输入您要查找的内容" class="search">
            <input type="submit" class="btn" value="搜索">
        </div>
    </form>
</div>
<div id="content">
    <div id="c_left">
        <div class="s_tuijian">
            <h2>文章<span>推荐</span></h2>
        </div>
        <div class="content_text">
            <div class="content_text">
                <ul>
                    <li>
                        <c:forEach items="${requestScope.pageBean.data}" var="article">
                            <div class="secondContent">
                                <div class="text-1">
                                    <a href="
                                    <c:url value="/show?type=show">
                                          <c:param name="article_id" value="${article.id}">${article.id}</c:param>
                                   </c:url>
                                "> <img src="${article.picture.url}" width="200" height="160"></a>
                                </div>
                                <div class="text-2">
                                    <div class="wz">
                                        <h3><a href="
                                    <c:url value="/show?type=show">
                                          <c:param name="article_id" value="${article.id}">${article.id}</c:param>
                                   </c:url>
                                ">${article.title}</a></h3>
                                    </div>
                                    <div class="text-2-first">
                                            ${article.content}
                                    </div>
                                    <div class="text-2-second">
                                        <div class="bottom-1">
                                            <ul>
                                                <li><img src="${pageContext.request.contextPath}/src/images/tx.jpg"></li>
                                                <li><div style="width:60px;height:20px;overflow:hidden;text-overflow:ellipsis;">${article.user.loginname}</div></li>
                                                <li><img src="${pageContext.request.contextPath}/src/images/sj.jpg"></li>
                                                <li>${article.publish_time}</li>
                                            </ul>
                                        </div>
                                        <div class="bottom-2">
                                            <ul>
                                                <li><a href="
                                    <c:url value="/show?type=show">
                                          <c:param name="article_id" value="${article.id}">${article.id}</c:param>
                                   </c:url>
                                " style="color: #5D5D5D;">阅读全文</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                         </c:forEach>
                    </li>
                </ul>
            </div>
        </div>

        <div class="showPage">
            <ul>
                <c:if test="${requestScope.pageBean.pageNow > 1}">
                    <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${requestScope.pageBean.pageNow-1}"
                           style="padding-top: 10px;">上一页</a></li>
                </c:if>
                <c:choose>
                    <c:when test="${requestScope.pageBean.pageCount <= 10}">
                        <c:forEach begin="1" end="${requestScope.pageBean.pageCount}" step="1"  varStatus="count">
                            <c:choose>
                                <c:when test="${requestScope.pageBean.pageNow == count.index}">
                                    <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${count.index}"
                                           class="countType" style="padding-top: 10px;">${count.index}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${count.index}"
                                           style="padding-top: 10px;">${count.index}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${requestScope.pageBean.pageNow >= 6 &&  requestScope.pageBean.pageCount - requestScope.pageBean.pageNow >= 4}">
                            <c:forEach begin="${requestScope.pageBean.pageNow - 5}"
                                       end="${requestScope.pageBean.pageNow + 4}" step="1" varStatus="count">
                                <c:choose>
                                    <c:when test="${requestScope.pageBean.pageNow == count.index}">
                                        <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${count.index}"
                                               class="countType" style="padding-top: 10px;">${count.index}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${count.index}"
                                               style="padding-top: 10px;">${count.index}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.pageBean.pageNow < 6}">
                            <c:forEach begin="1" end="10" step="1" varStatus="count">
                                <c:choose>
                                    <c:when test="${requestScope.pageBean.pageNow == count.index}">
                                        <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${count.index}"
                                               class="countType" style="padding-top: 10px;">${count.index}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${count.index}"
                                               style="padding-top: 10px;">${count.index}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.pageBean.pageCount - requestScope.pageBean.pageNow < 4 && requestScope.pageBean.pageCount - requestScope.pageBean.pageNow >= 0}">
                            <c:forEach begin="${requestScope.pageBean.pageCount - 9}"
                                       end="${requestScope.pageBean.pageCount}" step="1" varStatus="count">
                                <c:choose>
                                    <c:when test="${requestScope.pageBean.pageNow == count.index}">
                                        <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${count.index}"
                                               class="countType" style="padding-top: 10px;">${count.index}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${count.index}"
                                               style="padding-top: 10px;">${count.index}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <c:if test="${requestScope.pageBean.pageCount  > requestScope.pageBean.pageNow}">
                    <li><a href="${pageContext.request.contextPath}/showPage?pageNow=${requestScope.pageBean.pageNow+1}"
                           style="padding-top: 10px;">下一页</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <!--left结束-->
    <div id="c_right">
        <div id="right">
            <div id="right-text">
                <ul>
                    <li><h2>最新文章</h2><br>
                        <hr>
                    </li>
                    <c:forEach items="${requestScope.pageBeanRight.data}" var="article">
                        <li>
                            <a href=" <c:url value="/show?type=show">
                                          <c:param name="article_id" value="${article.id}">${article.id}</c:param>
                                   </c:url>"><img
                                    src="${article.picture.url}" width="90"
                                    height="70"></a>
                            <a href=" <c:url value="/show?type=show">
                                          <c:param name="article_id" value="${article.id}">${article.id}</c:param>
                                   </c:url>">
                                <div style="width: 100px;height: 40px;overflow: hidden;">${article.title}</div></a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="right-text-1">
            <ul>
                <li class="top"><b>关于我们</b></li>
                <li>
                    <hr>
                </li>
                <li><a href="${pageContext.request.contextPath}/jsps/about_us.jsp"><img
                        src="${pageContext.request.contextPath}/src/img/main-1.jpg"></a></li>
                <li><a href="${pageContext.request.contextPath}/jsps/about_us.jsp" style="color:#73706D;">班级：信息与计算科学一班</a>
                </li>
                <li>
                    <p>
                        <a href="#" title="联系博主">
                            <span class="b_1"></span>
                        </a>
                        <a href="#" title="加入QQ群，一起学习！">
                            <span class="b_2"></span>
                        </a>
                    <div class="clear"></div>
                    </p>
                </li>
            </ul>
        </div>
        <!--right-text-1结束-->
        <div class="lanmubox">
            <div>
                <p class="top">
                <h2>精心推荐</h2></p>
                <p>
                <hr>
                </p>
            </div>
            <div class="bd">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/article?type=search&pageNow=1&keyword=网站项目实战开发" title="网站项目实战开发">网站项目实战开发</a></li>
                    <li><a href="${pageContext.request.contextPath}/article?type=search&pageNow=1&keyword=关于响应式布局" title="关于响应式布局">关于响应式布局</a></li>
                    <li><a href="${pageContext.request.contextPath}/article?type=search&pageNow=1&keyword=如何创建个人博客网站" title="如何创建个人博客网站">如何创建个人博客网站</a></li>
                    <li><a href="${pageContext.request.contextPath}/article?type=search&pageNow=1&keyword=J2EE学习路线" title="J2EE学习路线">J2EE学习路线</a></li>
                    <li><a href="${pageContext.request.contextPath}/article?type=search&pageNow=1&keyword=程序员的养成" title="程序员的养成">程序员的养成</a></li>
                </ul>
            </div>
        </div>
        <!--lanmubox结束-->

        <div class="right-text-2">
            <div>
                <p>
                <h2 class="top">友情链接</h2></p>
                <p>
                <hr>
                </p>
            </div>
            <ul>
                <li><a href="#">网站模板</a></li>
                <li><a href="#">郭鹏涛网页设计</a></li>
                <li><a href="#">站长论坛</a></li>
                <li><a href="#">SEO博客</a></li>
                <li><a href="#">艺小昔个人博客</a></li>
            </ul>
        </div>
    </div>
    <!--right结束-->
    <div class="footer-wrap">
        <p style="text-align: center;"><span>本网站开发纯属兴趣爱好，不用于任何商业活动，如果本站部分内容侵犯您的权益，请您告知，站长会立即处理</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Designed by：郑国 黄健勇 李玺</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Powered by：合工大14级信计专业</span></p>
        <p style="text-align: center;margin-top: 10px;margin-bottom: 20px"><span>备案号：<strong>皖ICP备17015032号</strong></span></p>
    </div>
</div>
</body>
</html>
