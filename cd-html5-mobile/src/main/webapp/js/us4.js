//****************************User  Story 4*******************************//
    
	/*Pass imisi into this function which is called from the submit when you enter an imsi*/
    function getIMSIResultTemplate(imsi) {
    	
        $.ajax({
            url: "tmpl/us04.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateImsiTable(imsi);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve given imsi Number */
    function updateImsiTable(imsi) {

        $.ajax({
            url: "rest/cust/us04/"+imsi,
            type: "GET",
            cache: false,
            data:{imsi: imsi },
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query for '" + imsi + "' has returned no results.");
					document.forms["us04Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                	$('#results').empty().append(buildResultsRows(data));
                    document.forms["us04Form"].reset();
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
	function buildResultsRows(callfailures) {
	    return _.template( $( "#us04-tmpl" ).html(), {"callfailures": callfailures});
	}