<%--
  Created by IntelliJ IDEA.
  User: zhengguo
  Date: 2017/6/17
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
    <title>发表文章</title>
    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/main.css">
    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/write.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/index.css">
    <link type="text/css" rel="Stylesheet"
          href="${pageContext.request.contextPath}/src/css/csdn1/jquery.autocomplete.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/jquery-version.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/csdn.js.下载"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/master.js.下载"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/src/css/csdn1/jquery.autocomplete.min.js.下载"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/src/css/csdn1/xheditor-1.1.13-zh-cn.js.下载"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/src/css/csdn1/tracking.js.下载"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/src/css/csdn1/main.js.下载"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/style.css">
    <link id="xheCSS_default" rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/src/css/csdn1/ui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/csdn1/style(1).css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/scripts/draftEdit.js"></script>
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
                                src="${sessionScope.user.picture.url}"></a>
                    </c:otherwise>
                </c:choose></dt>
                <dd>
                    <ul>
                        <li class="user_name"><a href="${pageContext.request.contextPath}/jsps/background/homepage/updatePersonalInfo.jsp"
                                                 class="user_name">${user.loginname}</a><span><a href="${pageContext.request.contextPath}/showPage?pageNow=1">返回首页</a></span>
                        </li>
                        <li class="feed_link"><a href="${pageContext.request.contextPath}/jsps/background/homepage/updatePersonalInfo.jsp">修改个人信息</a>|
                            <a onclick="deleteUser(${user.id})">用户注销</a>
                        </li>
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
                <li id="tab_postedit" style="" class="active">
                    <a href="${pageContext.request.contextPath}/jsps/background/homepage/publishArticle.jsp"><span>发表文章</span></a></li>
                <li><a href="${pageContext.request.contextPath}/draft?type=show&pageNow=1"><span>草稿箱</span></a></li>
            </c:if>
            <c:if test="${sessionScope.user.grade == 2}">
                <li id="tab_postedit" style="" class="active">
                    <a href="${pageContext.request.contextPath}/jsps/background/homepage/publishArticle.jsp"><span>发表文章</span></a></li>
                <li><a href="${pageContext.request.contextPath}/draft?type=show&pageNow=1"><span>草稿箱</span></a></li>
                <li><a href="${pageContext.request.contextPath}//article?type=show&pageNow=1"><span>文章管理</span></a></li>

            </c:if>
            <c:if test="${sessionScope.user.grade == 3}">
                <li id="tab_postedit" style="" class="active">
                    <a href="${pageContext.request.contextPath}/jsps/background/homepage/publishArticle.jsp"><span>发表文章</span></a></li>
                <li><a href="${pageContext.request.contextPath}/draft?type=show&pageNow=1"><span>草稿箱</span></a></li>
                <li><a href="${pageContext.request.contextPath}/article?type=show&pageNow=1"><span>文章管理</span></a></li>

                <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/sortManage.jsp"><span>类别管理</span></a></li>
                <li><a href="${pageContext.request.contextPath}/jsps/background/homepage/roleManage.jsp"><span>角色管理</span></a></li>
            </c:if>
        </ul>
    </div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/src/css/csdn1/posteditnew.js.下载"></script>
    <link type="text/css" rel="Stylesheet" href="${pageContext.request.contextPath}/src/css/csdn1/bole_write.css">
    <form action="${pageContext.request.contextPath}/article?type=publish" id="form" name="form" method="post"
          enctype="multipart/form-data">
        <div id="editType" style="display:none">0</div>
        <p class="subtit">文章标题<span style="color: green; margin-left: 18px; display: none">尊重原创，请保证您的文章为原创作品</span>
            <input type="hidden" id="time" name="time" value="2017-11-11">
        </p>
        <div>
            <select id="selType" name="category">
                <option value="0">请选择</option>
                <c:forEach items="${applicationScope.categoryList}" var="category">
                    <c:choose>
                        <c:when test="${category.ddlname==requestScope.categoryname}">
                            <option value="${category.ddlcode}" selected="selected">${category.ddlname}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${category.ddlcode}">${category.ddlname}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="text" id="txtTitle" name="title" style="width:560px; height:20px; float:left;" maxlength="100" value="${sessionScope.draft.title}">

        </div>
        <div class="clear"></div>
        <p id="p_desc" class="subtit" style="">摘要：<span class="gray">（默认自动提取您文章的前200字显示在博客首页作为文章摘要，您也可以在这里自行编辑 ）</span>
        </p>
        <div id="d_desc">
            <textarea id="txtDesc" name="note" rows="6" style="width:99%;">${sessionScope.draft.note}</textarea>
        </div>
        <div id="checkcodearea">
        </div>
        <p class="subtit">文章内容<span style="color:#ff9900;font-weight:normal;display:none;">（很抱歉，由于博客图片审核功能尚未完成，普通用户暂时关闭引用站外图片功能，请您谅解，我们会尽快开放。）</span><span
                id="autosave_note"></span>
        </p>
        <div class="section">

            <textarea id="editor" name="content" rows="30" cols="131">${sessionScope.draft.content}</textarea>
        </div>

        <style type="text/css">
            #ol_img {
                margin: 0px 12px;
                padding: 10px;
            }

            #ol_img li {
                list-style-type: decimal;
            }

            #ol_img li a {
                margin-left: 10px;
            }
        </style>
        <fieldset style="margin-top: 10px; padding: 4px 10px 10px 10px;">
            <legend>上传图片</legend>
            <table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
                <tbody>
                <tr>
                    <td valign="top">
                        <ol id="ol_img"></ol>
                        <p id="p_err" style="color: Red;"></p>
                    </td>
                    <td rowspan="2" valign="top" style="width: 240px;">
                        <div style="border: solid 1px #999; background-color: #f0f0f0; font-size: 11px; padding-left: 10px;">
                            <p>1、图片大小不能超过<b>2M</b></p>
                            <p>2、支持格式：.jpg .gif .png .bmp</p>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td valign="bottom">
                        <input type="file" id="file1" value="选择图片" name="picture" style="width:380px;"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </fieldset>
        <script type="text/javascript">

        </script>
        <p class="subtit" style="display:none;">文章别名（URL中使用，取代文章ID）</p>
        <div style="display:none;">
            <input type="text" id="txtFileName" style="width:60%; height:20px;" maxlength="30">（只能使用数字、字母、横线和下划线）
        </div>
        <p class="subtit">文章分类（到分类首页）</p>
        <div class="radioBox channel">
            <c:forEach items="${applicationScope.second_categoryList}" var="second_category">

                <c:choose>
                    <c:when test="${second_category.ddlname == requestScope.second_categoryname}">
                        <input type="radio" class="radChs" name="second_category" id="${second_category.ddlcode}"
                               value="${second_category.ddlcode}" checked="checked"><label
                            for="${second_category.ddlcode}">${second_category.ddlname}</label>
                    </c:when>
                    <c:otherwise>
                        <input type="radio" class="radChs" name="second_category" id="${second_category.ddlcode}"
                               value="${second_category.ddlcode}"><label
                            for="${second_category.ddlcode}">${second_category.ddlname}</label>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <div class="btn_area_1">
            <input id="btnPublish" type="button" value="发表文章">
            <input id="btnDraft" type="button" value="立即保存">
            <input id="btnCancel" type="button" value="舍弃">
        </div>
    </form>
    <div class="footer-wrap">
        <p style="text-align: center;"><span>本网站开发纯属兴趣爱好，不用于任何商业活动，如果本站部分内容侵犯您的权益，请您告知，站长会立即处理</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Designed by：郑国 黄健勇 李玺</span></p>
        <p style="text-align: center;margin-top: 10px;"><span>Powered by：合工大14级信计专业</span></p>
        <p style="text-align: center;margin-top: 10px;margin-bottom: 20px"><span>备案号：<strong>皖ICP备17015032号</strong></span></p>
    </div>
</div>
</body>

