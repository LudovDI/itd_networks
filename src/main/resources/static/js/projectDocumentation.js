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

function addDrawing() {
    const newDrawing = document.createElement('div');
    newDrawing.classList.add('d-flex', 'mb-3');

    newDrawing.innerHTML = `
        <div class="mr-4 col-lg-8">
            <input type="text" name="drawing${newDrawingNumber}" class="form-control" placeholder="Введите чертеж">
        </div>
        <div class="col-lg-4">
            <button type="button" onclick="removeDrawing(this)" class="btn btn-danger align-self-end">
                <span class="icon text-white-50">
                    <i class="fas fa-trash"></i>
                </span>
                <span class="text">Удалить</span>
            </button>
        </div>
    `;

    inputsContainer.appendChild(newDrawing);
    newDrawingNumber++;
}

function removeDrawing(button) {
    var parentDiv = button.closest('.d-flex.mb-3');
    if (parentDiv) {
        parentDiv.remove();
    }
}