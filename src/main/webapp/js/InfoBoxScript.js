const infoMsgBtns = document.querySelectorAll(".infoMsgBtn");

infoMsgBtns.forEach((btn) => {
    const parentElement = btn.parentElement;

    btn.addEventListener("click", () => {
        console.log("Click");
        parentElement.remove();
    });
});
