function toggleSection(sectionId) {
    // Скрыть все секции
    const sections = document.querySelectorAll('.form-section');
    sections.forEach(section => section.style.display = 'none');

    // Показать только выбранную секцию
    const section = document.getElementById(sectionId);
    section.style.display = 'block';
}