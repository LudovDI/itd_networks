function selectButton(divId) {
    console.log("Div ID:", divId);
    const section = document.getElementById(divId);
    var checkboxes = section.querySelectorAll('input[name^="nameWorks"]');
    checkboxes.forEach(checkbox => {
        checkbox.checked = true;
    });
}