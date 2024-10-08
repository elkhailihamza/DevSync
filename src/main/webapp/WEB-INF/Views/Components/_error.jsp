<%
    String errorMessage = (String) session.getAttribute("errorMessage");
    if (errorMessage != null && !errorMessage.isEmpty()) {
%>
    <div class="infoMsg flex justify-between items-center bg-red-600 transition-all px-2 py-2">
        <p class="bold text-white"><%= errorMessage %></p>
        <div class="infoMsgBtn rounded-full hover:bg-gray-50 cursor-pointer transition-all p-2">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" stroke="#000000" fill="none" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
        </div>
        <script src="${pageContext.request.contextPath}/js/InfoBoxScript.js"></script>
    </div>
<%
        session.removeAttribute("errorMessage");
    }
%>
