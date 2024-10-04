<%
    Boolean hasError = (Boolean) request.getAttribute("hasError");
    String errorMessage = (String) request.getAttribute("errorMessage");

    if (Boolean.TRUE.equals(hasError) && errorMessage != null) {
%>
<p style="color: red;"><%= errorMessage %></p>
<%
    }
%>
