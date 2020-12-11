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
    <title>添加新URL</title>
</head>
<body>
<h1>添加新URL</h1>

<table border="1" width="500" align="center">
    <tr bgcolor="00FF7F">
        <th><b>URL名称</b></th>
        <th><b>URL地址</b></th>
        <th><b>监控周期</b></th>
        <th><b>响应时间</b></th>
        <th><b>响应代码</b></th>
        <th><b>响应匹配字符串</b></th>
        <th><b>最大响应时间</b></th>
        <th><b>最小响应时间</b></th>
    </tr>
    <tr>
        <form method="post" action="${pageContext.request.contextPath}/update">
            <td>
                <input style="text-align: center" type="text" name="url_name" placeholder="localhost">
            </td>

            <td>
                <input style="text-align: center" type="text" name="url_address" placeholder="http://localhost:8080/">
            </td>

            <td>
                <input style="text-align: center" type="text" name="monitoring_period" placeholder="10000">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_time" placeholder="600">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_code" placeholder="200">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_substring" placeholder="somestring">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_range_max" placeholder="400000">
            </td>

            <td>
                <input style="text-align: center" type="text" name="response_range_min" placeholder="1">
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
