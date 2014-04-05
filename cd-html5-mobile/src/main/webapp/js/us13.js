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
            data:{},
            success: function(data) {
            	alert(data);
//            	if (data.length < 1) {
//                    $('#info').removeClass("hidden");
//                    $('#hidden-container').addClass("hidden");
//            		$('#info').empty().append("Information: The query has returned no results.");
//					document.forms["us13Form"].reset();
//            	} else {
//            		$('#info').addClass("hidden");
//                    $('#hidden-container').removeClass("hidden");
//                    $('#results').empty().append(buildUS13ResultsRows(data));
//                    document.forms["us13Form"].reset();
//                }
            },
            error: function(error) {
            	alert(data);
            }
        });
    }

	/* Builds the updated table for the callfailures list */
	function buildUS13ResultsRows(obj) {
	    return _.template( $( "#us13-tmpl" ).html(), {"obj": obj});
	}