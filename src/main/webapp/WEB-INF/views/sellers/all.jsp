<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='/WEB-INF/views/css/table_dark.css' %>
</style>
<html>
<head>
    <title>All sellers</title>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>
<h1 class="table_dark">All sellers:</h1>
<table border="1" class="table_dark">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Last name</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="seller" items="${sellers}">
        <tr>
            <td>
                <c:out value="${seller.id}"/>
            </td>
            <td>
                <c:out value="${seller.name}"/>
            </td>
            <td>
                <c:out value="${seller.lastName}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/sellers/delete?id=${seller.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
