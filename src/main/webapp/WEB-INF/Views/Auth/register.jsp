<%
    request.setAttribute("contentPage", "Views/Auth/registerContent.jsp");
%>

<jsp:include page="/WEB-INF/app.jsp">
    <jsp:param name="pageTitle" value="Register"/>
</jsp:include>
