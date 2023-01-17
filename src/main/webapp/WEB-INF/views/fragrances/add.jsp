<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  <%@include file='/WEB-INF/views/css/table_dark.css' %>
</style>
<html>
<head>
  <title>Add fragrance</title>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>
<form method="post" id="fragrance" action="${pageContext.request.contextPath}/fragrances/add"></form>
<h1 class="table_dark">Add fragrance:</h1>
<table border="1" class="table_dark">
  <tr>
    <th>Fragrance name</th>
    <th>Brand id</th>
    <th>Add</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="fragrance_name" form="fragrance" required>
    </td>
    <td>
      <input type="number" name="brand_id" form="fragrance" required>
    </td>
    <td>
      <input type="submit" name="add" form="fragrance">
    </td>
  </tr>
</table>
</body>
</html>
