<div id="addTaskContainer" class="w-full mb-4">
    <label class="grid gap-2 mb-1">
        <span class="flex justify-between">
            <span>Tags</span>
            <span id="selectedTask"></span>
        </span>
        <span class="flex gap-2">
            <input id="tagInput" placeholder="Tags" style="border: 1px gray solid" class="w-full transition-all rounded-sm w-full p-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            <span class="flex justify-center items-center">
                <button type="button" id="addTagsBtn" class="bg-blue-600 hover:bg-blue-700 transition-all p-1 rounded text-white">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="12" y1="5" x2="12" y2="19"></line><line x1="5" y1="12" x2="19" y2="12"></line></svg>
                </button>
            </span>
            <span class="flex justify-center items-center">
                <button id="addTagsToTasksBtn" class="hidden bg-green-600 transition-all p-1 rounded text-white">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polyline points="20 6 9 17 4 12"></polyline></svg>
                </button>
            </span>
        </span>
    </label>
    <span id="tagError" class="hidden text-red-600 select-none" style="font-size: 14px"></span>
    <div id="tagContainer" class="flex flex-wrap gap-1 mt-2"></div>
</div>