<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  <%@include file='/WEB-INF/views/css/table_dark.css' %>
</style>
<html>
<head>
  <title>Add brand</title>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>
<form method="post" id="brand" action="${pageContext.request.contextPath}/brands/add"></form>
<h1 class="table_dark">Add brand:</h1>
<table border="1" class="table_dark">
  <tr>
    <th>Brand name</th>
    <th>Country</th>
    <th>Add</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="brand_name" form="brand" required>
    </td>
    <td>
      <input type="text" name="country" form="brand" required>
    </td>
    <td>
      <input type="submit" name="add" form="brand" required>
    </td>
  </tr>
</table>
</body>
</html>
