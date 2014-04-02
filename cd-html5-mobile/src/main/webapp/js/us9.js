/* Get the member template */
function getMemberTemplate() {
   $.ajax({
       url: "tmpl/member.tmpl",
       dataType: "html",
       success: function( data ) {
           $( "head" ).append( data );
           updateMemberTable();
       }
   });
}
function getCdTemplate() {
   $.ajax({
       url: "tmpl/cd.tmpl",
       dataType: "html",
       success: function( data ) {
           $( "head" ).append( data );
           updateCdTable();
       }
   });
}

/* Builds the updated table for the member list */
function buildMemberRows(members) {
   return _.template( $( "#imsi-tmpl" ).html(), {"members": members});
}
/* Builds the updated table for the cd list */
function buildCdRows(cds) {
   return _.template( $( "#cd-tmpl" ).html(), {"cds": cds});
}
/* Uses JAX-RS GET to retrieve current member list */
function updateMemberTable() {
   $.ajax({
       url: "rest/members",
       cache: false,
       success: function(data) {
           $('#results').empty().append(buildMemberRows(data));
       },
       error: function(error) {
           //console.log("error updating table -" + error.status);
       }
   });
}
function updateCdTable() {
   $.ajax({
       url: "rest/cds",
       cache: false,
       success: function(data) {
           $('#cds').empty().append(buildCdRows(data));
       },
       error: function(error) {
           //console.log("error updating table -" + error.status);
       }
   });
}

/*
Attempts to register a new member using a JAX-RS POST.  The callbacks
the refresh the member table, or process JAX-RS response codes to update
the validation errors.
*/
function getUS04Result(imsi) {
   //clear existing  msgs
//    $('span.invalid').remove();
//    $('span.success').remove();

   $.ajax({
   	url: "/rest/callfailures/us04/?_=344930000000011",
       type: "GET",
       contentType: "application/json",
       //data: JSON.stringify({ imsi: 344930000000011}),
       success: function (result) {
           alert(result.Result);
       
       	updateImsiTable();
       }
   });
}
   
   //****************************User  Story 09*******************************//
   
   function getIMSIResultTemplate() {
       $.ajax({
           url: "tmpl/imsi.tmpl",
           dataType: "html",
           success: function( data ) {
               $( "head" ).append( data );
               updateMemberTable();
           }
       });
   }
   
   function updateImsiTable() {
       $.ajax({
           url: "rest/callfailures/us04",
           cache: false,
           success: function(data) {
               $('#results').empty().append(buildRows(data));
           },
           error: function(error) {
               //console.log("error updating table -" + error.status);
           }
       });
   }
   
   

   /* Builds the updated table for the member list */
   function buildRows(callFailure) {
       return _.template( $( "#imsi-tmpl" ).html(), {"callfailures": callFailures});
   }
   
   
   
   
   
