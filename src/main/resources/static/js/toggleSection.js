const sectionNames = ['section-general', 'section-customer', 'section-contractor', 'section-designer', 'section-another-person'];
let currentSectionIndex = 0;
let currentActiveButton = null; // Store the currently active button


function toggleSection(sectionId) {
    // Remove active class from the current button
    if(currentActiveButton) {
         currentActiveButton.classList.remove('btn-primary');
        currentActiveButton.classList.add('btn-outline-primary');
    }

    sectionNames.forEach(id => {
        const section = document.getElementById(id);
        if (section) {
            section.style.display = (id === sectionId) ? 'block' : 'none';
        }
    });

     // Get the clicked button
    currentActiveButton = document.querySelector(`button[onclick="toggleSection('${sectionId}')"]`);
      // Change the style of the selected button
    if(currentActiveButton){
        currentActiveButton.classList.remove('btn-outline-primary');
        currentActiveButton.classList.add('btn-primary');
    }
}



function nextSection() {
    currentSectionIndex = (currentSectionIndex + 1) % sectionNames.length;
    toggleSection(sectionNames[currentSectionIndex]);
}

function prevSection() {
    currentSectionIndex = (currentSectionIndex - 1 + sectionNames.length) % sectionNames.length;
    toggleSection(sectionNames[currentSectionIndex]);
}

// Показать первую секцию при загрузке страницы
toggleSection(sectionNames[0]);
