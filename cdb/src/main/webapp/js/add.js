$('#computerName').keyup(function() {
    var inputVal = $(this).val();
    var reg = /^[ ]+.*$/;
    if(!reg.test(inputVal)) {
        $(this).css({'color' : '#4DA585'});
        $('#add').show();
    } else {
    	$(this).css({'color' : '#FF0000'});
    	$('#add').hide();
    }
});

$('#introduced').keyup(function() {
    var inputVal = $(this).val();
    var dateReg = /^(0[1-9]|1[0-9]|2[0-8]|29((?=-([0][13-9]|1[0-2])|(?=-(0[1-9]|1[0-2])-([0-9]{2}(0[48]|[13579][26]|[2468][048])|([02468][048]|[13579][26])00))))|30(?=-(0[13-9]|1[0-2]))|31(?=-(0[13578]|1[02])))-(0[1-9]|1[0-2])-[0-9]{4}$/;
    if(!inputVal || dateReg.test(inputVal)) {
        $(this).css({'color' : '#4DA585'});
        $('#add').show();
    } else {
    	$(this).css({'color' : '#FF0000'});
    	$('#add').hide();
    }
});

$('#discontinued').keyup(function() {
    var inputVal = $(this).val();
    var dateReg = /^(0[1-9]|1[0-9]|2[0-8]|29((?=-([0][13-9]|1[0-2])|(?=-(0[1-9]|1[0-2])-([0-9]{2}(0[48]|[13579][26]|[2468][048])|([02468][048]|[13579][26])00))))|30(?=-(0[13-9]|1[0-2]))|31(?=-(0[13578]|1[02])))-(0[1-9]|1[0-2])-[0-9]{4}$/;
    if(!inputVal || dateReg.test(inputVal)) {
        $(this).css({'color' : '#4DA585'});
        $('#add').show();
    } else {
    	$(this).css({'color' : '#FF0000'});
    	$('#add').hide();
    }
});