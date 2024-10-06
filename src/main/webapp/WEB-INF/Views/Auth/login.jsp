<%
    request.setAttribute("contentPage", "Views/Auth/loginContent.jsp");
%>

<jsp:include page="/WEB-INF/app.jsp">
    <jsp:param name="pageTitle" value="Login"/>
</jsp:include>