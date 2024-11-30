function addDrawing() {
    var inputsContainer = document.getElementById('drawing-container');
    var inputsCount = inputsContainer.querySelectorAll('input[type="text"]').length + 1;

    var newDiv = document.createElement('div');
    newDiv.setAttribute('class', 'd-flex mb-3');
    var inputWrapper = document.createElement('div');
    inputWrapper.className = 'mr-4 col-lg-8';
    var newInput = document.createElement('input');
    newInput.type = 'text';
    newInput.name = 'drawing' + inputsCount;
    newInput.className = 'form-control';
    newInput.placeholder = 'Введите чертеж';
    inputWrapper.appendChild(newInput);
    newDiv.appendChild(inputWrapper);

    var buttonWrapper = document.createElement('div');
    buttonWrapper.className = 'col-lg-4';
    var newButton = document.createElement('button');
    newButton.type = 'button';
    newButton.setAttribute("onclick", "removeDrawing(this)");
    newButton.className = "btn btn-danger align-self-end";
    var buttonSpan = document.createElement('span');
    buttonSpan.className = "icon text-white-50";
    var buttonIcon = document.createElement('i');
    buttonIcon.classList.add("fas", "fa-trash");
    buttonSpan.appendChild(buttonIcon);
    newButton.appendChild(buttonSpan);

    var buttonSpace = document.createElement('span');
    buttonSpace.textContent = " ";
    newButton.appendChild(buttonSpace);

    var buttonText = document.createElement('span');
    buttonText.className = "text";
    buttonText.textContent = 'Удалить';
    newButton.appendChild(buttonText);

    buttonWrapper.appendChild(newButton);
    newDiv.appendChild(buttonWrapper);

    inputsContainer.appendChild(newDiv);
}

function removeDrawing(button) {
    var parentDiv = button.closest('.d-flex.mb-3');
    if (parentDiv) {
        parentDiv.remove();
    }
}