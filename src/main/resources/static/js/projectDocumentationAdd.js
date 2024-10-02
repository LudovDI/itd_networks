function addDrawing() {
    var inputsContainer = document.getElementById('drawing-container');
    var inputsCount = inputsContainer.querySelectorAll('input[type="text"]').length + 1;
    var newInput = document.createElement('input');
    newInput.type = 'text';
    newInput.name = 'drawing' + inputsCount;
    newInput.className = 'w-50 mt-2';
    newInput.placeholder = 'Введите чертеж';
    inputsContainer.appendChild(document.createElement('br'));
    inputsContainer.appendChild(newInput);

    var newButton = document.createElement('button');
    newButton.type = 'button';
    newButton.textContent = 'Удалить';
    newButton.setAttribute("onclick", "removeDrawing(this)");
    newButton.className = "bg-success btn btn-success border-white mt-3 me-5";
    inputsContainer.appendChild(newButton);
}

function removeDrawing(button) {
    var inputToRemove = button.previousSibling;
    if (inputToRemove && inputToRemove.tagName.toLowerCase() === 'input') {
        inputToRemove.previousSibling.remove();
        inputToRemove.remove();
        button.remove();
    }
}