function addDocument() {
    var documentContainer = document.getElementById('document-container');
    var inputsCount = documentContainer.querySelectorAll('input[type="text"]').length / 4 + 1;

    var newDivMain = document.createElement('div');
    newDivMain.setAttribute('class', 'd-flex mb-3');
    var newDiv1 = document.createElement('div');
    newDiv1.setAttribute('class', 'mr-5');
    var newLabel1 = document.createElement('label');
    newLabel1.textContent = "Наименование";
    var newInput1 = document.createElement('input');
    newInput1.type = 'text';
    newInput1.name = 'name' + inputsCount;
    newInput1.className = 'form-control';
    newDiv1.appendChild(newLabel1);
    newDiv1.appendChild(document.createElement('br'));
    newDiv1.appendChild(newInput1);
    newDivMain.appendChild(newDiv1);

    var newDiv2 = document.createElement('div');
    newDiv2.setAttribute('class', 'mr-5');
    var newLabel2 = document.createElement('label');
    newLabel2.textContent = "Номер №";
    var newInput2 = document.createElement('input');
    newInput2.type = 'text';
    newInput2.name = 'number' + inputsCount;
    newInput2.className = 'form-control';
    newDiv2.appendChild(newLabel2);
    newDiv2.appendChild(document.createElement('br'));
    newDiv2.appendChild(newInput2);
    newDivMain.appendChild(newDiv2);

    var newDiv3 = document.createElement('div');
    newDiv3.setAttribute('class', 'mr-5');
    var newLabel3 = document.createElement('label');
    newLabel3.textContent = "Дата";
    var newInput3 = document.createElement('input');
    newInput3.type = 'text';
    newInput3.name = 'date' + inputsCount;
    newInput3.className = 'form-control';
    newDiv3.appendChild(newLabel3);
    newDiv3.appendChild(document.createElement('br'));
    newDiv3.appendChild(newInput3);
    newDivMain.appendChild(newDiv3);

    var newDiv4 = document.createElement('div');
    newDiv4.setAttribute('class', 'mr-5');
    var newLabel4 = document.createElement('label');
    newLabel4.textContent = "Наименование организации";
    var newInput4 = document.createElement('input');
    newInput4.type = 'text';
    newInput4.name = 'org' + inputsCount;
    newInput4.className = 'form-control';
    newDiv4.appendChild(newLabel4);
    newDiv4.appendChild(document.createElement('br'));
    newDiv4.appendChild(newInput4);
    newDivMain.appendChild(newDiv4);


    var clonedButton = document.getElementById("removeButton").cloneNode(true);
    newDivMain.appendChild(clonedButton);
    documentContainer.appendChild(newDivMain);
}

function removeAccDoc(button) {
    var parentDiv = button.parentElement;
        if (parentDiv) {
            parentDiv.remove();
        }
}