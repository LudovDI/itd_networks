function addSelect() {
    var select = document.getElementById('material1');
    if (select == null) {
        select = document.getElementById('material2');
    }
    var clone = select.cloneNode(true);
    var container = document.getElementById('select-container');
    var newDiv = document.createElement('div');
    newDiv.appendChild(document.createElement('br'));
    newDiv.appendChild(clone);

    var newButton = document.createElement('button');
    newButton.type = 'button';
    newButton.textContent = 'Удалить';
    newButton.setAttribute("onclick", "removeMaterial(this)");
    newButton.setAttribute("class", "btn btn-denger border-white");
    newDiv.appendChild(newButton);
    container.appendChild(newDiv);
}
function updateDrawings() {
    var projectSection = document.getElementById("projectSectionSelect").value;
    var drawingsList1 = document.getElementById("drawingsList1");
    var drawingsList2 = document.getElementById("drawingsList2");

    var option1 = document.querySelector("#projectSectionSelect option:first-child");
    var option2 = document.querySelector("#projectSectionSelect option:last-child");
    var table1 = drawingsList1.querySelector("table");
    var table2 = drawingsList2.querySelector("table");

    table1.style.display = (projectSection === option1.value) ? "block" : "none";
    table2.style.display = (projectSection === option2.value) ? "block" : "none";
}
function removeMaterial(button) {
    var parentDiv = button.parentElement;
    if (parentDiv) {
        parentDiv.remove();
    }
}
document.addEventListener("DOMContentLoaded", function(event) {
    updateDrawings();
});