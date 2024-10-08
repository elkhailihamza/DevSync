let intervalId;

function updateCreatedAtField(now) {
    document.getElementById("createdAT").value = now.toISOString().slice(0, 16);
    return now;
}

function updateDueDateField(now, createdAt) {
    const dueDate = new Date(createdAt);
    dueDate.setHours((dueDate.getHours() + 2));
    document.getElementById("dueDate").value = dueDate.toISOString().slice(0, 16);
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

window.onload = function() {
    updateDateTimeFields();
    startAutoUpdate();

    document.getElementById("createdAT").addEventListener("change", stopAutoUpdate);
    document.getElementById("dueDate").addEventListener("change", stopAutoUpdate);
};