function btnClick() {
    var div = document.getElementById("itdContainer");
    var btn = document.getElementById("btn");
    if (btn.innerText === "Создать новую ИТД") {
        div.removeAttribute("hidden");
        btn.innerText = "Скрыть";
    } else {
        div.setAttribute("hidden", "");
        btn.innerText = "Создать новую ИТД";
    }
}