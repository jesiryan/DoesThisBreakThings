//****************************User Story 11*******************************//

   
	function reloadPage()
	{
			location.reload();
	}



	/*Pass start and end dateTimes into this function which is called from the submit when you enter start and end dateTimes*/
    function getUS11ResultTemplate(startdate,enddate) {
    	
        $.ajax({
            url: "tmpl/us11.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS11Table(startdate,enddate);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers for start and end dateTimes*/
    function updateUS11Table(startdate,enddate) {

        $.ajax({
            url: "rest/net/us11/"+startdate+"/"+enddate,
            type: "GET",
            dataType: "html",
            cache: false,
            success: function(data) {
            	var result=data;
                if (result== 'No Results') {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
                    $('#info').empty().append("Information: The query has returned no results.");
                    document.forms["us11Form"].reset();
                } else {
                   // alert(data);
                    $('#info').addClass("hidden");
                    $('#inner-container').addClass("hidden");
                    $('#eric-multi').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('body').append(data);
                    document.forms["us11Form"].reset();
                }
            },
            error: function(error) {
            }
        });
    }
