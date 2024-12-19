var documentContainer = document.getElementById('document-container');
var inputElements = documentContainer.querySelectorAll('input[name^="name"]');
var maxAccDocNumber = 0;
inputElements.forEach(function (input) {
    var name = input.name;
    var accDocNumber = parseInt(name.replace('name', ''));
    if (accDocNumber > maxAccDocNumber) {
        maxAccDocNumber = accDocNumber;
    }
});
var newAccDocNumber = maxAccDocNumber + 1;

function addDocument() {
    var newDiv = document.createElement('div');
    newDiv.setAttribute('class', 'd-flex mb-3');

    newDiv.innerHTML = `
        <div class="mr-5">
            <label>Наименование</label><br>
            <input type="text" name="name${newAccDocNumber}" class="form-control" required>
        </div>
        <div class="mr-5">
            <label>Номер №</label><br>
            <input type="text" name="number${newAccDocNumber}" class="form-control">
        </div>
        <div class="mr-5">
            <label>Дата</label><br>
            <input type="text" name="date${newAccDocNumber}" class="form-control">
        </div>
        <div class="mr-5">
            <label>Наименование организации</label><br>
            <input type="text" name="org${newAccDocNumber}" class="form-control">
        </div>
        <button type="button" id="removeButton" onclick="removeAccDoc(this)" class="btn btn-danger align-self-end">
            <span class="icon text-white-50">
                <i class="fas fa-trash"></i>
            </span>
            <span class="text">Удалить</span>

        </button>
    `;

    documentContainer.appendChild(newDiv);
    newAccDocNumber++;
}

function removeAccDoc(button) {
    var parentDiv = button.parentElement;
    if (parentDiv) {
        parentDiv.remove();
    }
}