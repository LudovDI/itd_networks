// Функция для переключения видимости секций формы
function toggleSection(sectionId) {
    // Скрыть все секции
    const sections = document.querySelectorAll('.form-section');
    sections.forEach(section => section.style.display = 'none');

    // Показать только выбранную секцию
    const section = document.getElementById(sectionId);
    section.style.display = 'block';
}

// Счетчик для уникальных имен полей ответственных лиц
let responsibleCounter = 1;

// Функция для добавления полей "Ответственное лицо"
function addCustomerResponsible() {
    responsibleCounter++;

    const container = document.getElementById('customerResponsiblesContainer');
    const newResponsible = document.createElement('div');
    newResponsible.classList.add('form-group', 'mb-4');

    newResponsible.innerHTML = `
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h4 class="h4 mb-0 text-gray-800">Представитель заказчика №${responsibleCounter}</h4>
        </div>
        <div class="form-group row">
            <label for="responsiblePosition_${responsibleCounter}" class="col-sm-4">Должность представителя заказчика:</label>
            <input type="text" id="responsiblePosition_${responsibleCounter}" name="responsiblePositionPosition_${responsibleCounter}" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="responsibleName_${responsibleCounter}" class="col-sm-4">ФИО представителя заказчика:</label>
            <input type="text" id="responsibleName_${responsibleCounter}" name="responsibleName_${responsibleCounter}" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="responsibleID_${responsibleCounter}" class="col-sm-6">Идентификационный номер в национальном реестре специалистов:</label>
            <input type="text" id="responsibleID_${responsibleCounter}" name="responsibleID_${responsibleCounter}" required class="col-sm-6">
        </div>
        <div class="form-group row">
            <label for="responsibleDocument_${responsibleCounter}" class="col-sm-4">Распорядительный документ:</label>
            <input type="text" id="responsibleDocument_${responsibleCounter}" name="responsibleDocument_${responsibleCounter}" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="responsibleNumber_${responsibleCounter}" class="col-sm-4">Номер распорядительного документа:</label>
            <input type="text" id="responsibleNumber_${responsibleCounter}" name="responsibleNumber_${responsibleCounter}" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="responsibleDate_${responsibleCounter}" class="col-sm-4">Дата распорядительного документа:</label>
            <input type="text" id="responsibleDate_${responsibleCounter}" name="responsibleDate_${responsibleCounter}" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="responsibleCompany_${responsibleCounter}" class="col-sm-6">Юридическое лицо представителя заказчика:</label>
            <input type="text" id="responsibleCompany_${responsibleCounter}" name="responsibleCompany_${responsibleCounter}" required class="col-sm-6">
        </div>
        <div class="form-group row">
            <label for="responsibleOGRN_${responsibleCounter}" class="col-sm-4">ОГРН юридического лица:</label>
            <input type="text" id="responsibleOGRN_${responsibleCounter}" name="responsibleOGRN_${responsibleCounter}" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="responsibleINN_${responsibleCounter}" class="col-sm-4">ИНН юридического лица:</label>
            <input type="text" id="responsibleINN_${responsibleCounter}" name="responsibleINN_${responsibleCounter}" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="responsibleAddress_${responsibleCounter}" class="col-sm-4">Адрес юридического лица:</label>
            <input type="text" id="responsibleAddress_${responsibleCounter}" name="responsibleAddress_${responsibleCounter}" required class="col-sm-8">
        </div>
    `;

    container.appendChild(newResponsible);
}

// Функция для добавления полей "Ответственное лицо"
function addSubcustomerResponsible() {
    responsibleCounter++;

    const container = document.getElementById('subcustomerResponsiblesContainer');
    const newResponsible = document.createElement('div');
    newResponsible.classList.add('form-group', 'mb-4');

    newResponsible.innerHTML = `
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h4 class="h4 mb-0 text-gray-800">Представитель подрядчика №1</h4>
        </div>
        <div class="form-group row">
            <label for="subcustomerResponsiblePosition_1" class="col-sm-4">Должность представителя подрядчика:</label>
            <input type="text" id="subcustomerResponsiblePosition_1" name="subcustomerResponsiblePosition_1" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="subcustomerResponsibleName_1" class="col-sm-4">ФИО представителя подрядчика:</label>
            <input type="text" id="subcustomerResponsibleName_1" name="subcustomerResponsibleName_1" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="subcustomerResponsibleID_1" class="col-sm-6">Идентификационный номер в национальном реестре специалистов:</label>
            <input type="text" id="subcustomerResponsibleID_1" name="subcustomerResponsibleID_1" required class="col-sm-6">
        </div>
        <div class="form-group row">
            <label for="subcustomerResponsibleDocument_1" class="col-sm-4">Распорядительный документ:</label>
            <input type="text" id="subcustomerResponsibleDocument_1" name="subcustomerResponsibleDocument_1" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="subcustomerResponsibleNumber_1" class="col-sm-4">Номер распорядительного документа:</label>
            <input type="text" id="subcustomerResponsibleNumber_1" name="subcustomerResponsibleNumber_1" required class="col-sm-8">
        </div>
        <div class="form-group row">
            <label for="subcustomerResponsibleDate_1" class="col-sm-4">Дата распорядительного документа:</label>
            <input type="text" id="subcustomerResponsibleDate_1" name="subcustomerResponsibleDate_1" required class="col-sm-8">
        </div>
    `;

    container.appendChild(newResponsible);
}
