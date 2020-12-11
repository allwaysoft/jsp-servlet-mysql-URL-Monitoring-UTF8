<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");
%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.WebPage" %>

<html>
<head>
    <title>URL监控</title>
</head>
<body>
<h1 style="text-align: center">URL监控URL名称</h1>
<br>
<form align="center" method="get" action="${pageContext.request.contextPath}/insert_table">
    <input type="submit" value="Add new URL">
</form>
<br>
<table border="1" align="center">
    <tr bgcolor="00FF7F">
        <th><b>URL名称</b></th>
        <th><b>URL地址</b></th>
        <th><b>状态</b></th>
        <th><b>操作</b></th>
    </tr>
    <%
        ArrayList<WebPage> webPages = (ArrayList<WebPage>) request.getAttribute("webPages");

        for (WebPage webPage : webPages) {
    %>
    <tr>
        <td style="text-align: center" width="150">
            <%=webPage.getUrlName()%>
        </td>

        <td style="text-align: center" width="200">
            <%=webPage.getUrlAddress()%>
        </td>

        <td style="text-align: center" width="300">
            <%=webPage.getStatus()%>
        </td>
        <td width="150">
            <br>
            <form align="center" method="post" action="${pageContext.request.contextPath}/read?id=<%=webPage.getId()%>">
                <input type="submit" value="编辑">
            </form>
            <form align="center" method="post" action="${pageContext.request.contextPath}/change_state?id=<%=webPage.getId()%>">
                <input type="hidden" value="">
                <input type="submit" value="停止 / 恢复">
            </form>
            <form align="center" method="post" action="${pageContext.request.contextPath}/delete?id=<%=webPage.getId()%>">
                <input type="submit" value="删除">
            </form>
        </td>
    </tr>
    <%
        }
        // refresh page every 30 sec
        response.setIntHeader("Refresh", 30);
    %>
</table>
<br>
<hr/>
</body>
</html>
