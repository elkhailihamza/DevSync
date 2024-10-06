<%@ page import="java.util.List" %>
<%@ page import="com.DevSync.Entities.Tasks" %>
<%@ page import="com.DevSync.Enums.Status" %>
<%
    @SuppressWarnings("unchecked")
    List<String> statusList = (List<String>) request.getAttribute("statusList");
    Tasks selectedTask = (Tasks) request.getAttribute("selectedTask");
%>

<div class="h-screen flex flex-col gap-4 justify-center items-center">
    <div class="w-full text-center">
        <h2 class="text-2xl"><%= selectedTask.getTitle() %></h2>
    </div>
    <form class="w-full max-w-md min-w-fit text-lg md:text-md sm:text-sm" action="${pageContext.request.contextPath}/tasks/update?id=<%=selectedTask.getId()%>" method="post">
        <div class="mb-4">
            <label class="grid gap-4">
                <span class="bold">Title<span class="text-red-900"> *</span></span>
                <input name="task_name" value="<%= selectedTask.getTitle() %>" class="border p-2" type="text" placeholder="What should the title be?" required/>
            </label>
        </div>
        <div class="mb-4">
            <label class="grid gap-4">
                <span class="bold">Description<span class="text-red-900">*</span></span>
                <textarea name="task_description" class="border p-2" placeholder="What should be done?"><%= selectedTask.getDescription() %></textarea>
            </label>
        </div>
        <div class="mb-4">
            <label class="grid gap-4">
                <span class="bold">Start date<span class="text-red-900"> *</span></span>
                <input name="task_createdAT" value="<%= selectedTask.getCreatedAt() %>" class="border p-2" type="datetime-local" required/>
            </label>
        </div>
        <div class="mb-4">
            <label class="grid gap-4">
                <span class="bold">Due date<span class="text-red-900"> *</span></span>
                <input name="task_dueDate" value="<%= selectedTask.getDueDate() %>" class="border p-2" type="datetime-local" required/>
            </label>
        </div>
        <div class="mb-6">
            <label class="flex justify-between items-center">
                <span class="bold">Status<span class="text-red-900"> *</span></span>
                <select name="task_status" class="p-1 text-sm rounded">
                    <%
                        for (String s : statusList) {
                    %>
                    <option value="<%= s %>" <%= selectedTask.getStatus() != null && selectedTask.getStatus().getStatus().equals(s) ? "selected" : "" %>><%= s %></option>
                    <%
                        }
                    %>
                </select>
            </label>
        </div>
        <div class="text-center">
            <button type="submit" class="py-2 px-6 transition-all bg-green-600 hover:bg-green-700 text-white rounded-sm">Update</button>
        </div>
    </form>
</div>
