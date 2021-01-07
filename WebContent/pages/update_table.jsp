<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="model.WebPage" %>

<html>
<head>
    <title>编辑URL</title>
</head>
<body>
<h1>编辑URL</h1>
<%
    WebPage webPage = (WebPage) request.getAttribute("webPage");
%>

<table border="1" width="500" align="center">
    <tr bgcolor="00FF7F">
        <th><b>URL名称</b></th>
        <th><b>URL地址</b></th>
        <th><b>监控周期</b></th>
        <th><b>响应时间</b></th>
        <th><b>响应代码</b></th>
        <th><b>响应匹配字符串</b></th>
        <th><b>响应字符集</b></th>
        <th><b>最大响应时间</b></th>
        <th><b>最小响应时间</b></th>
    </tr>
    <tr>
        <form align="center" method="post" action="${pageContext.request.contextPath}/update?id=<%=webPage.getId()%>">
            <td>
                <input style="text-align: center" type="text" name="url_name" value="<%=webPage.getUrlName()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="url_address" value="<%=webPage.getUrlAddress()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="monitoring_period" value="<%=webPage.getMonitoringPeriod()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_time" value="<%=webPage.getResponseTime()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_code" value="<%=webPage.getResponseCode()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_substring" value="<%=webPage.getResponseSubstring()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_charset" value="<%=webPage.getResponseCharset()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_range_max" value="<%=webPage.getResponseRangeMax()%>">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_range_min" value="<%=webPage.getResponseRangeMin()%>">
            </td>
            <input type="submit" value="保存修改">
            <br>
            <br>
            <input type="reset" value="放弃修改">
        </form>
    </tr>
</table>
<form method="get" action="${pageContext.request.contextPath}/monitoring">
    <input type="submit" value="返回监控">
</form>
<hr/>
</body>
</html>
