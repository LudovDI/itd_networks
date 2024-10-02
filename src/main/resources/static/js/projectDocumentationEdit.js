function addDrawing() {
    var inputsContainer = document.getElementById('drawing-container');
    var inputElements = inputsContainer.querySelectorAll('input[name^="drawing"]');
    var maxDrawingNumber = 0;

    inputElements.forEach(function (input) {
        var name = input.name;
        var drawingNumber = parseInt(name.replace('drawing', ''));
        if (drawingNumber > maxDrawingNumber) {
            maxDrawingNumber = drawingNumber;
        }
    });

    var newDrawingNumber = maxDrawingNumber + 1;

    var newDiv = document.createElement('div');
    var newInput = document.createElement('input');
    newInput.type = 'text';
    newInput.name = 'drawing' + newDrawingNumber;
    newInput.className = 'w-50 mt-2';
    newInput.placeholder = 'Введите чертеж';

    var newButton = document.createElement('button');
    newButton.type = 'button';
    newButton.textContent = 'Удалить';
    newButton.setAttribute("onclick", "removeDrawing(this)");
    newButton.className = "bg-success btn btn-success border-white mt-3 me-5";

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