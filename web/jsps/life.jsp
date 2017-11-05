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
    <title>生活感悟</title>
    <meta name="keywords" content="生活感悟" />
    <meta name="description" content="" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/study.css">
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
            "  class="first-a">生活感悟</a>
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
            <li><font color="#F00E71">生活感悟</font></li>
        </ul>
    </div>
</div>
<div id="body">
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
                                ">阅读全文</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </li>
        </ul>
        <div class="showPage">
            <ul>
                <c:if test="${requestScope.pageBean.pageNow > 1}">
                    <li>
                        <a href="
                        <c:url value="/show?type=life">
                            <c:param name="pageNow" value="${requestScope.pageBean.pageNow-1}">${requestScope.pageBean.pageNow-1}</c:param>
                        </c:url>
                        " style="padding-top: 10px;">上一页
                        </a>
                    </li>
                </c:if>
                <c:choose>
                    <c:when test="${requestScope.pageBean.pageCount <= 10}">
                        <c:forEach begin="1" end="${requestScope.pageBean.pageCount}" step="1" var="num">
                            <c:choose>
                                <c:when test="${requestScope.pageBean.pageNow == num}">
                                    <li> <a href="
                                    <c:url value="/show?type=life">
                                      <c:param name="pageNow" value="${num}">${num}</c:param>
                                     </c:url>
                                   " class="countType" style="padding-top: 10px;">${num}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li> <a href="
                                    <c:url value="/show?type=life">
                                      <c:param name="pageNow" value="${num}">${num}</c:param>
                                     </c:url>
                                   "  style="padding-top: 10px;">${num}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${requestScope.pageBean.pageNow >= 6 &&  requestScope.pageBean.pageCount - requestScope.pageBean.pageNow >= 4}">
                            <c:forEach begin="${requestScope.pageBean.pageNow - 5}" end="${requestScope.pageBean.pageNow + 4}" step="1"  var="num">
                                <c:choose>
                                    <c:when test="${requestScope.pageBean.pageNow == num}">
                                        <li> <a href="
                                    <c:url value="/show?type=life">
                                      <c:param name="pageNow" value="${num}">${num}</c:param>
                                     </c:url>
                                   " class="countType" style="padding-top: 10px;">${num}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li> <a href="
                                    <c:url value="/show?type=life">
                                      <c:param name="pageNow" value="${num}">${num}</c:param>
                                     </c:url>
                                   "  style="padding-top: 10px;">${num}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.pageBean.pageNow < 6}">
                            <c:forEach begin="1" end="10" step="1" var="num">
                                <c:choose>
                                    <c:when test="${requestScope.pageBean.pageNow == num}">
                                        <li> <a href="
                                    <c:url value="/show?type=life">
                                      <c:param name="pageNow" value="${num}">${num}</c:param>
                                     </c:url>
                                   " class="countType" style="padding-top: 10px;">${num}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li> <a href="
                                    <c:url value="/show?type=life">
                                      <c:param name="pageNow" value="${num}">${num}</c:param>
                                     </c:url>
                                   "  style="padding-top: 10px;">${num}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                        <c:if test="${requestScope.pageBean.pageCount - requestScope.pageBean.pageNow < 4 && requestScope.pageBean.pageCount - requestScope.pageBean.pageNow >= 0}">
                            <c:forEach begin="${requestScope.pageBean.pageCount - 9}" end="${requestScope.pageBean.pageCount}" step="1" var="num">
                                <c:choose>
                                    <c:when test="${requestScope.pageBean.pageNow == num}">
                                        <li> <a href="
                                    <c:url value="/show?type=life">
                                      <c:param name="pageNow" value="${num}">${num}</c:param>
                                     </c:url>
                                   " class="countType" style="padding-top: 10px;">${num}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li> <a href="
                                    <c:url value="/show?type=life">
                                      <c:param name="pageNow" value="${num}">${num}</c:param>
                                     </c:url>
                                   "  style="padding-top: 10px;">${num}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>
                    </c:otherwise>
                </c:choose>
                <c:if test="${requestScope.pageBean.pageCount  > requestScope.pageBean.pageNow}">
                    <li><a href="
                        <c:url value="/show?type=life">
                            <c:param name="pageNow" value="${requestScope.pageBean.pageNow+1}">${requestScope.pageBean.pageNow+1}</c:param>
                        </c:url>
                        " style="padding-top: 10px;">下一页
                    </a></li>
                </c:if>
            </ul>
        </div>
    </div>

    <!--content_text结束-->
    <div id="right">
        <div id="right-text">
            <ul>
                <li><img src="${pageContext.request.contextPath}/src/img/hot-recommend-img.png" width="200" class="hot-recommend"><br><hr></li>
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
