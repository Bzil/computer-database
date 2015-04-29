//Event handling
//Onkeydown
$(document).keydown(function (e) {
    switch (e.keyCode) {
        //DEL key
        case 46:
            if ($(".editMode").is(":visible") && $(".cb:checked").length != 0) {
                $.fn.deleteSelected();
            }
            break;
        //E key (CTRL+E will switch to edit mode)
        case 69:
            if (e.ctrlKey) {
                $.fn.toggleEditMode();
            }
            break;
    }
});
