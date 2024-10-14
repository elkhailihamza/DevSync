const selectStatus = document.querySelectorAll(".status_select");

const sendStatus = (e) => {
    const selectedElement = e.target;
    const statusVal = selectedElement.value;
    const dataId = selectedElement.dataset.id;

    const requestData = {
        status: statusVal,
        id: dataId
    }

    $.ajax({
        url: contextPath+'/tasks/setStatus',
        method: 'POST',
        data: requestData
    })
}

selectStatus.forEach(s => {
    s.addEventListener("change", sendStatus);
})