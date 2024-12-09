function removeResponsible(button) {
    var parentDiv = button.parentElement;
    if (parentDiv) {
        parentDiv.remove();
    }
}

var customerResponsiblesContainer = document.getElementById('customerResponsiblesContainer');
var subcustomerResponsiblesContainer = document.getElementById('subcustomerResponsiblesContainer');
var subcustomerResponsiblesContainer2 = document.getElementById('subcustomerResponsiblesContainer2');
var designerResponsiblesContainer = document.getElementById('designerResponsiblesContainer');
var anotherPersonResponsiblesContainer = document.getElementById('anotherPersonResponsiblesContainer');
var inputCustomer = customerResponsiblesContainer.querySelectorAll('input[name^="customerResponsiblePosition_"]');
var inputSubcustomer = subcustomerResponsiblesContainer.querySelectorAll('input[name^="subcustomerResponsiblePosition_"]');
var inputSubcustomer2 = subcustomerResponsiblesContainer2.querySelectorAll('input[name^="subcustomerResponsible2Position_"]');
var inputDesigner = designerResponsiblesContainer.querySelectorAll('input[name^="designerResponsiblePosition_"]');
var inputAnotherPerson = anotherPersonResponsiblesContainer.querySelectorAll('input[name^="anotherPersonResponsiblePosition_"]');

let maxCustomerResponsibleCounter = 0;
let maxSubcustomerResponsibleCounter = 0;
let maxSubcustomerResponsibleCounter2 = 0;
let maxDesignerResponsibleCounter = 0;
let maxAnotherPersonResponsibleCounter = 0;

inputCustomer.forEach(function (input) {
    var name = input.name;
    var inputNumber = parseInt(name.replace('customerResponsiblePosition_', ''));
    if (inputNumber > maxCustomerResponsibleCounter) {
        maxCustomerResponsibleCounter = inputNumber;
    }
});
inputSubcustomer.forEach(function (input) {
    var name = input.name;
    var inputNumber = parseInt(name.replace('subcustomerResponsiblePosition_', ''));
    if (inputNumber > maxSubcustomerResponsibleCounter) {
        maxSubcustomerResponsibleCounter = inputNumber;
    }
});
inputSubcustomer2.forEach(function (input) {
    var name = input.name;
    var inputNumber = parseInt(name.replace('subcustomerResponsible2Position_', ''));
    if (inputNumber > maxSubcustomerResponsibleCounter2) {
        maxSubcustomerResponsibleCounter2 = inputNumber;
    }
});
inputDesigner.forEach(function (input) {
    var name = input.name;
    var inputNumber = parseInt(name.replace('designerResponsiblePosition_', ''));
    if (inputNumber > maxDesignerResponsibleCounter) {
        maxDesignerResponsibleCounter = inputNumber;
    }
});
inputAnotherPerson.forEach(function (input) {
    var name = input.name;
    var inputNumber = parseInt(name.replace('anotherPersonResponsiblePosition_', ''));
    if (inputNumber > maxAnotherPersonResponsibleCounter) {
        maxAnotherPersonResponsibleCounter = inputNumber;
    }
});

let customerResponsibleCounter = maxCustomerResponsibleCounter + 1;
let subcustomerResponsibleCounter = maxSubcustomerResponsibleCounter + 1;
let subcustomerResponsibleCounter2 = maxSubcustomerResponsibleCounter2 + 1;
let designerResponsibleCounter = maxDesignerResponsibleCounter + 1;
let anotherPersonResponsibleCounter = maxAnotherPersonResponsibleCounter + 1;

