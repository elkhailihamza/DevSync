<%
    String errorMessage = (String) request.getAttribute("errorMessage");

    if (errorMessage != null && !errorMessage.isEmpty()) {
%>
<p style="color: red;"><%= errorMessage %></p>
<%
    }
%>
