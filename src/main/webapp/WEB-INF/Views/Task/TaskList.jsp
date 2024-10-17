<%@ page import="java.util.List" %>
<%@ page import="com.DevSync.Entities.Task" %>
<%@ page import="com.DevSync.Entities.Utilisateur" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.time.LocalDateTime" %>

<%
    @SuppressWarnings("unchecked")
    List<Task> taskList = (List<Task>) request.getAttribute("taskList");
%>

<div class="h-screen w-screen flex flex-col gap-4 justify-center items-center">
    <%
        if (taskList != null && !taskList.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<String> statusList = (List<String>) request.getAttribute("statusList");
            Utilisateur user = (Utilisateur) session.getAttribute("user");

            int updateTokens = user.getUserTokens().getDailyUpdateTokens();
            int deleteTokens = user.getUserTokens().getMonthlyDeletionTokens();
            int i = 1;
    %>
    <div class="relative overflow-auto shadow-md sm:rounded-lg">
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
                    boolean userIsCreator = Objects.equals(task.getCreator().getId(), user.getId());
                    boolean userIsAssignee = task.getAssignee() != null && Objects.equals(task.getAssignee().getId(), user.getId());
                    boolean taskDueDateBeforeToday = task.getDueDate().isBefore(LocalDateTime.now());
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
                    <select data-id="<%= task.getId() %>" name="task_status" class="status_select p-1 border text-sm rounded"
                            <%= taskDueDateBeforeToday || !userIsAssignee ? "disabled" : "" %> >
                        <%
                            for (String s : statusList) {
                        %>
                        <option value="<%= s %>" <%= task.getStatus() != null && task.getStatus().getStatus().equals(s) ? "selected" : "" %> >
                            <%= s %>
                        </option>
                        <%
                            }
                        %>
                    </select>
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
                    <button id="dropdownDefaultButton" data-dropdown-toggle="dropdown<%= task.getId() %>" class="rounded-lg hover:bg-gray-100 transition-all text-sm p-2 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" type="button">
                        <svg fill="#000000" height="20px" width="20px" xmlns="http://www.w3.org/2000/svg"
                             viewBox="0 0 32.055 32.055" xml:space="preserve">
                        <g>
                            <path d="M3.968,12.061C1.775,12.061,0,13.835,0,16.027c0,2.192,1.773,3.967,3.968,3.967c2.189,0,3.966-1.772,3.966-3.967
                                C7.934,13.835,6.157,12.061,3.968,12.061z M16.233,12.061c-2.188,0-3.968,1.773-3.968,3.965c0,2.192,1.778,3.967,3.968,3.967
                                s3.97-1.772,3.97-3.967C20.201,13.835,18.423,12.061,16.233,12.061z M28.09,12.061c-2.192,0-3.969,1.774-3.969,3.967
                                c0,2.19,1.774,3.965,3.969,3.965c2.188,0,3.965-1.772,3.965-3.965S30.278,12.061,28.09,12.061z"></path>
                        </g>
                        </svg>
                    </button>

                    <div id="re-assign_container"></div>

                    <div id="dropdown<%= task.getId() %>" class="z-50 absolute hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700">
                        <ul class="p-4 flex flex-col gap-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefaultButton">
                            <% if (userIsAssignee && !user.isManager() && !userIsCreator && task.isReplaceable()) {
                                if (updateTokens > 0) {%>
                                    <li>
                                        <a class="re-assign_button" data-id="<%= task.getId() %>">Ask to Re-assign task</a>
                                    </li>
                                    <% }
                                        if (deleteTokens > 0) {%>
                                    <li>
                                        <form action="${pageContext.request.contextPath}/tasks/delete?id=<%=task.getId()%>" method="post">
                                            <input type="hidden" name="_method" value="DELETE" />
                                            <button class="bg-gray-100 hover:bg-gray-200 w-full transition-all p-1 rounded-sm">
                                                <span>Ask to Delete task</span>
                                            </button>
                                        </form>
                                    </li>
                            <%  }
                            }
                                if (user.isManager() && !userIsAssignee || task.getAssignee() == null) {%>
                                <li>
                                    <form action="${pageContext.request.contextPath}/tasks/assign" method="post">
                                        <input type="hidden" name="taskId" value="<%= task.getId() %>" />
                                        <button class="task bg-gray-100 hover:bg-blue-500 hover:text-white w-full transition-all p-1 rounded-sm">
                                            <span>Assign to self</span>
                                        </button>
                                    </form>
                                </li>
                            <%}%>
                            <% if (user.isManager() && userIsCreator) { %>
                                <li>
                                    <a href="${pageContext.request.contextPath}/tasks/assign?id=<%= task.getId() %>" class="task bg-gray-100 hover:bg-blue-500 hover:text-white text-center w-full transition-all p-1 rounded-sm block">
                                        <span>Assign to someone else</span>
                                    </a>
                                </li>
                            <% } %>

                            <% if (userIsCreator || user.isManager()) {%>
                                <li class="text-center">
                                    <a href="${pageContext.request.contextPath}/tasks/update?id=<%=task.getId()%>" class="bg-gray-100 hover:bg-green-800 hover:text-white w-full transition-all p-1 rounded-sm block">
                                        Update task
                                    </a>
                                </li>
                                <li>
                                    <form action="${pageContext.request.contextPath}/tasks/delete?id=<%=task.getId()%>" method="post">
                                        <input type="hidden" name="_method" value="DELETE" />
                                        <button class="bg-red-600 hover:bg-red-800 text-white w-full transition-all p-1 rounded-sm">
                                            <span>Delete task</span>
                                        </button>
                                    </form>
                                </li>
                            <%}%>
                        </ul>
                    </div>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <div class="w-full flex justify-between items-center p-4" style="user-select: none">
            <h1>Create a new Task</h1>
            <a href="${pageContext.request.contextPath}/tasks/create" class="bg-blue-600 hover:bg-blue-700 transition-all text-white px-4 py-2 rounded-sm">Create a task</a>
        </div>
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

<script type="text/javascript">
    const contextPath = '<%= request.getContextPath() %>';
</script>
<script src="${pageContext.request.contextPath}/js/SelectStatus.js"></script>
<script src="${pageContext.request.contextPath}/js/ReAssignTask.js"></script>
