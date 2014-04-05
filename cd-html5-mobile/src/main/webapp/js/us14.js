//****************************User Story 14*******************************//
    
	/*Pass causeCode and eventId into this function which is called from the submit when you enter causeCode and eventId*/
    function getUS14ResultTemplate(causeCode,eventId) {
    	
        $.ajax({
            url: "tmpl/us14.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS14Table(causeCode,eventId);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers and count*/
    function updateUS14Table(causeCode,eventId) {

        $.ajax({
            url: "rest/supp/us14/"+causeCode+"/"+eventId,
            type: "GET",
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query for " + causeCode + " and " + eventId + " has returned no results.");
					document.forms["us14Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('#results').empty().append(buildUS14ResultsRows(data));
                    document.forms["us14Form"].reset();
                    console.log("The data being added is : " + data);
                }
            },
            error: function(error) {
//                document.forms["us14Form"].reset();
                //console.log("error updating table -" + error.status);
            }
        });
    }

	/* Builds the updated table for the callfailures list */
	function buildUS14ResultsRows(obj) {
	    return _.template( $( "#us14-tmpl" ).html(), {"obj": obj});
	}