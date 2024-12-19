function selectButton(divId) {
    const section = document.getElementById(divId);
    var checkboxes = section.querySelectorAll('input[name^="nameWorks"]');

    const allChecked = Array.from(checkboxes).every(checkbox => checkbox.checked);

    checkboxes.forEach(checkbox => {
        checkbox.checked = !allChecked;
    });
}