<%@ page import="java.util.List" %>
<%@ page import="com.DevSync.Entities.Tasks" %>
<%@ page import="com.DevSync.Entities.Utilisateurs" %>

<%
  @SuppressWarnings("unchecked")
  List<Utilisateurs> userList = (List<Utilisateurs>) request.getAttribute("UserList");
  int i = 1;
  long userId = (long) session.getAttribute("userId");
%>

<div class="h-screen w-screen flex flex-col gap-4 justify-center items-center">
  <%
    if (userList != null && !userList.isEmpty() && userList.size() > 1) {
  %>
  <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
      <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
      <tr>
        <th scope="col" class="px-6 py-3">User ID</th>
        <th scope="col" class="px-6 py-3">Username</th>
        <th scope="col" class="px-6 py-3">nom</th>
        <th scope="col" class="px-6 py-3">prenom</th>
        <th scope="col" class="px-6 py-3">Email</th>
        <th scope="col" class="px-6 py-3">Manager</th>
        <th scope="col" class="px-6 py-3">Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        for (Utilisateurs user : userList) {
          if (user.getId() != userId) {
      %>
      <tr class="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700">
        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
          <%= i++ %>
        </th>
        <td class="px-6 py-4">
          <%= user.getUser_name() %>
        </td>
        <td class="px-6 py-4">
          <%= user.getNom() %>
        </td>
        <td class="px-6 py-4">
          <%= user.getPrenom() %>
        </td>
        <td class="px-6 py-4">
          <%= user.getEmail() %>
        </td>
        <td class="px-6 py-4">
          <%= user.isManager() %>
        </td>
        <td class="px-6 py-4">
          <div class="flex justify-between items-center gap-4" style="display: flex; justify-content: space-between">
            <a href="${pageContext.request.contextPath}/users/update?id=<%=user.getId()%>" class="bg-green-500 hover:bg-green-700 transition-all p-1 rounded-md">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M20 14.66V20a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h5.34"></path>
                <polygon points="18 2 22 6 12 16 8 16 8 12 18 2"></polygon>
              </svg>
            </a>

            <form action="${pageContext.request.contextPath}/users/delete?id=<%= user.getId() %>" method="post">
              <input hidden name="_method" value="DELETE">
              <button class="bg-red-500 hover:bg-red-700 transition-all p-1 rounded-md">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg>
              </button>
            </form>
          </div>
        </td>
      </tr>
      <%
          }
        }
      %>
      </tbody>
    </table>
  </div>
  <%
  } else {
  %>
  <div class="bg-gray-50 text-center rounded-xl select-none" style="padding: 5rem 4rem; user-select: none">
    <h1 class="text-2xl mb-4">No user <span class="italic text-xl">(other than you)</span> can be found!</h1>
  </div>
  <%
    }
  %>
</div>
