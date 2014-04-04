//****************************User Story 6*******************************//
    
	/*Pass start and end dateTimes into this function which is called from the submit when you enter start and end dateTimes*/
    function getUS06ResultTemplate(imsi) {
    	
        $.ajax({
            url: "tmpl/us06.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS06Table(imsi);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers for start and end dateTimes*/
    function updateUS06Table(imsi) {

        $.ajax({
            url: "rest/cust/us06/"+imsi,
            type: "GET",
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query for IMSI " + imsi + " has returned no results.");
					document.forms["us06Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('#results').empty().append(buildUS06ResultsRows(data));
                    document.forms["us06Form"].reset();
                    console.log("The data being added is : " + data);
                }
            },
            error: function(error) {
//                document.forms["us05Form"].reset();
                //console.log("error updating table -" + error.status);
            }
        });
    }

	/* Builds the updated table for the callfailures list */
	function buildUS06ResultsRows(obj) {
	    return _.template( $( "#us06-tmpl" ).html(), {"obj": obj});
	}