//****************************User  Story 4*******************************//
    
	/*Pass imisi into this function which is called from the submit when you enter an imsi*/
    function getIMSIResultTemplate(imsi) {
    	
        $.ajax({
            url: "tmpl/imsi.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateMemberTable(imsi);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve given imsi Number */
    function updateMemberTable(imsi) {

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
                	$('#results').empty().append(buildMemberRows(data));
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
	function buildMemberRows(callfailures) {
	    return _.template( $( "#imsi-tmpl" ).html(), {"callfailures": callfailures});
	}