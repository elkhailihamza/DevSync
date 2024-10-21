<%@ page import="com.DevSync.Entities.TaskRequest" %>
<%@ page import="java.util.List" %>
<%@ page import="com.DevSync.Entities.Task" %>
<%
  @SuppressWarnings("unchecked")
  List<TaskRequest> taskRequests = (List<TaskRequest>) request.getAttribute("requestList");
  int i = 1;
%>

<div class="h-screen flex justify-center items-center flex-col">
  <% if (taskRequests != null) {%>
  <div class="relative overflow-x-auto">
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
      <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
      <tr>
        <th scope="col" class="px-6 py-3">Request ID</th>
        <th scope="col" class="px-6 py-3">Reason</th>
        <th scope="col" class="px-6 py-3">Task ID</th>
        <th scope="col" class="px-6 py-3">Task</th>
        <th scope="col" class="px-6 py-3">Controls</th>
      </tr>
      </thead>
      <tbody>
      <% for (TaskRequest taskRequest : taskRequests) {
        Task task = taskRequest.getTask();
      %>
      <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
          <%= i++ %>
        </th>
        <td class="px-6 py-4">
          <%= taskRequest.getReason() %>
        </td>
        <td class="px-6 py-4">
          <%= taskRequest.getTask().getId() %>
        </td>
        <td class="px-6 py-4">
          <%= taskRequest.getTask().getTitle() %>
        </td>
        <td class="px-6 py-4 flex justify-between gap-4">
          <form action="${pageContext.request.contextPath}/tasks/request/accept?id=<%= task.getId() %>" method="post">
            <button class="p-2 bg-green-500 hover:bg-green-800 transition-all rounded">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path><polyline points="22 4 12 14.01 9 11.01"></polyline></svg>
            </button>
          </form>
          <form action="${pageContext.request.contextPath}/tasks/request/decline?id=<%= task.getId() %>" method="post">
            <button class="p-2 bg-red-500 hover:bg-red-800 transition-all rounded">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg>
            </button>
          </form>
        </td>
      </tr>
      <% }; %>
      </tbody>
    </table>
  </div>
  <%} else {%>

  <%}%>
</div>
