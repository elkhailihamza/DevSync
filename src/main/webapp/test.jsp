<jsp:useBean id="test" scope="request" type="com.DevSync.Servlets.HelloServlet"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>document</title>
</head>
<body>
<h1>hello, ${test}</h1> <!-- Access the 'test' attribute -->
</body>
</html>