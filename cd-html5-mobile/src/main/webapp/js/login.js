//****************************LOGIN*******************************//

    
    /* Uses JAX-RS GET to retrieve given imsi Number */
    function pageRedirect(name, password) {

        $.ajax({
            url: "rest/members/login/"+name+"/"+password,
            type: "GET",
            cache: false,
            success: function(data) {
            	alert(data);
            	if (data.length < 1) {
            		document.location.href = '/cd-html5-mobile/loginFail.html';
            	} else {
            		document.location.href = '/cd-html5-mobile/adminMenu.html';
                }
            },
            error: function(error) {
//                document.location.href = '/loginFail.html';
                //console.log("error updating table -" + error.status);
            }
        });
    }

	/* Builds the updated table for the callfailures list */
//	function buildResultsRows(callfailures) {
//	    return _.template( $( "#us04-tmpl" ).html(), {"callfailures": callfailures});
//	}