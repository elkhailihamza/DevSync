<%@ page import="com.DevSync.Entities.Utilisateur" %>
<%
  @SuppressWarnings("unchecked")
  Utilisateur selectedUser = (Utilisateur) request.getAttribute("selectedUser");
%>

<div class="h-screen flex flex-col gap-4 justify-center items-center">
  <div class="w-full text-center">
    <h2 class="text-2xl"><%= selectedUser.getUser_name() %></h2>
  </div>
  <form class="w-full max-w-md min-w-fit text-lg md:text-md sm:text-sm" action="${pageContext.request.contextPath}/users/update?id=<%=selectedUser.getId()%>" method="post">
    <div class="mb-4">
      <label class="grid gap-4">
        <span class="bold">Username<span class="text-red-900"> *</span></span>
        <input name="user_name" value="<%= selectedUser.getUser_name() %>" class="border p-2" type="text" placeholder="username" required/>
      </label>
    </div>
    <div class="mb-4">
      <label class="grid gap-4">
        <span class="bold">Nom<span class="text-red-900"> *</span></span>
        <input name="user_nom" value="<%= selectedUser.getNom() != null ? selectedUser.getNom() : "" %>" placeholder="nom" class="border p-2" type="text" />
      </label>
    </div>
    <div class="mb-4">
      <label class="grid gap-4">
        <span class="bold">Prenom<span class="text-red-900"> *</span></span>
        <input name="user_prenom" value="<%= selectedUser.getPrenom() != null ? selectedUser.getPrenom() : "" %>" placeholder="prenom" class="border p-2" type="text" />
      </label>
    </div>
    <div class="mb-4">
      <label class="grid gap-4">
        <span class="bold">Email<span class="text-red-900"> *</span></span>
        <input name="user_email" value="<%= selectedUser.getEmail() %>" placeholder="email" class="border p-2" type="text" required/>
      </label>
    </div>
    <div class="mb-6">
      <label class="flex justify-between items-center">
        <span class="bold">Manager<span class="text-red-900"> *</span></span>
        <select name="isManager" class="p-1 text-sm rounded">
          <option value="<%= selectedUser.isManager() %>" selected disabled hidden><%= selectedUser.isManager() %></option>
          <option value="<%=!selectedUser.isManager()%>"><%= !selectedUser.isManager()%></option>
        </select>
      </label>
    </div>
    <div class="text-center">
      <input hidden name="_method" value="UPDATE" />
      <button type="submit" class="py-2 px-6 transition-all bg-green-600 hover:bg-green-700 text-white rounded-sm">Update</button>
    </div>
  </form>
</div>
