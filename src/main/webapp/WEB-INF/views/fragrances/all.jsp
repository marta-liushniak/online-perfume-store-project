<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='/WEB-INF/views/css/table_dark.css' %>
</style>
<html>
<head>
    <title>All fragrances</title>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>
<h1 class="table_dark">All fragrances:</h1>
<table border="1" class="table_dark">
    <tr>
        <th>Id</th>
        <th>Fragrance name</th>
        <th>Brand name</th>
        <th>Brand country</th>
        <th>Sellers</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="fragrance" items="${fragrances}">
        <tr>
            <td>
                <c:out value="${fragrance.id}"/>
            </td>
            <td>
                <c:out value="${fragrance.fragranceName}"/>
            </td>
            <td>
                <c:out value="${fragrance.brand.brandName}"/>
            </td>
            <td>
                <c:out value="${fragrance.brand.country}"/>
            </td>
            <td>
                <c:forEach var="seller" items="${fragrance.sellers}">
                    ${seller.id} ${seller.name} ${seller.lastName} <br>
                </c:forEach>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/fragrances/delete?id=${fragrance.id}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
