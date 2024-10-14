let intervalId;
const createdAtDate = document.getElementById("createdAT");
const dueDateDate = document.getElementById("dueDate");

function updateCreatedAtField(now) {
    if (createdAtDate != null) {
        createdAtDate.value = now.toISOString().slice(0, 16);
    }
    return now;
}

function updateDueDateField(now, createdAt) {
    const dueDate = new Date(createdAt);
    dueDate.setHours((dueDate.getHours() + 2));
    if (dueDateDate) {
        dueDateDate.value = dueDate.toISOString().slice(0, 16);
    }
}

function updateDateTimeFields() {
    const now = new Date();
    const createdAt = updateCreatedAtField(now);
    updateDueDateField(now, createdAt);
}

function startAutoUpdate() {
    clearInterval(intervalId);
    intervalId = setInterval(updateDateTimeFields, 60000);
}

function stopAutoUpdate() {
    clearInterval(intervalId);
}

window.addEventListener("load", () => {
    updateDateTimeFields();
    startAutoUpdate();

    if (createdAtDate) {
        createdAtDate.addEventListener("change", stopAutoUpdate);
    }
    if (dueDateDate) {
        dueDateDate.addEventListener("change", stopAutoUpdate);
    }
})