function addCustomerResponsible() {
    customerResponsibleCounter++;

    const container = document.getElementById('customerResponsiblesContainer');
    const newResponsible = document.createElement('div');
    newResponsible.classList.add('form-group', 'mb-4');

    newResponsible.innerHTML = `
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h4 class="h4 mb-0 text-gray-800">Представитель заказчика</h4>
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Должность представителя заказчика:</label>
            <input type="text" name="customerResponsiblePosition_${customerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ФИО представителя заказчика:</label>
            <input type="text" name="customerResponsibleName_${customerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-6">Идентификационный номер в национальном реестре специалистов:</label>
            <input type="text" name="customerResponsibleIdNumber_${customerResponsibleCounter}"  class="col-sm-6">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Распорядительный документ:</label>
            <input type="text" name="customerResponsibleNameDocument_${customerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Номер распорядительного документа:</label>
            <input type="text" name="customerResponsibleNumberDocument_${customerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Дата распорядительного документа:</label>
            <input type="text" name="customerResponsibleDateDocument_${customerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-6">Юридическое лицо представителя заказчика:</label>
            <input type="text" name="customerResponsibleNameCompany_${customerResponsibleCounter}"  class="col-sm-6">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ОГРН юридического лица:</label>
            <input type="text" name="customerResponsibleOgrnCompany_${customerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ИНН юридического лица:</label>
            <input type="text" name="customerResponsibleInnCompany_${customerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Адрес юридического лица:</label>
            <input type="text" name="customerResponsibleAdrCompany_${customerResponsibleCounter}"  class="col-sm-8">
        </div>
        <button type="button" onclick="removeResponsible(this)" class="btn btn-danger align-self-end">
            <span class="icon text-white-50">
                <i class="fas fa-trash"></i>
            </span>
            <span class="text">Удалить</span>
        </button>
    `;

    container.appendChild(newResponsible);
}

function addSubcustomerResponsible() {
    subcustomerResponsibleCounter++;

    const container = document.getElementById('subcustomerResponsiblesContainer');
    const newResponsible = document.createElement('div');
    newResponsible.classList.add('form-group', 'mb-4');

    newResponsible.innerHTML = `
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h4 class="h4 mb-0 text-gray-800">Представитель подрядчика</h4>
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Должность представителя подрядчика:</label>
            <input type="text" name="subcustomerResponsiblePosition_${subcustomerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ФИО представителя подрядчика:</label>
            <input type="text" name="subcustomerResponsibleName_${subcustomerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-6">Идентификационный номер в национальном реестре специалистов:</label>
            <input type="text" name="subcustomerResponsibleIdNumber_${subcustomerResponsibleCounter}"  class="col-sm-6">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Распорядительный документ:</label>
            <input type="text" name="subcustomerResponsibleNameDocument_${subcustomerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Номер распорядительного документа:</label>
            <input type="text" name="subcustomerResponsibleNumberDocument_${subcustomerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Дата распорядительного документа:</label>
            <input type="text" name="subcustomerResponsibleDateDocument_${subcustomerResponsibleCounter}"  class="col-sm-8">
        </div>
        <button type="button" onclick="removeResponsible(this)" class="btn btn-danger align-self-end">
            <span class="icon text-white-50">
                <i class="fas fa-trash"></i>
            </span>
            <span class="text">Удалить</span>
        </button>
    `;

    container.appendChild(newResponsible);
}

function addSubcustomerResponsible2() {
    subcustomerResponsibleCounter2++;

    const container = document.getElementById('subcustomerResponsiblesContainer2');
    const newResponsible = document.createElement('div');
    newResponsible.classList.add('form-group', 'mb-4');

    newResponsible.innerHTML = `
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h4 class="h4 mb-0 text-gray-800">Представитель подрядчика по вопросам строительного контроля</h4>
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Должность представителя подрядчика:</label>
            <input type="text" name="subcustomerResponsible2Position_${subcustomerResponsibleCounter2}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ФИО представителя подрядчика:</label>
            <input type="text" name="subcustomerResponsible2Name_${subcustomerResponsibleCounter2}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-6">Идентификационный номер в национальном реестре специалистов:</label>
            <input type="text" name="subcustomerResponsible2IdNumber_${subcustomerResponsibleCounter2}"  class="col-sm-6">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Распорядительный документ:</label>
            <input type="text" name="subcustomerResponsible2NameDocument_${subcustomerResponsibleCounter2}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Номер распорядительного документа:</label>
            <input type="text" name="subcustomerResponsible2NumberDocument_${subcustomerResponsibleCounter2}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Дата распорядительного документа:</label>
            <input type="text" name="subcustomerResponsible2DateDocument_${subcustomerResponsibleCounter2}"  class="col-sm-8">
        </div>
        <button type="button" onclick="removeResponsible(this)" class="btn btn-danger align-self-end">
            <span class="icon text-white-50">
                <i class="fas fa-trash"></i>
            </span>
            <span class="text">Удалить</span>
        </button>
    `;

    container.appendChild(newResponsible);
}

