<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome page</title>
</head>
<body>
<h1>You've successfully logged in. It's a great pleasure to welcome you here!</h1>
<h2>All available functions:</h2>
<a href="${pageContext.request.contextPath}/sellers/all">→ Check all registered sellers</a>
<BR>
<a href="${pageContext.request.contextPath}/brands/add">→ Add new brand</a>
<BR>
<a href="${pageContext.request.contextPath}/brands/all">→ Check all available brands</a>
<BR>
<a href="${pageContext.request.contextPath}/fragrances/add">→ Add new fragrance</a>
<BR>
<a href="${pageContext.request.contextPath}/fragrances/all">→ Check all available fragrances</a>
<BR>
<a href="${pageContext.request.contextPath}/fragrances/sellers/add">→ Add seller to fragrance</a>
<BR>
<BR>
<%@include file="/WEB-INF/views/header.jsp"%>
</body>
</html>
