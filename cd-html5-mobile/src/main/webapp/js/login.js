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
            
            	if (data.length > 0) {
            		alert('true');    
            		alert(data[2]);  
            		if(data[2]=='Support Engineer'){
            			document.location.href = '/cd-html5-mobile/supEngMenu.html';
            		}
            		if(data[2]=='Customer Service'){
            			document.location.href = '/cd-html5-mobile/custSerRepMenu.html';
            		}
            		if(data[2]=='Network Engineer'){
            			document.location.href = '/cd-html5-mobile/netMgmtEngMenu.html';
            		}
            		if(data[2]=='Administrator'){
            			document.location.href = '/cd-html5-mobile/adminMenu.html';
            		}
            	} else {
            		alert('false');
            		document.location.href = '/cd-html5-mobile/loginFail.html';
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