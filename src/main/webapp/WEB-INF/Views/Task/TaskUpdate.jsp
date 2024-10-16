<%@ page import="java.util.List" %>
<%@ page import="com.DevSync.Entities.Task" %>
<%@ page import="com.DevSync.Entities.UserToken" %>
<%@ page import="com.DevSync.Entities.Utilisateur" %>
<%
    @SuppressWarnings("unchecked")
    List<String> statusList = (List<String>) request.getAttribute("statusList");
    Task selectedTask = (Task) request.getAttribute("selectedTask");
    @SuppressWarnings("unchecked")
    String jsonArray = (String) request.getAttribute("tagList");
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    UserToken userTokens = user.getUserTokens();
    int updateTokens = userTokens.getDailyUpdateTokens();
%>

<div style="margin-top: 70px" class="h-screen flex flex-col gap-4 justify-center items-center">
    <% if (updateTokens > 0) {%>
        <div class="w-full text-center">
            <h2 class="text-2xl"><%= selectedTask.getTitle() %></h2>
        </div>
        <form id="task-form" class="w-full max-w-md min-w-fit text-lg md:text-md sm:text-sm" action="${pageContext.request.contextPath}/tasks/put?id=<%=selectedTask.getId()%>" method="post">
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
            <div class="mb-6 w-full flex justify-between items-center">
                <input type="hidden" id="tagsInput" name="tags" />
                <jsp:include page="/WEB-INF/Views/Components/_tags.jsp" />
            </div>
            <div class="text-center">
                <button type="submit" class="py-2 px-6 transition-all bg-green-600 hover:bg-green-700 text-white rounded-sm">Update</button>
            </div>
        </form>
    <%} else {%>
        <h1>Update Tokens are depleted! wait until tomorrow.</h1>
    <%}%>
</div>

<script>const tagList = <%= jsonArray %>;</script>
<script src="${pageContext.request.contextPath}/js/AddTags.js"></script>
<script src="${pageContext.request.contextPath}/js/DateChangeOvertime.js"></script>
<script src="${pageContext.request.contextPath}/js/TaskFormValidator.js"></script>