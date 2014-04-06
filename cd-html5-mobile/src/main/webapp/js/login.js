//****************************LOGIN*******************************//

	// Length of time (in minutes before logout)
	var maxTime = 15;
    
    function pageRedirect(name, password) {

        $.ajax({
            url: "rest/members/login?name="+name+"&password="+password,
            type: "GET",
            cache: false,
            data:{name: name,password:password },
            success: function(data) {
            	if (data.length > 0) {
            		$('#info').empty();
            		if(data[2]=='Support Engineer'){
            			document.location.href = '/cd-html5-mobile/supEngMenu.html';
            		}
            		if(data[2]=='Customer Service Rep'){
            			document.location.href = '/cd-html5-mobile/custSerRepMenu.html';
            		}
            		if(data[2]=='Network Management Engineer'){
            			document.location.href = '/cd-html5-mobile/netMgmtEngMenu.html';
            		}
            		if(data[2]=='Administrator'){
            			document.location.href = '/cd-html5-mobile/adminMenu.html';
            		}
            		addCurrentUserToLocalStorage(data);
            	} else {
            		$('#userPassWord').val('');
            		$('#info').removeClass("hidden");
            		$('#info').empty().append("<br/>Login for Username: '"+name+"' failed. Please try again.");
                }
            },
            error: function(error) {
            	alert('login error');
//                document.location.href = '/loginFail.html';
            }
        });
    }
    
    function addCurrentUserToLocalStorage(data) {
    	localStorage.setItem("currentUserName", data[0]);
    	localStorage.setItem("currentUserType", data[2]);
    }