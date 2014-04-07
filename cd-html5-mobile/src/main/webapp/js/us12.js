//****************************User Story 12*******************************//
    

	function reloadPage()
	{
			location.reload();
	}


	/*Pass start and end dateTimes into this function which is called from the submit when you enter start and end dateTimes*/
    function getUS12ResultTemplate(startdate,enddate) {
    	
        $.ajax({
            url: "tmpl/us12.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS12Table(startdate,enddate);
            }
        });
    }
    
    function startDateError() {
    	
        $.ajax({
            url: "tmpl/us12.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                badInput();
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers for start and end dateTimes*/
    function updateUS12Table(startdate,enddate) {

        $.ajax({
            url: "rest/net/us12/"+startdate+"/"+enddate,
            type: "GET",
            dataType: "html",
            cache: false,
            success: function(data) {
            	var result=data;
                if (result== 'No Results') {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
                    $('#info').empty().append("Information: The query between dates " + startdate + " and " + enddate + " has returned no results.");
                    document.forms["us12Form"].reset();
                } else {
                   // alert(data);
                    $('#info').addClass("hidden");
                    $('#inner-container').addClass("hidden");
                    $('#eric-multi').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('body').append(data);
                    document.forms["us12Form"].reset();
                }
            },
            error: function(error) {

            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers for start and end dateTimes*/
    function badInput() {

        $.ajax({
            url: "rest/net/us12/"+startdate+"/"+enddate,
            type: "GET",
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Start date was not before end date");
					document.forms["us12Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('#results').empty();
                    document.forms["us12Form"].reset();
                }
            },
            error: function(error) {

            }
        });
    }

	/* Builds the updated table for the callfailures list */
	function buildUS12ResultsRows(obj) {
	    return _.template( $( "#us12-tmpl" ).html(), {"obj": obj});
	}
