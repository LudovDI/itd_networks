document.addEventListener('DOMContentLoaded', function() {
    var menuImage = document.querySelector('.menu-main img');
    var isOpen = false;

    menuImage.addEventListener('click', function() {
        var listItems = document.querySelectorAll('.menu-main li');
        if (isOpen) {
            for (var i = 1; i < listItems.length - 2; i++) {
                listItems[i].style.display = 'none';
            }
            listItems[listItems.length - 1].style.display = 'inline-block';
            isOpen = false;
        } else {
            for (var i = 1; i < listItems.length - 2; i++) {
                listItems[i].style.display = 'inline-block';
            }
            listItems[listItems.length - 1].style.display = 'none';
            isOpen = true;
        }
    });
});
document.addEventListener("DOMContentLoaded", function() {
    var headerText = document.getElementById("headerText").textContent;
    var menuItems = document.querySelectorAll(".menu-main li");
    var lastMenuItem = menuItems[menuItems.length - 1];
    lastMenuItem.textContent = headerText;
});