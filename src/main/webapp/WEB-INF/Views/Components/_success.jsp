<%
  String successMessage = (String) request.getAttribute("successMessage");
  if (successMessage != null && !successMessage.isEmpty()) {%>
<p style="color: green;"><%= successMessage %></p>
<%
  }
%>
