<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhengguo
  Date: 2017/5/28
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- saved from url=(0047)http://write.blog.csdn.net/postedit?ref=toolbar -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/repoAddr2.js.下载"></script>

    <title>草稿箱</title>

    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/main.css">
    <!--new top-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/index.css">
    <!--new top-->

    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery-version.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/csdn.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/master.js.下载"></script>
    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/write.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery.autocomplete.min.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/xheditor-1.1.13-zh-cn.js.下载"></script>
    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/jquery.autocomplete.css">
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/src/css/csdn1/tracking.js.下载"></script><script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/src/css/csdn1/main.js.下载"></script><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/style.css"><link id="xheCSS_default" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/ui.css"><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/style(1).css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/draft.js"></script>
</head>
<body>
<div id="wrap">
    <div class="head">
        <div class="user_info">
            <dl>
                <dt>
                    <c:choose>
                        <c:when test="${sessionScope.user.picture.url == null}">
                            <a href="${pageContext.request.contextPath}/jsps/background/homepage/updatePersonalInfo.jsp"><img
                                    src="${pageContext.request.contextPath}/src/css/csdn1/3_qq_31589695.jpg"></a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/jsps/background/homepage/updatePersonalInfo.jsp"><img
                                    src="${sessionScope.user.picture.url}"></a>
                        </c:otherwise>
                    </c:choose>
                </dt>
                <dd>
                    <ul>
                        <li class="user_name"><a href="${pageContext.request.contextPath}/jsps/background/homepage/updatePersonalInfo.jsp"
                                                 class="user_name">${user.loginname}</a><span><a href="${pageContext.request.contextPath}/showPage?pageNow=1">返回首页</a></span></li>
                        <li class="feed_link"><a href="${pageContext.request.contextPath}/jsps/background/homepage/updatePersonalInfo.jsp">修改个人信息</a>|
                            <a onclick="deleteUser(${user.id})">用户注销</a></li>
                    </ul>
                </dd>
            </dl>
        </div>

        <div style="float:right; margin-top:20px; color:Red;">

        </div>
    </div>
    <div class="tabs_header">
        <ul id="ul_tab" class="tabs" style="width:120%">
            <c:if test="${sessionScope.user.grade == 1}">
                <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/publishArticle.jsp"><span>发表文章</span></a></li>
                <li id="tab_postedit" style="" class="active"><a href="${pageContext.request.contextPath}/draft?type=show&pageNow=1"><span>草稿箱</span></a></li>
            </c:if>
            <c:if test="${sessionScope.user.grade == 2}">
                <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/publishArticle.jsp"><span>发表文章</span></a></li>
                <li id="tab_postedit" style="" class="active"><a href="${pageContext.request.contextPath}/draft?type=show&pageNow=1"><span>草稿箱</span></a></li>
                <li><a href="${pageContext.request.contextPath}/article?type=show&pageNow=1"><span>文章管理</span></a></li>

            </c:if>
            <c:if test="${sessionScope.user.grade == 3}">
                <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/publishArticle.jsp"><span>发表文章</span></a></li>
                <li id="tab_postedit" style="" class="active"><a href="${pageContext.request.contextPath}/draft?type=show&pageNow=1"><span>草稿箱</span></a></li>
                <li><a href="${pageContext.request.contextPath}/article?type=show&pageNow=1"><span>文章管理</span></a></li>

                <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/sortManage.jsp"><span>类别管理</span></a></li>
                <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/roleManage.jsp"><span>角色管理</span></a></li>
            </c:if>
        </ul>
    </div>

    <table id="lstBox" cellspacing="0">
        <tbody><tr class="">
            <th class="tdleft">标题</th>
            <th style="width:50px;">状态</th>
            <th style="width:50px;">阅读</th>
            <th style="width:50px;">评论</th>
            <th style="width:170px;">操作</th>
        </tr>
        <c:forEach items="${requestScope.pageBean.data}" var="draft">
            <tr class="">
                <td class="tdleft">
                    <a href="${pageContext.request.contextPath}/draft?type=edit&id=${draft.id}">${draft.title}</a>
                    <span class="gray">（${draft.time}）</span>
                </td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>0</td>
                <td>0</td>
                <td>
                    <a href="${pageContext.request.contextPath}/draft?type=edit&id=${draft.id}">编辑</a> |
                    <a href="${pageContext.request.contextPath}/draft?type=delete&id=${draft.id}&pageNow=1&user_id=${sessionScope.user.id}" name="del">删除</a>
                </td>
            </tr>
        </c:forEach>
         </tbody>
    </table>
    <div class="page_nav">
        <span> ${requestScope.pageBean.rowCount}条  共${requestScope.pageBean.pageCount}页</span>
        <c:if test="${requestScope.pageBean.pageNow > 1}">
            <a onclick="changePage(${requestScope.pageBean.pageNow-1})"  style="text-decoration: none;border: none;">上一页</a>
        </c:if>
        <c:choose>
            <c:when test="${requestScope.pageBean.pageCount <= 10}">
                <c:forEach begin="1" end="${requestScope.pageBean.pageCount}" step="1" var="num">
                    <c:choose>
                        <c:when test="${requestScope.pageBean.pageNow == num}">
                            <a  onclick="changePage(${num})" style="text-decoration: none;border: none;"><strong>${num}</strong></a>
                        </c:when>
                        <c:otherwise>
                             <a   style="text-decoration: none;border: none;">${num}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:if test="${requestScope.pageBean.pageNow >= 6 &&  requestScope.pageBean.pageCount - requestScope.pageBean.pageNow >= 4}">
                    <c:forEach begin="${requestScope.pageBean.pageNow - 5}" end="${requestScope.pageBean.pageNow + 4}" step="1"  var="num">
                        <c:choose>
                            <c:when test="${requestScope.pageBean.pageNow == num}">
                                <a onclick="changePage(${num})" style="text-decoration: none;border: none;">${num}</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="changePage(${num})" style="text-decoration: none;border: none;">${num}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.pageBean.pageNow < 6}">
                    <c:forEach begin="1" end="10" step="1" var="num">
                        <c:choose>
                            <c:when test="${requestScope.pageBean.pageNow == num}">
                                <a onclick="changePage(${num})" style="text-decoration: none;border: none;"><strong>${num}</strong></a>
                            </c:when>
                            <c:otherwise>
                                 <a onclick="changePage(${num})" style="text-decoration: none;border: none;">${num}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.pageBean.pageCount - requestScope.pageBean.pageNow < 4 && requestScope.pageBean.pageCount - requestScope.pageBean.pageNow >= 0}">
                    <c:forEach begin="${requestScope.pageBean.pageCount - 9}" end="${requestScope.pageBean.pageCount}" step="1" var="num">
                        <c:choose>
                            <c:when test="${requestScope.pageBean.pageNow == num}">
                                <a onclick="changePage(${num})" style="text-decoration: none;border: none;"><strong>${num}</strong></a>
                            </c:when>
                            <c:otherwise>
                                 <a onclick="changePage(${num})" style="text-decoration: none;border: none;">${num}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
            </c:otherwise>
        </c:choose>
        <c:if test="${requestScope.pageBean.pageCount  > requestScope.pageBean.pageNow}">
            <a onclick="changePage(${requestScope.pageBean.pageNow+1})"  style="text-decoration: none;border: none;">下一页</a>
        </c:if>
    </div>
    <div class="footer-wrap">
        <p style="text-align: center;"><span>本网站开发纯属兴趣爱好，不用于任何商业活动，如果本站部分内容侵犯您的权益，请您告知，站长会立即处理</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Designed by：郑国 黄健勇 李玺</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Powered by：合工大14级信计专业</span></p>
        <p style="text-align: center;margin-top: 10px;margin-bottom: 20px"><span>备案号：<strong>皖ICP备17015032号</strong></span></p>
    </div>
</div>
</body>

