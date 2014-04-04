//****************************User Story 5*******************************//
    
	/*Pass start and end dateTimes into this function which is called from the submit when you enter start and end dateTimes*/
    function getUS05ResultTemplate(imsi,startdate,enddate) {
    	
        $.ajax({
            url: "tmpl/us05.tmpl",
            dataType: "html",
            success: function( data ) {
            	
                $( "head" ).append( data );
                updateUS05Table(imsi,startdate,enddate);
            }
        });
    }
    
    /* Uses JAX-RS GET to retrieve all imsi Numbers for start and end dateTimes*/
    function updateUS05Table(imsi,startdate,enddate) {

        $.ajax({
            url: "rest/cust/us05/"+imsi+"/"+startdate+"/"+enddate,
            type: "GET",
            cache: false,
            success: function(data) {
            	if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
            		$('#info').empty().append("Information: The query between dates " + startdate + " and " + enddate + " has returned no results.");
					document.forms["us05Form"].reset();
            	} else {
            		$('#info').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
//                    var json = JSON.stringify('{"imsi":'+imsi+', "startdate":'+startdate+', "enddate":'+enddate+', "count":'+data+'}'); 
//                    var obj = eval(json);
//                    alert(obj);
                    $('#results').empty().append(buildUS05ResultsRows(data));
                    document.forms["us05Form"].reset();
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
	function buildUS05ResultsRows(obj) {
	    return _.template( $( "#us05-tmpl" ).html(), {"obj": obj});
	}