

/* Get the member template */
function getMemberTemplate() {
    $.ajax({
        url: "tmpl/member.tmpl",
        dataType: "html",
        success: function( data ) {
            $( "head" ).append( data );
            updateMemberTable();
        }
    });
}

/* Builds the updated table for the member list */
function buildMemberRows(members) {
    return _.template( $( "#member-tmpl" ).html(), {"members": members});
}

/* Uses JAX-RS GET to retrieve current member list */
function updateMemberTable() {
    $.ajax({
        url: "rest/members",
        cache: false,
        success: function(data) {
            $('#members').empty().append(buildMemberRows(data));
        },
        error: function(error) {
            //console.log("error updating table -" + error.status);
        }
    });
}

function isValidModelInput(modelString){
	if(containsColon(modelString)){
		return false;
	}
	return true;
}

function containsColon(stringInput){
	if(stringInput.contains(';')){
		alert("does contain a ;");
		return true;
	}
	return false;
}

function startBeforeEnd(startDateTime, endDateTime){
	alert("checking startBeforeEnd");
	var start = new Date(startDateTime);
	var end = new Date(endDateTime);
	var today = new Date();
	alert(start);
	if(start < end){
		return true;
	}
	return false;
}

$(document).ready(function() {
	$("#passwordRetype").keyup(validate);
});


function validate() {
	var password = $("#password").val();
	var passwordRetype = $("#passwordRetype").val();
	
	if(password == passwordRetype) {
		$("#validate-status").text("passwords match :)"); 
		document.getElementById("validate-status").style.color= "#14993E";
	}
	else {
		$("#validate-status").text("passwords don't match");  
		document.getElementById("validate-status").style.color= "#CC0000";
	}
}

/*
Attempts to register a new member using a JAX-RS POST.  The callbacks
the refresh the member table, or process JAX-RS response codes to update
the validation errors.
 */
function registerMember(memberData) {
    //clear existing  msgs
    $('span.invalid').remove();
    $('span.success').remove();

    $.ajax({
        url: 'rest/members',
        contentType: "application/json",
        dataType: "json",
        type: "POST",
        data: JSON.stringify(memberData),
        success: function(data) {
            //console.log("Member registered");

            //clear input fields
            $('#reg')[0].reset();

            //mark success on the registration form
            $('#formMsgs').append($('<span class="success">Member Registered</span>'));

           // updateMemberTable();
        },
        error: function(error) {
            if ((error.status == 409) || (error.status == 400)) {
                //console.log("Validation error registering user!");

                var errorMsg = $.parseJSON(error.responseText);

                $.each(errorMsg, function(index, val) {
                	$('#formMsgs').append($('<span class="invalid">' + val + '</span>').insertAfter($('#' + index)));
                });
            } else {
                //console.log("error - unknown server issue");
                $('#formMsgs').append($('<span class="invalid">Unknown server error</span>'));
            }
        }
    });
}
