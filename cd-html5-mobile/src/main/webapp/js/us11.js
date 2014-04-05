//****************************User Story 11*******************************//
    
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
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query between dates " + startdate + " and " + enddate + " has returned no results.");
					document.forms["us11Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('#results').empty().append(buildUS11ResultsRows(data));
                    document.forms["us11Form"].reset();
                    console.log("The data being added is : " + data[0] + data[1] + data[2] + data[3]+ data[4] + data[5]);
                }
            },
            error: function(error) {
//                document.forms["us05Form"].reset();
                //console.log("error updating table -" + error.status);
            }
        });
    }

	/* Builds the updated table for the callfailures list */
	function buildUS11ResultsRows(obj) {
	    return _.template( $( "#us11-tmpl" ).html(), {"obj": obj});
	}