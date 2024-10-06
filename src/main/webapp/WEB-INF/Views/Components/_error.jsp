<%
    String errorMessage = (String) request.getAttribute("errorMessage");

    if (errorMessage != null && !errorMessage.isEmpty()) {
%>
<p class="bg-red-400"><%= errorMessage %></p>
<%
    }
%>
