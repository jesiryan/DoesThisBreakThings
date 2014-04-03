

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
            url: "rest/callfailures",
            type: "GET",
            cache: false,
            data:{imsi: imsi },
            success: function(data) {
            	
                $('#results').empty().append(buildMemberRows(data));
            },
            error: function(error) {
                //console.log("error updating table -" + error.status);
            }
        });
    }


/* Builds the updated table for the callfailures list */
function buildMemberRows(callfailures) {
    return _.template( $( "#imsi-tmpl" ).html(), {"callfailures": callfailures});
}



 
   
    

