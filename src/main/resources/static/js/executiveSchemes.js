document.addEventListener("DOMContentLoaded", function(event) {
    restoreInputData();
    updateDrawings();

    const projectSectionSelect = document.getElementById("projectSectionSelect");
    if (projectSectionSelect) {
        projectSectionSelect.addEventListener("change", function() {
            updateDrawings();
            saveSelectData();
        });
    } else {
        console.error("Элемент с ID 'projectSectionSelect' не найден.");
    }
});

function updateDrawings() {
    const selectedProjectSectionId = document.getElementById("projectSectionSelect").value;
    const drawingCheckboxes = document.querySelectorAll("input[name^='drawingCheckbox']");

    drawingCheckboxes.forEach(checkbox => {
        if (checkbox.value === selectedProjectSectionId) {
            checkbox.parentElement.style.display = "block";
        } else {
            checkbox.parentElement.style.display = "none";
        }
    });

    saveSelectData();
}

function removeMaterial(button) {
    var parentDiv = button.parentElement;
    if (parentDiv) {
        parentDiv.remove();
    }
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

function saveSelectData() {
    const select = document.getElementById("projectSectionSelect");
    if (select) {
        localStorage.setItem("selectedProjectSection", select.value);
    }
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
    const selectedValue = localStorage.getItem("selectedProjectSection");
    if (selectedValue !== null) {
        const select = document.getElementById("projectSectionSelect");
        if (select) {
            select.value = selectedValue;
        }
    }
}

function clearInputData() {
    localStorage.removeItem("formData");
    clearSelectData();
}

function clearSelectData() {
    localStorage.removeItem("selectedProjectSection");
}

document.querySelectorAll("input[type='text'], input[type='checkbox']").forEach(input => {
    input.addEventListener(input.type === 'checkbox' ? "change" : "input", saveInputData);
});
