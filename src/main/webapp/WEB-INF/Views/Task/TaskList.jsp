<%@ page import="java.util.List" %>
<%@ page import="com.DevSync.Entities.Tasks" %>

<%
    @SuppressWarnings("unchecked")
    List<Tasks> taskList = (List<Tasks>) request.getAttribute("TaskList");
    int i = 1;
%>

<div class="h-screen w-screen flex justify-center items-center">
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
                for (Tasks task : taskList) {
            %>
            <tr class="odd:bg-white odd:dark:bg-gray-900 even:bg-gray-50 even:dark:bg-gray-800 border-b dark:border-gray-700">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                    <%= i++ %>
                </th>
                <td class="px-6 py-4">
                    <%= task.getTitle() %>
                </td>
                <td class="px-6 py-4">
                    <%= task.getDescription() %>
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
                    %>
                    <%= task.getAssignee().getUser_name() %>
                    <%
                    } else {
                    %>
                    <h6>Not Assigned</h6>
                    <%
                        }
                    %>
                </td>
                <td class="px-6 py-4">
                    <div class="text-white" style="display: flex; justify-content: space-between">
                        <button class="bg-green-500 hover:bg-green-700 transition-all p-1 rounded-md">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 14.66V20a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h5.34"></path><polygon points="18 2 22 6 12 16 8 16 8 12 18 2"></polygon></svg>
                        </button>
                        <form action="${pageContext.request.contextPath}/tasks/delete" method="post">
                            <input type="hidden" name="taskId" value="<%= task.getId() %>" />
                            <button class="bg-red-500 hover:bg-red-700 transition-all p-1 rounded-md">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="15" y1="9" x2="9" y2="15"></line><line x1="9" y1="9" x2="15" y2="15"></line></svg>
                            </button>
                        </form> 
                    </div>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
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
