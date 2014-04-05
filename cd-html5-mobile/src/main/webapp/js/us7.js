//****************************User Story 7*******************************//
    
	/*Pass start and end dateTimes into this function which is called from the submit when you enter start and end dateTimes*/
    function getUS07ResultTemplate(start,end) {
    	
        $.ajax({
            url: "tmpl/us07.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS07Table(start,end);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers for start and end dateTimes*/
    function updateUS07Table(start,end) {

        $.ajax({
            url: "rest/supp/us07/"+start+"/"+end,
            type: "GET",
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query between dates " + start + " and " + end + " has returned no results.");
					document.forms["us07Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('#results').empty().append(buildUS07ResultsRows(data));
                    document.forms["us07Form"].reset();
                }
            },
            error: function(error) {
            }
        });
    }

	/* Builds the updated table for the callfailures list */
	function buildUS07ResultsRows(obj) {
	    return _.template( $( "#us07-tmpl" ).html(), {"obj": obj});
	}