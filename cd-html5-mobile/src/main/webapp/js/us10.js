//****************************User Story 10*******************************//
    
	/*Pass start and end dateTimes into this function which is called from the submit when you enter start and end dateTimes*/
    function getUS10ResultTemplate(modelString) {
    	
        $.ajax({
            url: "tmpl/us10.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS10Table(modelString);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers for start and end dateTimes*/
    function updateUS10Table(modelString) {

        $.ajax({
            url: "rest/net/us10/"+modelString,
            type: "GET",
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query for model " + modelString + " has returned no results.");
					document.forms["us10Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('#results').empty().append(buildUS10ResultsRows(data));
                    document.forms["us10Form"].reset();
                   // console.log("The data being added is : " + data[0] + data[1] + data[2] + data[3]+ data[4] + data[5]);
                }
            },
            error: function(error) {
//                document.forms["us05Form"].reset();
                //console.log("error updating table -" + error.status);
            }
        });
    }

	/* Builds the updated table for the callfailures list */
	function buildUS10ResultsRows(obj) {
	    return _.template( $( "#us10-tmpl" ).html(), {"obj": obj});
	}