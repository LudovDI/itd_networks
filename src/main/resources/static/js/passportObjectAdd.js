let customerResponsibleCounter = 1;
let subcustomerResponsibleCounter = 1;
let subcustomerResponsibleCounter2 = 1;
let designerResponsibleCounter = 1;
let anotherPersonResponsibleCounter = 1;

function addCustomerResponsible() {
    customerResponsibleCounter++;

    const container = document.getElementById('customerResponsiblesContainer');
    const newResponsible = document.createElement('div');
    newResponsible.classList.add('form-group', 'mb-4');

    newResponsible.innerHTML = `
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h4 class="h4 mb-0 text-gray-800">Представитель заказчика №${customerResponsibleCounter}</h4>
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
            <h4 class="h4 mb-0 text-gray-800">Представитель подрядчика №${subcustomerResponsibleCounter}</h4>
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
            <h4 class="h4 mb-0 text-gray-800">Представитель подрядчика №${subcustomerResponsibleCounter2} по вопросам строительного контроля</h4>
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
            <h4 class="h4 mb-0 text-gray-800">Представитель проектировщика №${designerResponsibleCounter}</h4>
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
            <h4 class="h4 mb-0 text-gray-800">Представитель иного лица №${anotherPersonResponsibleCounter}</h4>
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
    `;

 container.appendChild(newResponsible);
}