function addDesignerResponsible() {
    designerResponsibleCounter++;

    const container = document.getElementById('designerResponsiblesContainer');
    const newResponsible = document.createElement('div');
    newResponsible.classList.add('form-group', 'mb-4');

    newResponsible.innerHTML = `
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h4 class="h4 mb-0 text-gray-800">Представитель проектировщика</h4>
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Должность представителя проектировщика:</label>
            <input type="text" name="designerResponsiblePosition_${designerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ФИО представителя проектировщика:</label>
            <input type="text" name="designerResponsibleName_${designerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-6">Идентификационный номер в национальном реестре специалистов:</label>
            <input type="text" name="designerResponsibleIdNumber_${designerResponsibleCounter}"  class="col-sm-6">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Распорядительный документ:</label>
            <input type="text" name="designerResponsibleNameDocument_${designerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Номер распорядительного документа:</label>
            <input type="text" name="designerResponsibleNumberDocument_${designerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Дата распорядительного документа:</label>
            <input type="text" name="designerResponsibleDateDocument_${designerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-6">Юридическое лицо представителя проектировщика:</label>
            <input type="text" name="designerResponsibleNameCompany_${designerResponsibleCounter}"  class="col-sm-6">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ОГРН юридического лица:</label>
            <input type="text" name="designerResponsibleOgrnCompany_${designerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ИНН юридического лица:</label>
            <input type="text" name="designerResponsibleInnCompany_${designerResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Адрес юридического лица:</label>
            <input type="text" name="designerResponsibleAdrCompany_${designerResponsibleCounter}"  class="col-sm-8">
        </div>
        <button type="button" onclick="removeResponsible(this)" class="btn btn-danger align-self-end">
            <span class="icon text-white-50">
                <i class="fas fa-trash"></i>
            </span>
            <span class="text">Удалить</span>
        </button>
    `;

 container.appendChild(newResponsible);
}

function addAnotherPersonResponsible() {
    anotherPersonResponsibleCounter++;

    const container = document.getElementById('anotherPersonResponsiblesContainer');
    const newResponsible = document.createElement('div');
    newResponsible.classList.add('form-group', 'mb-4');

    newResponsible.innerHTML = `
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h4 class="h4 mb-0 text-gray-800">Представитель иного лица</h4>
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Должность представителя иного лица:</label>
            <input type="text" name="anotherPersonPosition_${anotherPersonResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ФИО представителя иного лица:</label>
            <input type="text" name="anotherPersonName_${anotherPersonResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-6">Идентификационный номер в национальном реестре специалистов:</label>
            <input type="text" name="anotherPersonIdNumber_${anotherPersonResponsibleCounter}"  class="col-sm-6">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Распорядительный документ:</label>
            <input type="text" name="anotherPersonNameDocument_${anotherPersonResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Номер распорядительного документа:</label>
            <input type="text" name="anotherPersonNumberDocument_${anotherPersonResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Дата распорядительного документа:</label>
            <input type="text" name="anotherPersonDateDocument_${anotherPersonResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-6">Юридическое лицо представителя иного лица:</label>
            <input type="text" name="anotherPersonNameCompany_${anotherPersonResponsibleCounter}"  class="col-sm-6">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ОГРН юридического лица:</label>
            <input type="text" name="anotherPersonOgrnCompany_${anotherPersonResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">ИНН юридического лица:</label>
            <input type="text" name="anotherPersonInnCompany_${anotherPersonResponsibleCounter}"  class="col-sm-8">
        </div>
        <div class="form-group row">
            <label class="col-sm-4">Адрес юридического лица:</label>
            <input type="text" name="anotherPersonAdrCompany_${anotherPersonResponsibleCounter}"  class="col-sm-8">
        </div>
        <button type="button" onclick="removeResponsible(this)" class="btn btn-danger align-self-end">
            <span class="icon text-white-50">
                <i class="fas fa-trash"></i>
            </span>
            <span class="text">Удалить</span>
        </button>
    `;

 container.appendChild(newResponsible);
}