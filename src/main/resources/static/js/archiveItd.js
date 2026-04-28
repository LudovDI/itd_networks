function changeCheckbox() {
    var div = document.getElementById("checkList");
    var inputsArray = div.querySelectorAll('input[type="checkbox"]');
    var allChecked = true;

    inputsArray.forEach(function(input) {
        if (!input.checked) {
            allChecked = false;
        }
    });

    var saveItdDiv = document.getElementById("saveItd");

    if (allChecked) {
        div.setAttribute("hidden", "");
        saveItdDiv.removeAttribute("hidden");
    }
}