<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register page</h2>
    <form action="${pageContext.request.contextPath}/register" method="post">
        Username: <input type="text" name="username" required/><br/>
        Email: <input type="text" name="email" required/><br/>
        Password: <input type="password" name="password" required/><br/>
        Manager: <input type="checkbox" name="isManager" value="true" /><br/>
        <input type="submit" value="Register"/>
    </form>
</body>
</html>
