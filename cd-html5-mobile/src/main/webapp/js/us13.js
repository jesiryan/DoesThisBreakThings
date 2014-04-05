
   //****************************User Story 13*******************************//
   
	function reloadPage()
	{
			location.reload();
	}





    /*This function is called from the submit*/
    function getUS13ResultTemplate(startdate,enddate) {
        $.ajax({
            url: "tmpl/us13.tmpl",
            dataType: "html",
            success: function( data ) {
                $( "head" ).append( data );
                updateUS13Table(startdate,enddate);
            }
        });
    }
   
    /* Uses JAX-RS GET to retrieve all country, operator, cell combinations*/
    function updateUS13Table(startdate,enddate) {

        $.ajax({
            url: "rest/net/us13/"+startdate+"/"+enddate,
            type: "GET",
            dataType: "html",
            cache: false,
           
            success: function(data) {
                if (data.length < 1) {
                    $('#info').removeClass("hidden");
                    $('#hidden-container').addClass("hidden");
                    $('#info').empty().append("Information: The query has returned no results.");
                    document.forms["us13Form"].reset();
                } else {
                   // alert(data);
                    $('#info').addClass("hidden");
                    $('#inner-container').addClass("hidden");
                    $('#hidden-container').removeClass("hidden");
                    $('body').append(data);
                    document.forms["us13Form"].reset();
                }
            },
            error: function(error) {
            	
            }
        });
    }
 





