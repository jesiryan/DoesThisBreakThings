//****************************User Story 6*******************************//
    
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
                }
            },
            error: function(error) {
            }
        });
    }

	/* Builds the updated table for the callfailures list */
	function buildUS06ResultsRows(obj) {
	    return _.template( $( "#us06-tmpl" ).html(), {"obj": obj});
	}