//****************************User Story 8******************************//
    
	/*Pass start and end dateTimes into this function which is called from the submit when you enter start and end dateTimes*/
    function getUS08ResultTemplate(start,end) {
    	
        $.ajax({
            url: "tmpl/us08.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS08Table(start,end);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers for start and end dateTimes*/
    function updateUS08Table(start,end) {

        $.ajax({
            url: "rest/supp/us08/"+start+"/"+end,
            type: "GET",
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query between dates " + start + " and " + end + " has returned no results.");
					document.forms["us08Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('#results').empty().append(buildUS08ResultsRows(data));
                    document.forms["us08Form"].reset();
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
	function buildUS08ResultsRows(obj) {
	    return _.template( $( "#us08-tmpl" ).html(), {"obj": obj});
	}