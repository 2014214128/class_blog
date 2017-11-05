<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sheeran
  Date: 2017/6/11
  Time: 15:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>修改个人信息</title>
    <meta charset='utf-8'>
    <!--插入CSS代码-->
    <style type="text/css">
        .LM-file-box {
            position: absolute;
            top: 0;
            right: 0;
            width: 200px;
            height: 200px;
        }

        .LM-file {
            position: absolute;
            top: 0;
            left: 0;
            height: 200px;
            width: 200px;
            filter: alpha(opacity:0);
            opacity: 0;
        }

        #LM-file_img {
            width: 100%;
            height: 200px;
            overflow: hidden;
            border: 1px solid #666;
        }

        * {
            margin: 0;
            padding: 0;
            list-style: none;
        }

        body {
            background-color: lightblue;
            font-family: sans-serif, Verdana, Helvetica, Arial;
        }

        #main {
            position: relative;
            width: 700px;
            margin-top: 50px;
            margin-left: auto;
            margin-right: auto;
        }

        .kv-item {
            padding-left: 100px;
            padding-bottom: 20px;
        }

        .kv-label {
            display: inline;
            height: 28px;
            line-height: 28px;
            margin-left: -100px;
            float: left;
            text-align: right;
            width: 100px;
        }

        .text-align {
            border: 1px solid #474E63;
            color: #0A1844;
            height: 28px;
            line-height: 28px;
            padding: 0 2px;
            width: 200px;
            text-align: left;
        }

        #submit {
            margin-left: 80px;
            border: 1px solid #474E63;
            height: 33px;
            width: 100px;
            font-weight: bold;
        }

        #reset {
            border: 1px solid #474E63;
            height: 33px;
            width: 100px;
            font-weight: bold;
        }
    </style>
    <!--CSS部分结束-->
    <!--此处插入JS代码-->
    <!--
    <script type="text/javascript">
   //判断前三个表单元素（用户名、密码、确认密码是否为空）
   function check(Form){
    var check=true;
    var flag=0;
    for(i=0;i<3;i++){
     if(Form.elements[i].value=="")
     {alert(Form.elements[i].title+"不能为空！");
     Form.elements[i].focus();
     check=false;
     return false;}
     //判断两次输入的密码是否相同
     if(i==2)
     if(Form.pwd.value!=Form.repwd.value)
      {alert("两次输入密码不同，请重新输入！");
     Form.repwd.value="";
        Form.pwd.value="";
     //Form.pwd.focus();
     i=i-2;
     continue;
     }

    }
   return check;
   }
        </script>
    <!--JS代码结束-->
</head>
<body>
<div id="main">
    <form name="personalForm" method="post" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/updatePage?type=modify"
          onSubmit="return check(this)">
        <div class="kv-item">
    <span class="kv-label">
     <label class="tsl" for="loginname">用户名：</label>
    </span>
            <input type="text" name="loginname" class="text-align" id="loginname" placeholder="*必填项" autofocus size="25"
                   title="用户名" value="${user.loginname}"></div>
        <div class="kv-item">
    <span class="kv-label">
     <label class="tsl" for="name">姓名：</label>
    </span>
            <input type="text" name="name" class="text-align" id="name" placeholder="*必填项" autofocus size="25"
                   title="姓名" value="${user.name}"></div>
        <div class="kv-item">
<span class="kv-label">
     <label class="tsl" for="sex_boy">性&nbsp;别：</label>
    </span>
            <c:if test="${user.sex == 0}">
                <input type="radio" name="sex" id="sex_boy" value="0" checked="checked">
                男
                <input type="radio" name="sex" id="sex_girl" value="1">女
            </c:if>

            <c:if test="${user.sex == 1 }"><input type="radio" name="sex" id="sex_boy" value="0">
                男
                <input type="radio" name="sex" id="sex_girl" value="1" checked="checked">女
            </c:if>

        </div>

        <div class="kv-item">
    <span class="kv-label">
    <label class="tsl" for="birthday">出生日期：</label>
    </span>
            <input type="date" name="birthday" id="birthday" value="${user.birthday}"></div>

        <div class="kv-item">
    <span class="kv-label">
    <label class="tsl" for="email">邮&nbsp;箱：</label>
    </span>
            <input value="${user.email}" type="text" name="email" class="text-align" id="email" title="邮箱"
                   pattern="^\w+([-+.]\w+)*@\w+([-.]\w+*\.\w+([-.]\w+)*$" required></div>

        <div class="kv-item">
    <span class="kv-label">
    <label class="tsl" for="telphone">手机号码：</label>
    </span>
            <input type="tel" name="telphone" value="${user.phone}" id="telphone"></div>

        <div class="kv-item">
    <span class="kv-label">
    <label class="tsl" for="address">联系地址：</label>
    </span>
            <input type="text" name="address" id="address" value="${user.address}"></div>
        <div class="LM-file-box">
            <img id="LM-file_img" src="">
            <input type="file" name="fileField" class="LM-file" size="28" onchange="previewImage(this)"/>
        </div>
        <div class="kv-item">
            <input type="submit" name="submit" id="submit" value="提交">
            <input type="reset" name="reset" id="reset" value="重置">
        </div>
    </form>
</div>
</form>
</div>
<script type="text/javascript">
    function previewImage(file) {
        if (file.files && file.files[0]) {
            var img = document.getElementById('LM-file_img');
            var reader = new FileReader(); //读取文件
            reader.onload = function (evt) {
                img.src = evt.target.result;
            } //加载文件
            reader.readAsDataURL(file.files[0]); //文件位置
        }
        else { //兼容IE
            file.select(); //表单的file字段被选中
            var src = document.selection.createRange().text;
            var img = document.getElementById('LM-file_img');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src; //滤镜背景图片地址
        }
    }
</script>
</body>
</html>




