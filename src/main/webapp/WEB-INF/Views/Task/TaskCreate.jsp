<%@ page import="java.util.List" %><%
    @SuppressWarnings("unchecked")
    List<String> statusList = (List<String>) request.getAttribute("statusList");
%>

<div style="margin-top: 70px" class="h-screen flex flex-col gap-4 justify-center items-center">
    <div class="w-full text-center">
        <h2 class="text-2xl">Backlog</h2>
    </div>
    <form id="task-form" class="w-full max-w-md min-w-fit text-lg md:text-md sm:text-sm" action="<%= request.getContextPath() %>/tasks/save" method="post">
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
        <div class="mb-6 w-full flex justify-between items-center">
            <input type="hidden" id="tagsInput" name="tags" />
            <jsp:include page="/WEB-INF/Views/Components/_tags.jsp" />
        </div>
        <div class="text-center p-2">
            <button id="submit-button" type="submit" class="py-2 px-6 transition-all bg-blue-600 hover:bg-blue-700 text-white rounded-sm">Create</button>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/AddTags.js"></script>
<script src="${pageContext.request.contextPath}/js/DateChangeOvertime.js"></script>
<script src="${pageContext.request.contextPath}/js/TaskFormValidator.js"></script>
