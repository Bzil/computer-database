//On load
$(function () {
    // Default: hide edit mode
    $(".editMode").hide();

    // Click on "selectall" box
    $("#selectall").click(function () {
        $('.cb').prop('checked', this.checked);
    });

    // Click on a checkbox
    $(".cb").click(function () {
        if ($(".cb").length == $(".cb:checked").length) {
            $("#selectall").prop("checked", true);
        } else {
            $("#selectall").prop("checked", false);
        }
        if ($(".cb:checked").length != 0) {
            $("#deleteSelected").enable();
        } else {
            $("#deleteSelected").disable();
        }
    });

});


// Function setCheckboxValues
(function ($) {

    $.fn.setCheckboxValues = function (formFieldName, checkboxFieldName) {

        var str = $('.' + checkboxFieldName + ':checked').map(function () {
            return this.value;
        }).get().join();

        $(this).attr('value', str);

        return this;
    };

}(jQuery));

// Function toggleEditMode
(function ($) {

    $.fn.toggleEditMode = function () {
        if ($(".editMode").is(":visible")) {
            $(".editMode").hide();
            $("#editComputer").text("Edit");
        }
        else {
            $(".editMode").show();
            if (local == "fr") {
                $("#editComputer").text("Vue");
            }
            else {
                $("#editComputer").text("View");
            }
        }
        return this;
    };

}(jQuery));


// Function delete selected: Asks for confirmation to delete selected computers, then submits it to the deleteForm
(function ($) {
    $.fn.deleteSelected = function () {
        if (local == "fr") {
            var confirmText = "Confirmez-vous la suppression des ces ordinateurs ?";
        }
        else {
            var confirmText = "Are you sure you want to delete the selected computers?"
        }
        if (confirm(confirmText)) {
            $('#deleteForm input[name=selection]').setCheckboxValues('selection', 'cb');
            $('#deleteForm').submit();
        }
    };
}(jQuery));

