function toggleSection(sectionId) {
    const sections = document.querySelectorAll('.form-section');
    sections.forEach(section => section.style.display = 'none');
    const section = document.getElementById(sectionId);
    section.style.display = 'block';
}