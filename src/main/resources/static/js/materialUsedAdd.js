function addDocument() {
    var documentContainer = document.getElementById('document-container');
    var inputsCount = documentContainer.querySelectorAll('input[type="text"]').length / 3 + 1;

    var newDivMain = document.createElement('div');
    newDivMain.setAttribute('class', 'm-2 p-2 d-flex bg-success justify-content-between');
    var newDiv1 = document.createElement('div');
    newDiv1.setAttribute('class', 'text-center');
    var newLabel1 = document.createElement('label');
    newLabel1.textContent = "Наименование";
    var newInput1 = document.createElement('input');
    newInput1.type = 'text';
    newInput1.name = 'name' + inputsCount;
    newInput1.className = 'w-75';
    newDiv1.appendChild(newLabel1);
    newDiv1.appendChild(document.createElement('br'));
    newDiv1.appendChild(newInput1);
    newDivMain.appendChild(newDiv1);

    var newDiv2 = document.createElement('div');
    newDiv2.setAttribute('class', 'text-center');
    var newLabel2 = document.createElement('label');
    newLabel2.textContent = "Номер №";
    var newInput2 = document.createElement('input');
    newInput2.type = 'text';
    newInput2.name = 'number' + inputsCount;
    newInput2.className = 'w-75';
    newDiv2.appendChild(newLabel2);
    newDiv2.appendChild(document.createElement('br'));
    newDiv2.appendChild(newInput2);
    newDivMain.appendChild(newDiv2);

    var newDiv3 = document.createElement('div');
    newDiv3.setAttribute('class', 'text-center');
    var newLabel3 = document.createElement('label');
    newLabel3.textContent = "Дата";
    var newInput3 = document.createElement('input');
    newInput3.type = 'text';
    newInput3.name = 'date' + inputsCount;
    newInput3.className = 'w-75';
    newDiv3.appendChild(newLabel3);
    newDiv3.appendChild(document.createElement('br'));
    newDiv3.appendChild(newInput3);
    newDivMain.appendChild(newDiv3);

    var newButton = document.createElement('button');
    newButton.type = 'button';
    newButton.textContent = 'Удалить';
    newButton.setAttribute("class", "bg-success btn btn-succcess border-white mt-3 me-5");
    newButton.setAttribute("onclick", "removeAccDoc(this)");
    newDivMain.appendChild(newButton);
    documentContainer.appendChild(newDivMain);
}
function removeAccDoc(button) {
    var parentDiv = button.parentElement;
        if (parentDiv) {
            parentDiv.remove();
        }
}