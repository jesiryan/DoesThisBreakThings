//****************************LOGIN*******************************//

    
    /* Uses JAX-RS GET to retrieve given imsi Number */
    function pageRedirect(name, password) {

        $.ajax({
            url: "rest/members/login?name="+name+"&password="+password,
            type: "GET",
            cache: false,
            data:{name: name,password:password },
            success: function(data) {
            	alert('before');
            	if (data.length < 1) {
            		alert('false');
            		document.location.href = '/cd-html5-mobile/loginFail.html';
            	} else {
            		alert('true');
            		document.location.href = '/cd-html5-mobile/adminMenu.html';
                }
            },
            error: function(error) {
            	alert('error');
//                document.location.href = '/loginFail.html';
                //console.log("error updating table -" + error.status);
            }
        });
    }

	/* Builds the updated table for the callfailures list */
//	function buildResultsRows(callfailures) {
//	    return _.template( $( "#us04-tmpl" ).html(), {"callfailures": callfailures});
//	}