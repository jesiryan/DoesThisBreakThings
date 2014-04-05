//****************************User Story 13*******************************//
    
	/*This function is called from the submit*/
    function getUS13ResultTemplate() {
    	
        $.ajax({
            url: "tmpl/us13.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS13Table();
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all country, operator, cell combinations*/
    function updateUS13Table() {

        $.ajax({
            url: "rest/net/us13/",
            type: "GET",
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query has returned no results.");
					document.forms["us13Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('#results').empty().append(buildUS13ResultsRows(data));
                    document.forms["us13Form"].reset();
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
	function buildUS13ResultsRows(obj) {
	    return _.template( $( "#us13-tmpl" ).html(), {"obj": obj});
	}