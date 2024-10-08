<%@ page import="java.util.List" %><%
    @SuppressWarnings("unchecked")
    List<String> statusList = (List<String>) request.getAttribute("statusList");
%>

<script src="${pageContext.request.contextPath}/js/TaskScript.js">
    script.startBeforeEnd();
</script>

<div class="h-screen flex flex-col gap-4 justify-center items-center">
    <div class="w-full text-center">
        <h2 class="text-2xl">Backlog</h2>
    </div>
    <form class="w-full max-w-md min-w-fit text-lg md:text-md sm:text-sm" action="<%= request.getContextPath() %>/tasks/create" method="post">
        <div class="mb-4">
            <label class="grid gap-4">
                <span class="bold">Title<span class="text-red-900"> *</span></span>
                <input name="task_name" class="border p-2" type="text" placeholder="What should the title be?" required/>
            </label>
        </div>
        <div class="mb-4">
            <label class="grid gap-4">
                <span class="bold">Description<span class="text-red-900">*</span></span>
                <textarea name="task_description" class="border p-2" placeholder="What should be done?"></textarea>
            </label>
        </div>
        <div class="mb-4">
            <label class="grid gap-4">
                <span class="bold">Start date<span class="text-red-900"> *</span></span>
                <input id="createdAT" name="task_createdAT" value="${formattedDate}" class="border p-2" type="datetime-local" required/>
            </label>
        </div>
        <div class="mb-4">
            <label class="grid gap-4">
                <span class="bold">Due date<span class="text-red-900"> *</span></span>
                <input id="dueDate" name="task_dueDate" value="${formattedDate}" class="border p-2" type="datetime-local" required/>
            </label>
        </div>
        <div class="mb-6">
            <label class="flex justify-between items-center">
                <span class="bold">Status<span class="text-red-900"> *</span></span>
                <select name="task_status" class="p-1 text-sm rounded">
                    <%
                        if (statusList != null) {
                            for (String s : statusList) {
                    %>
                    <option value="<%= s %>"><%= s %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </label>
        </div>
        <div class="text-center">
            <input hidden name="_method" value="CREATE">
            <button type="submit" class="py-2 px-6 transition-all bg-blue-600 hover:bg-blue-700 text-white rounded-sm">Create</button>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/DateChangeOvertime.js"></script>
