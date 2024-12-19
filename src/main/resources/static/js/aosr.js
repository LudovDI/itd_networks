function addSelect() {
    var select1Text = document.getElementById('nameWorks').value;
    var select2 = document.getElementById('nextNameWorks');
    var nextNameWorksToAosr = document.getElementById('nextNameWorksToAosr');
    select2.innerHTML = '';

    var nameWorksMap = JSON.parse(document.getElementById('nameWorks').getAttribute('data-map'));
    var nextNameWorksToAosrValue = (nextNameWorksToAosr != null) ? nextNameWorksToAosr.value : null;

    var arrayOption = nameWorksMap[select1Text] || [];

    arrayOption.forEach(function(el) {
        var optionAdd = document.createElement('option');
        var myArray = el.split("_");
        optionAdd.value = myArray[0];
        optionAdd.text = myArray[1];
        select2.appendChild(optionAdd);
    });

    if (nextNameWorksToAosrValue != null && arrayOption.includes(nextNameWorksToAosrValue)) {
      select2.value = nextNameWorksToAosrValue.split("_")[0];
    } else {
        select2.value = arrayOption[0].split("_")[0];
    }

    var normativeContainer = document.getElementById('normativeContainer');
    normativeContainer.innerHTML = '';

    var normativeMap = JSON.parse(normativeContainer.getAttribute('data-map'));

    var arrayP = normativeMap[select1Text] || [];

    arrayP.forEach(function(el) {
        var pAdd = document.createElement('p');
        pAdd.innerText = el;
        normativeContainer.appendChild(pAdd);
    });
}

var projectSectionContainer = document.getElementById('projectSectionContainer');
let maxProjectSectionCounter = 0;
var divRow = projectSectionContainer.querySelectorAll('div[id^="projectSectionRow"]');

divRow.forEach(function (div) {
    var id = div.id;
    var divNumber = parseInt(id.replace('projectSectionRow', ''));
    if (divNumber > maxProjectSectionCounter) {
        maxProjectSectionCounter = divNumber;
    }
});

let projectSectionCounter = maxProjectSectionCounter + 1;

function addProjectSection() {

    const originalSection = document.querySelector("#projectSectionContainer .row");

    if (originalSection) {
        const newProjectSection = originalSection.cloneNode(true);

        newProjectSection.id = `projectSectionRow${projectSectionCounter}`;

        const select = newProjectSection.querySelector("select[name^='projectSectionSelect']");
        select.name = `projectSectionSelect${projectSectionCounter}`;

        const checkboxes = newProjectSection.querySelectorAll("input[type='checkbox'][name^='drawingCheckbox']");
        checkboxes.forEach(checkbox => {
            const nameParts = checkbox.name.split('-');

            if (nameParts.length > 1) {
                nameParts[nameParts.length - 1] = projectSectionCounter;
            } else {
                nameParts.push(projectSectionCounter);
            }

            checkbox.name = nameParts.join('-');
            checkbox.checked = false;
        });

        const toggleLink = newProjectSection.querySelector("a[href^='#collapseCardDrawings']");
        const newHrefId = `collapseCardDrawings${projectSectionCounter}`;
        toggleLink.href = `#${newHrefId}`;
        toggleLink.setAttribute("aria-controls", newHrefId);

        const collapseDiv = newProjectSection.querySelector("div.collapse[id^='collapseCardDrawings']");
        collapseDiv.id = newHrefId;

        const hiddenField = document.createElement('input');
        hiddenField.type = 'hidden';
        hiddenField.name = `projectSectionHidden${projectSectionCounter}`;
        hiddenField.value = projectSectionCounter;
        newProjectSection.appendChild(hiddenField);

        select.addEventListener("change", function() {
            updateDrawings();
            saveSelectData();
        });

        document.getElementById('projectSectionContainer').appendChild(newProjectSection);
    }
    projectSectionCounter++;
}

function removeDiv(button) {
    var parentDiv = button.closest('.row.mb-2');
    if (parentDiv) {
        parentDiv.remove();
    }
}

document.addEventListener("DOMContentLoaded", function(event) {
    addSelect();
    restoreInputData();
    updateDrawings();

    const projectSectionSelects = document.querySelectorAll("select[name^='projectSectionSelect']");
    projectSectionSelects.forEach(select => {
        select.addEventListener("change", function() {
            updateDrawings();
            saveSelectData();
        });
    });
});

function updateDrawings() {
    const projectSectionSelects = document.querySelectorAll("select[name^='projectSectionSelect']");

    projectSectionSelects.forEach(select => {
        const selectedProjectSectionId = select.value;
        const sectionContainer = select.closest(".row");

        if (sectionContainer) {
            const drawingsContainer = sectionContainer.querySelector("div[id^='collapseCardDrawings']");
            const drawingCheckboxes = drawingsContainer.querySelectorAll("input[name^='drawingCheckbox']");

            drawingCheckboxes.forEach(checkbox => {
                if (checkbox.value === selectedProjectSectionId) {
                    checkbox.parentElement.style.display = "block";
                } else {
                    checkbox.parentElement.style.display = "none";
                    checkbox.checked = false;
                }
            });
        }
    });

    saveSelectData();
}

function saveSelectData() {
    const selects = document.querySelectorAll("select");
    const selectData = {};

    selects.forEach(select => {
        selectData[select.name] = select.value;
    });

    localStorage.setItem("selectedOptions", JSON.stringify(selectData));
}

function saveInputData() {
    const inputs = document.querySelectorAll("input[type='text'], input[type='checkbox']");
    const data = {};

    inputs.forEach(input => {
        if (input.type === "checkbox") {
            data[input.name] = input.checked;
        } else {
            data[input.name] = input.value;
        }
    });

    localStorage.setItem("formData", JSON.stringify(data));
    saveSelectData();
}

function restoreInputData() {
    const data = JSON.parse(localStorage.getItem("formData"));
    if (data) {
        Object.keys(data).forEach(name => {
            const input = document.querySelector(`input[name="${name}"]`);
            if (input) {
                if (input.type === "checkbox") {
                    input.checked = data[name];
                } else {
                    input.value = data[name];
                }
            }
        });
    }
    restoreSelectData();
}

function restoreSelectData() {
    const selectData = JSON.parse(localStorage.getItem("selectedOptions"));
    if (selectData) {
        const selects = document.querySelectorAll("select");
        selects.forEach(select => {
            if (selectData[select.name] !== undefined) {
                select.value = selectData[select.name];
            }
        });
    }
}

document.addEventListener("DOMContentLoaded", () => {
    restoreInputData();

    document.querySelectorAll("input[type='text'], input[type='checkbox']").forEach(input => {
        input.addEventListener(input.type === 'checkbox' ? "change" : "input", saveInputData);
    });

    document.querySelectorAll("select").forEach(select => {
        select.addEventListener("change", saveSelectData);
    });
});

function clearInputData() {
    localStorage.removeItem("formData");
    clearSelectData();
}

function clearSelectData() {
    localStorage.removeItem("selectedOptions");
}