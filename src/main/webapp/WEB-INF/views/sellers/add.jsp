<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  <%@include file='/WEB-INF/views/css/table_dark.css' %>
</style>
<html>
<head>
  <title>Add seller</title>
</head>
<body>
<%@include file="/WEB-INF/views/header.jsp"%>
<form method="post" id="seller" action="${pageContext.request.contextPath}/sellers/add"></form>
<h1 class="table_dark">Add seller:</h1>
<table border="1" class="table_dark">
  <tr>
    <th>Name</th>
    <th>Last name</th>
    <th>Login</th>
    <th>Password</th>
    <th>Add</th>
  </tr>
  <tr>
    <td>
      <input type="text" name="name" form="seller" required>
    </td>
    <td>
      <input type="text" name="last_name" form="seller" required>
    </td>
    <td>
      <input type="text" name="login" form="seller" required>
    </td>
    <td>
      <input type="password" name="password" form="seller" required>
    </td>
    <td>
      <input type="submit" name="add" form="seller">
    </td>
  </tr>
</table>
<h4><a href="${pageContext.request.contextPath}/login">Login after registration process</a></h4>
</body>
</html>
