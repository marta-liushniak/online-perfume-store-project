<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='/WEB-INF/views/css/table_dark.css' %>
</style>
<html>
<head>
    <title>All brands</title>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>
<h1 class="table_dark">All brands:</h1>
<table border="1" class="table_dark">
    <tr>
        <th>Id</th>
        <th>Brand name</th>
        <th>Country</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="brand" items="${brands}">
        <tr>
            <td>
                <c:out value="${brand.id}"/>
            </td>
            <td>
                <c:out value="${brand.brandName}"/>
            </td>
            <td>
                <c:out value="${brand.country}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/brands/delete?id=${brand.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
