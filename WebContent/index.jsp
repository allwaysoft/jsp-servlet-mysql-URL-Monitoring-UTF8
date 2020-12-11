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
  <title>URL监控</title>
</head>
<body>
<h1 style="text-align: center">简单URL内容匹配监控</h1>
<br/>
<form align="center" action="${pageContext.request.contextPath}/monitoring">
  <input type="submit" value="开始监控">
</form>
</body>
</html>
