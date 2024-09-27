function addDrawing1() {
    var inputsContainer = document.getElementById('drawing-container1');
    var inputsCount = inputsContainer.querySelectorAll('input[type="text"]').length + 1;
    var newInput = document.createElement('input');
    newInput.type = 'text';
    newInput.name = 'drawing1_' + inputsCount;
    newInput.className = 'w-50 mt-2';
    newInput.placeholder = 'Введите чертеж';
    inputsContainer.appendChild(document.createElement('br'));
    inputsContainer.appendChild(newInput);

    var newButton = document.createElement('button');
    newButton.type = 'button';
    newButton.textContent = 'Удалить';
    newButton.setAttribute("onclick", "removeDrawing(this)");
    newButton.className = "bg-success btn btn-succcess border-white mt-3 me-5";
    inputsContainer.appendChild(newButton);
}

function addDrawing2() {
    var inputsContainer = document.getElementById('drawing-container2');
    var inputsCount = inputsContainer.querySelectorAll('input[type="text"]').length + 1;
    var newInput = document.createElement('input');
    newInput.type = 'text';
    newInput.name = 'drawing2_' + inputsCount;
    newInput.className = 'w-50 mt-2';
    newInput.placeholder = 'Введите чертеж';
    inputsContainer.appendChild(document.createElement('br'));
    inputsContainer.appendChild(newInput);

    var newButton = document.createElement('button');
    newButton.type = 'button';
    newButton.textContent = 'Удалить';
    newButton.setAttribute("onclick", "removeDrawing(this)");
    newButton.className = "bg-success btn btn-succcess border-white mt-3 me-5";
    inputsContainer.appendChild(newButton);
}

function addProjectSection() {
    var projectSectionHidden = document.getElementById("projectSectionHidden");
    projectSectionHidden.removeAttribute("hidden");
    var btn = document.getElementById("btnAddProjectSection");
    btn.setAttribute("hidden", "");
}

function removeDrawing(button) {
    var inputToRemove = button.previousSibling;
    if (inputToRemove && inputToRemove.tagName.toLowerCase() === 'input') {
        inputToRemove.previousSibling.remove();
        inputToRemove.remove();
        button.remove();
    }
}