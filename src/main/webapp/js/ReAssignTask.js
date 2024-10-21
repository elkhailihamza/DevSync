const submitReassign = (form) => {
    if (!reasonInput.val().trim()) {
        return;
    }
    form.submit();
}

const createReasonInput = (id) => {
    reasonForm.attr('data-id', id);
    $('#re-assign_container').append(reasonForm);
}

const reasonInput = $('<input>', {
    type: 'text',
    id: 'reason',
    name: 'reason',
    placeholder: 'Reason as to why'
})

const requestType = $('<select>', {

})

const reasonInputSubmitBtn = $('<button>', {
    type: 'submit',
    innerHTML: 'test'
})

const reasonForm = $('<form>', {
    action: contextPath+'/tasks/assign/create',
    method: 'POST',
    class: 're-assign_form'
}).append(reasonInput, reasonInputSubmitBtn);

$('.re-assign_button').on("click", (e) => {
    createReasonInput($(this).closest('.re-assign_button').data('id'));
})

$('.re-assign_form').on("submit", (e) => {
    e.preventDefault();
    submitReassign(e.target);
})