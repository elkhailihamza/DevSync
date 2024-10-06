<%
  String successMessage = (String) request.getAttribute("successMessage");
  if (successMessage != null && !successMessage.isEmpty()) {%>
<p class="bg-green-400"><%= successMessage %></p>
<%
  }
%>
