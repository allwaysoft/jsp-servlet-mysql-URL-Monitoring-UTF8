<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>

<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>驱动错误！</title>
</head>
<body>
<h1 style="text-align: center">驱动错误！</h1>
<br/>
<form align="center" action="${pageContext.request.contextPath}/monitoring">
    <input type="submit" value="请重试">
</form>
</body>
</html>
