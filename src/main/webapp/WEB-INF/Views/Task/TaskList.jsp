<%@ page import="java.util.List" %>
<%@ page import="com.DevSync.Entities.Task" %>
<%@ page import="com.DevSync.Entities.Utilisateur" %>
<%@ page import="java.util.Objects" %>

<%
    @SuppressWarnings("unchecked")
    List<Task> taskList = (List<Task>) request.getAttribute("TaskList");
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    int i = 1;
%>

<div class="h-screen w-screen flex flex-col gap-4 justify-center items-center">
    <%
        if (taskList != null && !taskList.isEmpty()) {
    %>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-6 py-3">Task ID</th>
                <th scope="col" class="px-6 py-3">Title</th>
                <th scope="col" class="px-6 py-3">Description</th>
                <th scope="col" class="px-6 py-3">Created At</th>
                <th scope="col" class="px-6 py-3">Due Date</th>
                <th scope="col" class="px-6 py-3">Status</th>
                <th scope="col" class="px-6 py-3">Assigned To</th>
                <th scope="col" class="px-6 py-3">Controls</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Task task : taskList) {
            %>
            <tr class="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                    <%= i++ %>
                </th>
                <td class="px-6 py-4">
                    <%= task.getTitle() %>
                </td>
                <td class="px-6 py-4">
                    <%= task.getDescription().isEmpty() ? "non-specified" : task.getDescription() %>
                </td>
                <td class="px-6 py-4">
                    <%= task.getCreatedAt() %>
                </td>
                <td class="px-6 py-4">
                    <%= task.getDueDate() %>
                </td>
                <td class="px-6 py-4">
                    <%= task.getStatus() %>
                </td>
                <td class="px-6 py-4">
                    <%
                        if (task.getAssignee() != null && !task.getAssignee().getUser_name().isEmpty()) {
                            String assigneeUsername = task.getAssignee().getUser_name();
                            String username = (String) session.getAttribute("username");
                    %>
                    <%= Objects.equals(assigneeUsername, username) ? "YOU" : assigneeUsername%>
                    <%
                    } else {
                    %>
                    <h6>Not Assigned</h6>
                    <%
                        }
                    %>
                </td>
                <td class="px-6 py-4">
                    <div class="gap-4 flex justify-between items-center">
                        <%
                            if (task.getAssignee() == null || user.isManager()) {
                                if (task.getAssignee() == null) { %>
                                <form action="${pageContext.request.contextPath}/tasks/assign" method="post">
                                    <input type="hidden" name="taskId" value="<%= task.getId() %>" />
                                    <input type="hidden" name="_method" value="ASSIGN" />
                                    <button class="task bg-blue-500 hover:bg-blue-700 transition-all p-1 rounded-md">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"></path><path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"></path></svg>
                                    </button>
                                </form>
                            <% } %>
                            <% if (user.isManager()) { %>
                                <a href="${pageContext.request.contextPath}/tasks/assign?id=<%= task.getId() %>" class="task bg-red-500 hover:bg-red-700 transition-all p-1 rounded-md">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"></path><path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"></path></svg>
                                </a>

                                <a href="${pageContext.request.contextPath}/tasks/update?id=<%=task.getId()%>" class="bg-green-500 hover:bg-green-700 transition-all p-1 rounded-md">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                        <path d="M20 14.66V20a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h5.34"></path>
                                        <polygon points="18 2 22 6 12 16 8 16 8 12 18 2"></polygon>
                                    </svg>
                                </a>

                                <form action="${pageContext.request.contextPath}/tasks/delete?id=<%=task.getId()%>" method="post">
                                    <input type="hidden" name="_method" value="DELETE" />
                                    <button class="bg-red-500 hover:bg-red-700 transition-all p-1 rounded-md">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg>
                                    </button>
                                </form>
                            <% }
                            } else {
                            %>
                                <span class="italic">EMPTY</span>
                            <%
                                }
                            %>
                    </div>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <% if(user.isManager()) {%>
            <div class="w-full flex justify-between items-center p-4" style="user-select: none">
                <h1>Create a new Task</h1>
                <a href="${pageContext.request.contextPath}/tasks/create" class="bg-blue-600 hover:bg-blue-700 transition-all text-white px-4 py-2 rounded-sm">Create a task</a>
            </div>
        <% } %>
    </div>
    <%
    } else {
    %>
    <div class="bg-gray-50 text-center rounded-xl select-none" style="padding: 5rem 4rem">
        <h1 class="text-2xl mb-4">Create a Task to begin!</h1>
        <a href="${pageContext.request.contextPath}/tasks/create" class="bg-blue-600 hover:bg-blue-700 transition-all text-white px-4 py-2 rounded-sm">Create a task</a>
    </div>
    <%
        }
    %>
</div>