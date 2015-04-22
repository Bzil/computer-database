$('#name').keyup(function() {
	console.log($(this).val());
	console.log(validateName(this));
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


function validator() {
    if(validateName('#name') && validateDate('#introduced') && validateDate('#discontinued')) {
    	$('#buttonControl').removeAttr("disabled");
    } else {
    	$('#buttonControl').attr("disabled", "disabled");
}};

function validateName(name) {
	var inputVal = $(name).val();
	var reg = /^[ ]+.*$/;
	return (inputVal != '' && !reg.test(inputVal));
};

function validateDate(date) {
	var inputVal = $(date).val();
	if (!inputVal) { // nothing
		return true;
	}
	pattern = /^\d{2}-\d{2}-\d{4}$/;
	if( !pattern.test(inputVal)) {
		return false;
	}
	subPattern = /\d+/g;
	matches = inputVal.match(subPattern);
	if (local == "fr") {
		day = matches[0];
		month = matches[1]; 
	} else {
		month = matches[0];
		day = matches[1];
	}
	year = matches[2];
	return isCorrectDate(day, month, year);
}


var isCorrectDate = function(day, month, year) {
	day = parseInt(day); month = parseInt(month); year = parseInt(year);
	if (!day || !month || !year || month < 1 || month > 12 || day < 0 || day > 31 || year < 1970) {
		return false;
	}
	if(day == 31 && (month == 11 || month == 4 || month == 6 || month == 9 )) {
		return false;
	} else if (month == 2) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ) {
			if (day == 30 || day == 31) {
				return false;
			}
		} else if (day ==29 || day == 30 || day == 31) {
			return false;
		}
	}
	
	return true;
};