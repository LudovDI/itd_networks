var normativeContainer = document.getElementById('normativeContainer');
var nextNameWorksContainer = document.getElementById('nextNameWorksContainer');
var inputNormative = normativeContainer.querySelectorAll('input[name^="normative"]');
var inputNextNameWorks = nextNameWorksContainer.querySelectorAll('input[name^="nextNameWorks"]');
var maxNormativeNumber = 0;
var maxNextNameWorksNumber = 0;
inputNormative.forEach(function (input) {
    var name = input.name;
    var normativeNumber = parseInt(name.replace('normative', ''));
    if (normativeNumber > maxNormativeNumber) {
        maxNormativeNumber = normativeNumber;
    }
});
inputNextNameWorks.forEach(function (input) {
    var name = input.name;
    var nextNameWorksNumber = parseInt(name.replace('nextNameWorks', ''));
    if (nextNameWorksNumber > maxNextNameWorksNumber) {
        maxNextNameWorksNumber = nextNameWorksNumber;
    }
});
var newNormativeNumber = maxNormativeNumber + 1;
var newNextNameWorksNumber = maxNextNameWorksNumber + 1;

function addNormative() {
    const newDiv = document.createElement('div');
    newDiv.classList.add('row', 'col-lg-12', 'mb-3');

    newDiv.innerHTML = `
        <div class="col-lg-6">
            <label class="m-0">Нормативка</label>
        </div>
        <div class="col-lg-5">
            <input type="text" name="normative${newNormativeNumber}" class="form-control">
        </div>
        <div class="col-lg-1">
            <button type="button" onclick="removeDiv(this)" class="btn btn-danger">
            <span class="icon text-white-50">
                <i class="fas fa-trash"></i>
            </span>
            </button>
        </div>
    `;

    normativeContainer.appendChild(newDiv);
    newNormativeNumber++;
}
function addNextNameWorks() {
    const newDiv = document.createElement('div');
    newDiv.classList.add('row', 'col-lg-12', 'mb-3');

    newDiv.innerHTML = `
        <div class="col-lg-6">
            <label class="m-0">Наименование последующей работы</label>
        </div>
        <div class="col-lg-5">
            <input type="text" name="nextNameWorks${newNextNameWorksNumber}" class="form-control">
        </div>
        <div class="col-lg-1">
            <button type="button" onclick="removeDiv(this)" class="btn btn-danger">
            <span class="icon text-white-50">
                <i class="fas fa-trash"></i>
            </span>
            </button>
        </div>
    `;

    nextNameWorksContainer.appendChild(newDiv);
    newNextNameWorksNumber++;
}

function removeDiv(button) {
    var parentDiv = button.closest('.row.col-lg-12.mb-3');
    if (parentDiv) {
        parentDiv.remove();
    }
}