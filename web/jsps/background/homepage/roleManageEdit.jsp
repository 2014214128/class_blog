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
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/repoAddr2.js.下载"></script>
    <title>角色管理</title>
    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/index.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery-version.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/csdn.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/master.js.下载"></script>
    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/write.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery.autocomplete.min.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/xheditor-1.1.13-zh-cn.js.下载"></script>
    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/jquery.autocomplete.css">
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/src/css/csdn1/tracking.js.下载"></script><script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/src/css/csdn1/main.js.下载"></script><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/style.css"><link id="xheCSS_default" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/ui.css"><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/style(1).css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/roleManageEdit.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/pub.js"></script>
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
            <li><a href="${pageContext.request.contextPath}/draft?type=show&pageNow=1"><span>草稿箱</span></a> </li>
            <li ><a href="${pageContext.request.contextPath}/article?type=show&pageNow=1"><span>文章管理</span></a></li>

            <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/sortManage.jsp"><span>类别管理</span></a></li>
            <li id="tab_postedit" style="" class="active"><a href="${pageContext.request.contextPath}/jsps/background/homepage/roleManage.jsp"><span>角色管理</span></a></li>
        </ul>
    </div>

    <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" colspan=2 align="right" width="100%"  height=10>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right" width="35%" >角色类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td class="ta_01" align="left"  width="65%" >
                <select name="role" id="role" class="bg" style="width:180px" onchange="changetype()">
                    <option value="0"></option>
                    <c:forEach items="${applicationScope.gradeList}" var="grade">
                        <c:choose>
                            <c:when test="${grade.ddlname==sessionScope.rolename}">
                                <option value="${grade.ddlcode}" selected="selected">${grade.ddlname}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${grade.ddlcode}">${grade.ddlname}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>

            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right" colspan=2 align="right" width="100%"  height=10></td>
        </tr>
        </TBODY>
    </table>
    <table cellSpacing="0" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
        <tr>
            <td class="ta_01" colspan=2 align="left" width="100%" >
                <br/>
                <br/>
                <span style="font-size: 14px">拥有权限：</span>
                <c:forEach items="${sessionScope.popedomList}" var="popedom">
                    <span style="font-size: 14px">${popedom.ddlname}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </c:forEach>
                <br/>
                <br/>
            </td>
        </tr>
    </table>
    <br/>
    <br/>
    <br/>
    <br/>
    <form  name="form1" id="form1"  method="post" style="margin:0px;" action="${pageContext.request.contextPath}/role">
        <table cellspacing="0" align="center" width="100%" cellpadding="1" rules="all" bordercolor="gray" border="1" id="DataGrid1"
               style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">

            <tr style="FONT-WEIGHT:bold;FONT-SIZE:12pt;HEIGHT:25px;BACKGROUND-COLOR:#afd1f3">
                <td class="ta_01"  align="center" width="20%" height=22 background="../images/tablehead.jpg">选中</td>
                <td class="ta_01"  align="center" width="40%" height=22 background="../images/tablehead.jpg">登录名</td>
                <td class="ta_01"  align="center" width="40%" height=22 background="../images/tablehead.jpg">用户姓名</td>
            </tr>
            <c:if test="${sessionScope.userList!=null}">
                <c:forEach items="${sessionScope.userList}" var="user">
                    <tr onmouseover="this.style.backgroundColor = 'white'"
                        onmouseout="this.style.backgroundColor = '#F5FAFE';">
                        <td style="HEIGHT: 22px" class="ta_01" align="center" width="20%">
                            <input type="checkbox" name="user_id" id="user_id" value="${user.id}">
                        </td>
                        <td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
                                ${user.loginname}
                        </td>
                        <td style="HEIGHT: 22px" class="ta_01" align="center" width="40%">
                                ${user.name}
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        <br/>
        <table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
            <tr>
                <td align="center" colspan=3>
                    <c:if test="${sessionScope.rolename == '普通用户'}">
                        <input type="hidden" name="type" value="update"/>
                        <input type="button" name="addRole" id="addRole" style="font-size:12px; color:black; height:20px;width:100px" value="设立管理员">
                    </c:if>
                    <c:if test="${sessionScope.rolename == '一般管理员'}">
                        <input type="hidden" name="type" value="delete"/>
                        <input type="button" name="deleteRole" id="addRole" style="font-size:12px; color:black; height:20px;width:100px" value="撤销管理员">
                    </c:if>
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

