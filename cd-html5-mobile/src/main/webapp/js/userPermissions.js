//**************************** User Permissions *******************************//

	function logout() {
		clearUser();
    	window.location.replace("index.html");
	}
	
	function home() {
    	var userType = localStorage.getItem("currentUserType");
    	if(userType == "Customer Service Rep") {
        	window.location.replace("custSerRepMenu.html");
    	}
    	else if(userType == "Support Engineer") {
        	window.location.replace("supEngMenu.html");    		
    	}
    	else if(userType == "Network Management Engineer") {
        	window.location.replace("netMgmtEngMenu.html");    		
    	}
    	else if(userType == "Administrator") {    		
        	window.location.replace("adminMenu.html");
    	}
    	else {
        	window.location.replace("index.html");
    	}
	}

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
    
    function networkManagementEngineerCheck() {
    	var userType = localStorage.getItem("currentUserType");
    	if(userType == "Network Management Engineer") {
        	var userName = localStorage.getItem("currentUserName");
        	setUserType(userType, userName);
    	} else {
        	window.location.replace("index.html");
    	}
    }
    
    function administratorCheck() {
    	var userType = localStorage.getItem("currentUserType");
    	if(userType == "Administrator") {
        	var userName = localStorage.getItem("currentUserName");
        	setUserType(userType, userName);
    	} else {
        	window.location.replace("index.html");
    	}
    }
    
    function setUserType(type, name) {
    	var div = document.getElementById("userType");
    	div.innerHTML=type+" ("+name+")";
    }