function formatState(state) {
    if (!state.id) { return state.text; }
    var imgUrl = $(state.element).data('image-url');
    var value = state.element.value;
    if (imgUrl) {
        return $('<span class="image-container"><img src="' + imgUrl + '" class="img-flag" /><div class="image-overlay">' + value + '</div></span>');
    }
    return state.text;
}

$(document).ready(function() {
    $('.status-select').select2({
        templateResult: formatState,
        templateSelection: formatState,
        escapeMarkup: function(m) { return m; },
        minimumResultsForSearch: Infinity
    });
});

function updateStatus(form) {
    form.submit();
}