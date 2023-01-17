<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
    <h1>Login form:</h1>
    <h4 style="color: darkred">${errorMsg}</h4>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="form-group">
            <label for="login">Username:</label> <input type="text"
                                                         class="form-control" id="login"
                                                         name="login" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label> <input type="password"
                                                        class="form-control" id="password"
                                                        name="password" required>
        </div>
        <button type="submit">Submit</button>
    </form>
    <h4><a href="${pageContext.request.contextPath}/sellers/add">Registration form</a></h4>
</div>
</body>
</html>
