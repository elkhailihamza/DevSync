const taskForm = document.getElementById("task-form");

taskForm.addEventListener("submit", (e) => {
    e.preventDefault();
    let dateDifference = createdAtDate - dueDateDate;
    if (tags.length < 3) {
        return addTagError("Add at least 3 tags!");
    }
    if (dateDifference < 3) {
        document.getElementById("main-error").innerText = "Due date cannot surpass 3 days after the creation date!"
        return;
    }
    prepareTagsForSubmission();
    e.target.submit();
});