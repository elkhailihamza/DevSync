<%@ page import="com.DevSync.Entities.Utilisateurs" %>
<nav class="bg-white shadow-sm border-gray-200 dark:bg-gray-900 fixed top-0 left-0 w-full">
    <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
        <a href="<%= request.getContextPath() %>" class="flex items-center space-x-3 rtl:space-x-reverse">
            <span class="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">DevSync</span>
        </a>
        <div class="hidden w-full md:block md:w-auto" id="navbar-default">
            <ul class="font-medium flex flex-col p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 rtl:space-x-reverse md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                <li>
                    <a href="#" class="block py-2 px-3 text-white bg-blue-700 rounded md:bg-transparent md:text-blue-700 md:p-0 dark:text-white md:dark:text-blue-500" aria-current="page">Home</a>
                </li>
                <%
                    if (session.getAttribute("username") != null) {
                %>
                <li>
                    <a href="<%= request.getContextPath() %>/tasks" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Backlog</a>
                </li>
                <%
                        String isManagerParam = (String) session.getAttribute("isManager");
                        boolean isManager = isManagerParam != null && isManagerParam.equalsIgnoreCase("true");
                        if (isManager) {
                            %>
                                <li>
                                    <a href="<%= request.getContextPath() %>/users" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Users</a>
                                </li>
                            <%
                        }
                    }
                %>
                <li>
                    <a href="#" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">About</a>
                </li>
                <%
                    if (session.getAttribute("username") != null) {
                %>
                <li>
                    <a href="<%= request.getContextPath() %>/logout" class="block py-2 px-3 text-red-900 rounded hover:bg-red-100 md:hover:bg-transparent md:border-0 md:hover:text-red-700 md:p-0 dark:text-white md:dark:hover:text-red-500 dark:hover:bg-red-700 dark:hover:text-white md:dark:hover:bg-transparent">Logout</a>
                </li>
                <%
                    } else {
                %>
                <li>
                    <a href="<%= request.getContextPath() %>/login" class="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Login</a>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>
<div style="top: 65px" class="fixed">
    <jsp:include page="_success.jsp" />
    <jsp:include page="_error.jsp" />
</div>