<%
  Boolean hasSuccess = (Boolean) request.getAttribute("hasSuccess");
  String successMessage = (String) request.getAttribute("successMessage");
  if (Boolean.TRUE.equals(hasSuccess) && successMessage != null) {%>
<p style="color: green;"><%= successMessage %></p>
<%
  }
%>
