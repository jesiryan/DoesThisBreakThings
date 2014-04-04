//****************************User Story 9*******************************//
    
	/*Pass start and end dateTimes into this function which is called from the submit when you enter start and end dateTimes*/
    function getUS09ResultTemplate(startdate,enddate) {
    	
        $.ajax({
            url: "tmpl/us09.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS09Table(startdate,enddate);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers for start and end dateTimes*/
    function updateUS09Table(startdate,enddate) {

        $.ajax({
            url: "rest/net/us09/"+startdate+"/"+enddate,
            type: "GET",
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query between dates " + startdate + " and " + enddate + " has returned no results.");
					document.forms["us09Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                	$('#results').empty().append(buildUS09ResultsRows(data));
                    document.forms["us09Form"].reset();
                    console.log("The data being added is : " + data);
                }
            },
            error: function(error) {
//                document.forms["us04Form"].reset();
                //console.log("error updating table -" + error.status);
            }
        });
    }

	/* Builds the updated table for the callfailures list */
	function buildUS09ResultsRows(userStory09Structures) {
	    return _.template( $( "#us09-tmpl" ).html(), {"userStory09Structures": userStory09Structures});
	}