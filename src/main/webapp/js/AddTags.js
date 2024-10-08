const tasks = document.querySelectorAll(".task");

tasks.forEach((task) => {
    task.addEventListener("onClick", () => {
        const selectedTask = this.dataset.id;
        const box = document.getElementById("addTaskContainer");
        const input = document.createElement("input");
        box.classList.add("w-96");
        input.placeholder = "Add Tags";
        input.classList.add("p-2", "border-gray-300", "transition-all", "hover:border-blue-500", "rounded-sm", "w-full", "p-3", "text-gray-700", "leading-tight", "focus:outline-none", "focus:shadow-outline");
    })
})