(function() {
  function initDeleteConfirmations(buttonSelector, modalId, confirmButtonId, getDeleteUrl) {
    const deleteButtons = document.querySelectorAll(buttonSelector);
    if (!deleteButtons || deleteButtons.length === 0) {
      console.warn("No delete buttons found with selector: " + buttonSelector);
      return;
    }

    deleteButtons.forEach(function(deleteButton) {
      const confirmationModal = new bootstrap.Modal(document.getElementById(modalId));
      const confirmDeleteButton = document.getElementById(confirmButtonId);

      if (!confirmationModal || !confirmDeleteButton) {
        console.error("Delete confirmation initialization failed for a button. Check modal and confirm button IDs.");
        return;
      }

      deleteButton.addEventListener('click', function(event) {
        event.preventDefault();
        confirmationModal.show();
      });

      confirmDeleteButton.addEventListener('click', function() {
        const deleteUrl = getDeleteUrl(deleteButton);
        if (deleteUrl) {
          fetch(deleteUrl, {
            method: "POST"
          })
            .then(response => {
              if (response.ok) {
                setTimeout(() => {
                  confirmationModal.hide();
                  window.location.reload();
                }, 500);
              } else {
                console.error("Ошибка при удалении", response);
              }
            })
            .catch(error => {
              console.error("Ошибка при удалении", error);
            });
        } else {
          console.error("Не удалось получить URL удаления!");
          confirmationModal.hide();
        }
      });
    });
  }

    function getDeleteUrlFromForm(button) {
        const form = button.closest('form');
        if (form) {
            return form.action;
        }
        return null;
    }


  window.initModalDeleteConfirmation = function(options) {
      const defaultOptions = {
            buttonSelector: '#deleteButton',  // Измененный селектор
            modalId: 'confirmationModal',
            confirmButtonId: 'confirmDelete',
            getDeleteUrl: getDeleteUrlFromForm, // По умолчанию получаем из action формы
        };

        const settings = {...defaultOptions, ...options};
    initDeleteConfirmations(settings.buttonSelector, settings.modalId, settings.confirmButtonId, settings.getDeleteUrl);
  };

   document.addEventListener('DOMContentLoaded', function(){
      initModalDeleteConfirmation();
    });
})();
