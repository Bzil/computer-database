$('#computerName').keyup(function() {
	if(validateName($(this))) {
        $(this).css({'color' : '#4DA585'});
    } else {
    	$(this).css({'color' : '#FF0000'});
    }
	validator();
});

$('#introduced').keyup(function() {
    if(validateDate($(this))) {
    	$(this).css({'color' : '#4DA585'});
    } else {
    	$(this).css({'color' : '#FF0000'});
    }
    validator();
});

$('#discontinued').keyup(function() {
    if(validateDate($(this))) {
        $(this).css({'color' : '#4DA585'});
    } else {
    	$(this).css({'color' : '#FF0000'});
    }
    validator();
});


function validator(){
    if(validateName('#computerName') && validateDate('#introduced') && validateDate('#discontinued')) {
    	$('#buttonControl').removeAttr("disabled");
    } else {
    	$('#buttonControl').attr("disabled", "disabled");
}};

function validateDate(name){
	var inputVal = $(name).val();
	var reg = /^(0[1-9]|1[0-9]|2[0-8]|29((?=-([0][13-9]|1[0-2])|(?=-(0[1-9]|1[0-2])-([0-9]{2}(0[48]|[13579][26]|[2468][048])|([02468][048]|[13579][26])00))))|30(?=-(0[13-9]|1[0-2]))|31(?=-(0[13578]|1[02])))-(0[1-9]|1[0-2])-[0-9]{4}$/;
    return (!inputVal || reg.test(inputVal));
};

function validateName(name){
	var inputVal = $(name).val();
	var reg = /^[ ]+.*$/;
	return (!inputVal || !reg.test(inputVal));
};