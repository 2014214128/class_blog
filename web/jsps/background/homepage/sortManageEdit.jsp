<%--
  Created by IntelliJ IDEA.
  User: zhengguo
  Date: 2017/5/28
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!-- saved from url=(0047)http://write.blog.csdn.net/postedit?ref=toolbar -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/repoAddr2.js.下载"></script>

    <title>类别管理</title>

    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/main.css">
    <!--new top-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/index.css">
    <!--new top-->
    <link href="${pageContext.request.contextPath}/src/css/sortManage/Style.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery-version.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/csdn.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/master.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/sortManageEdit.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/pub.js"></script>


    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/write.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery.autocomplete.min.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/xheditor-1.1.13-zh-cn.js.下载"></script>
    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/jquery.autocomplete.css">
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/src/css/csdn1/tracking.js.下载"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/src/css/csdn1/main.js.下载"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/style.css">
    <link id="xheCSS_default" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/ui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/style(1).css">
</head>
<body>
<div id="wrap">

    <div class="head">

        <div class="user_info">
            <dl>
                <dt><c:choose>
                    <c:when test="${sessionScope.user.picture.url == null}">
                        <a href="${pageContext.request.contextPath}/jsps/background/homepage/updatePersonalInfo.jsp"><img
                                src="${pageContext.request.contextPath}/src/css/csdn1/3_qq_31589695.jpg"></a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/jsps/background/homepage/updatePersonalInfo.jsp"><img
                                src="${sessionScope.user.picture.url}"></a>  </c:otherwise>
                </c:choose></dt>
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
            <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/publishArticle.jsp"><span>发表文章</span></a></li>

            <li><a href="${pageContext.request.contextPath}/draft?type=show&pageNow=1"><span>草稿箱</span></a></li>
            <li><a href="${pageContext.request.contextPath}/article?type=show&pageNow=1"><span>文章管理</span></a></li>

            <li id="tab_postedit" style="" class="active"><a
                    href="${pageContext.request.contextPath}/jsps/background/homepage/sortManage.jsp"><span>类别管理</span></a></li>
            <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/roleManage.jsp"><span>角色管理</span></a></li>

        </ul>
    </div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/posteditnew.js.下载"></script>


    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/bole_write.css">
    <form name="Form1" id="Form1" method="post" cssStyle="margin:0px;" action="${pageContext.request.contextPath}/systemddl?type=update">
        <table cellSpacing="1" cellPadding="0" width="100%" bgColor="#f5fafe" border="0">
            <TBODY>
            <TR height=10>
                <td colspan=3></td>
            </TR>
            <tr>
                <td class="ta_01" align="right" width="35%">类型列表：</td>
                <td class="ta_01" align="left" width="30%">
                    <select name="keyword" id="keyword" class="bg" style="width:197px" onchange="changetype()">
                        <option value="jerrynew"></option>
                        <c:forEach items="${applicationScope.keywords}" var="keyword">
                            <c:choose>
                                <c:when test="${keyword==sessionScope.keyword}">
                                    <option value="${keyword}" selected="selected">${keyword}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${keyword}">${keyword}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </td>

                <td class="ta_01" align="right" width="35%">
                </td>
            </tr>
            <TR height=10>
                <td colspan=3 align="right">
                    <input type="button" name="saveitem" value="添加选项"
                           style="font-size:12px; color:black; height: 20px;width: 80px;" onClick="insertRows()">
                </td>
            </TR>
            </TBODY>
        </table>
        <table cellSpacing="1" cellPadding="0" width="100%" bgColor="#f5fafe" border="0">
            <tr>
                <td>
                    <table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray" border="1" id="dictTbl"
                           style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WIDTH:100%; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
                        <tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
                            <td class="ta_01" align="center" width="20%" height=22
                                background="${pageContext.request.contextPath}/src/css/images/tablehead.jpg">编号
                            </td>
                            <td class="ta_01" align="center" width="60%" height=22
                                background="${pageContext.request.contextPath}/src/css/images/tablehead.jpg">名称
                            </td>
                            <td class="ta_01" align="center" width="20%" height=22
                                background="${pageContext.request.contextPath}/src/css/images/tablehead.jpg">删除
                            </td>
                        </tr>
                        <c:choose>
                            <c:when test="${sessionScope.systemList != null && sessionScope.systemList.size() > 0}">
                                <c:forEach items="${sessionScope.systemList}" var="systemddl">
                                    <tr>
                                        <td class="ta_01" align="center" width="20%">
                                                ${systemddl.ddlcode}
                                        </td>
                                        <td class="ta_01" align="center" width="60%">
                                            <input id="itemname" name="itemname" type="text"
                                                   value="${systemddl.ddlname}" size="45" maxlength="25">
                                        </td>
                                        <td class="ta_01" align="center" width="20%">
                                            <a href="#" onclick="delTableRow('${systemddl.ddlcode}')">
                                                <img src="${pageContext.request.contextPath}/src/images/delete.gif" width="16" height="16" border="0"
                                                     style="CURSOR:hand">
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td class="ta_01" align="center" width="20%">1</td>
                                    <td class="ta_01" align="center" width="60%">
                                        <input name="itemname" type="text" size="45" maxlength="25"></td>
                                    <td class="ta_01" align="center" width="20%"></td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                </td>
            </tr>
            <TR height=10>
                <td colspan=3></td>
            </TR>
            <tr>
                <td align="center" colspan=3>
                    <input type="button" name="saveitem" id="saveitem" value="保存"
                           style="font-size:12px; color:black; height: 20px;width: 50px;">
                </td>
            </tr>
        </table>
    </form>
    <div class="footer-wrap">
        <p style="text-align: center;"><span>本网站开发纯属兴趣爱好，不用于任何商业活动，如果本站部分内容侵犯您的权益，请您告知，站长会立即处理</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Designed by：郑国 黄健勇 李玺</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Powered by：合工大14级信计专业</span></p>
        <p style="text-align: center;margin-top: 10px;margin-bottom: 20px"><span>备案号：<strong>皖ICP备17015032号</strong></span></p>
    </div>
</div>

</body>

