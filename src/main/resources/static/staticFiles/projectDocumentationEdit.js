function addDrawing1() {
    var inputsContainer = document.getElementById('drawing-container1');
    var inputsCount = inputsContainer.querySelectorAll('input[type="text"]').length + 1;
    var newDiv = document.createElement('div');

    var newInput = document.createElement('input');
    newInput.type = 'text';
    newInput.name = 'drawing1_' + inputsCount;
    newInput.className = 'w-50 mt-2';
    newInput.placeholder = 'Введите чертеж';
    newInput.setAttribute('th:value', 'drawing1_' + inputsCount);

    var newButton = document.createElement('button');
    newButton.type = 'button';
    newButton.textContent = 'Удалить';
    newButton.setAttribute("onclick", "removeDrawing(this)");
    newButton.className = "bg-success btn btn-succcess border-white mt-3 me-5";

    newDiv.appendChild(newInput);
    newDiv.appendChild(newButton);
    inputsContainer.appendChild(newDiv);
}

function addDrawing2() {
    var inputsContainer = document.getElementById('drawing-container2');
    var inputsCount = inputsContainer.querySelectorAll('input[type="text"]').length + 1;
    var newDiv = document.createElement('div');

    var newInput = document.createElement('input');
    newInput.type = 'text';
    newInput.name = 'drawing2_' + inputsCount;
    newInput.className = 'w-50 mt-2';
    newInput.placeholder = 'Введите чертеж';
    newInput.setAttribute('th:value', 'drawing2_' + inputsCount);

    var newButton = document.createElement('button');
    newButton.type = 'button';
    newButton.textContent = 'Удалить';
    newButton.setAttribute("onclick", "removeDrawing(this)");
    newButton.className = "bg-success btn btn-succcess border-white mt-3 me-5";

    newDiv.appendChild(newInput);
    newDiv.appendChild(newButton);
    inputsContainer.appendChild(newDiv);
}
function removeDrawing(button) {
    var parentDiv = button.parentElement;
    if (parentDiv) {
        parentDiv.remove();
    }
}