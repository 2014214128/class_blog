<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2017/6/23
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>audio</title>
</head>
<body>
<audio autoplay="autoplay" hidden = "hidden" loop = "loop" controls="controls">
    <source src="${pageContext.request.contextPath}/src/audio/Ending.mp3" type="audio/mpeg">
</audio>
</body>
</html>
