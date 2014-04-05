//**************************** User Permissions *******************************//

    function clearUser() {
    	localStorage.clear();
    }
    
    function customerServiceRepCheck() {
    	var userType = localStorage.getItem("currentUserType");
    	if(userType == "Customer Service Rep" || userType == "Support Engineer" || userType == "Network Management Engineer") {
        	var userName = localStorage.getItem("currentUserName");
        	setUserType(userType, userName);
    	} else {
        	window.location.replace("index.html");
    	}
    }
    
    function supportEngineerCheck() {
    	var userType = localStorage.getItem("currentUserType");
    	if(userType == "Support Engineer" || userType == "Network Management Engineer") {
        	var userName = localStorage.getItem("currentUserName");
        	setUserType(userType, userName);
    	} else {
        	window.location.replace("index.html");
    	}
    }
    
    function NetworkManagementEngineerCheck() {
    	var userType = localStorage.getItem("currentUserType");
    	if(userType == "Network Management Engineer") {
        	var userName = localStorage.getItem("currentUserName");
        	setUserType(userType, userName);
    	} else {
        	window.location.replace("index.html");
    	}
    }
    
    
    function setUserType(type, name) {
    	var div = document.getElementById("userType");
    	div.innerHTML=type+" ("+name+")";
//        $.ajax(
//        	function(type) {
//        		$('#userType').empty().append(type+" ("+name+")");
//        	}
//        );
    }