<%
    request.setAttribute("contentPage", "Views/HomeContent.jsp");
%>

<jsp:include page="/WEB-INF/app.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>