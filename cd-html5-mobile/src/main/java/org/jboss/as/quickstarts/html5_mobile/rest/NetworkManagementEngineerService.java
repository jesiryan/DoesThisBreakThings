package org.jboss.as.quickstarts.html5_mobile.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.as.quickstarts.html5_mobile.data.CallfailureRepository;
import org.jboss.as.quickstarts.html5_mobile.service.MemberRegistration;

import com.conygre.training.entities.query.UserStory09Structure;
import com.conygre.training.entities.query.UserStory10Structure;
import com.conygre.training.entities.query.UserStory11Structure;
import com.conygre.training.entities.query.UserStory12Structure;
import com.conygre.training.entities.query.UserStory13Structure;

@Path("/net")
@RequestScoped
@Stateful
public class NetworkManagementEngineerService {
	@Inject
	private Logger log;

	@Inject
	private Validator validator;

	@Inject
	private CallfailureRepository repository;

	@Inject
	MemberRegistration upload;


	@GET
	@Path("/us09/{start}/{end}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserStory09Structure> findCauseCode_EventIDByIMSI(
			@PathParam("start") String startString,
			@PathParam("end") String endString) {
		startString = startString.replaceAll("T", " ");
		endString = endString.replaceAll("T", " ");

    	List<UserStory09Structure> userStory09Structures = repository.findCountBetweenTimesTotalDuration(startString, endString);
        if (userStory09Structures == null) {
        	return null;
        }
        return userStory09Structures;
    }
    @GET
    @Path("/us10/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserStory10Structure> findEventCauseByModel(	@PathParam("model") String modelString) {

    	List<UserStory10Structure> UserStory10Structures = repository.findEventCauseForModel(modelString);
        if (UserStory10Structures == null) {
        	return null;
        }
        return UserStory10Structures;
    }   

	@GET
	@Path("/us11/{start}/{end}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserStory11Structure> findTop10eNodeBFails(
			@PathParam("start") String startString,
			@PathParam("end") String endString) {
		startString = startString.replaceAll("T", " ");
		endString = endString.replaceAll("T", " ");

		List<UserStory11Structure> userStory11Structures = repository
				.findTop10failsForENodeB(startString, endString);
		if (userStory11Structures == null) {
			return null;
		}
		return userStory11Structures;
	}

	@GET
	@Path("/us12/{start}/{end}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserStory12Structure> findAllCallFailuresBetween(
			@PathParam("start") String startString,
			@PathParam("end") String endString) {
		startString = startString.replaceAll("T", " ");
		endString = endString.replaceAll("T", " ");

		List<UserStory12Structure> userStory12Structures = repository
				.findTop10CountBetweenTimesTotalDuration(startString, endString);
		if (userStory12Structures == null) {
			return null;
		}
		return userStory12Structures;
	}

	@GET
	@Path("/us13/")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllTimeTop() {

		List<UserStory13Structure> userStory13Structures = repository
				.findAllTimeTop();
		
		String labelTag= "",valueSet1 ="", valueSet2 ="",linkedChart= "", eNodeB = "";
		for(UserStory13Structure current : userStory13Structures){
			eNodeB = current.getMCC()+""+current.getMNC()+""+current.getCellID();
		
			labelTag += "{\"label\":\""+eNodeB+"\"},";
			valueSet1+= "{ \"value\":"+current.getTotalFails()+",  \"link\":\"newchart-json-"+eNodeB+"\"},";
			valueSet2+= "{\"value\":"+ current.getPercentOfTotal() +" },";
			
			linkedChart = linkedChart +"  {\"id\":\""+eNodeB+"\","
				    +"  \"linkedchart\":{"
				    +"    \"chart\":{"
				    +"      \"caption\":\"Failure Types\","
				    +"      \"subcaption\":\"For the Global eNodeB "+eNodeB+"\","
				    +"      \"xaxisname\":\"Type\","
				    +"      \"yaxisname\":\"Number\","
				    +"      \"animation\":\"0\""
				    +"    },"
				    +"    \"data\":[{ \"label\":\"EMERGENCY\", \"value\":\""+current.getClass0()+"\" },"
				    +"            { \"label\":\"HIGH PRIORITY ACCESS\", \"value\":\""+current.getClass1()+"\" },"
				    +"            { \"label\":\"MT ACCESS\", \"value\":\""+current.getClass2()+"\" },"
				    +"            { \"label\":\"MO SIGNALLING\", \"value\":\""+current.getClass3()+"\" },"
				    +"            { \"label\":\"MO DATA\", \"value\":\""+current.getClass4()+"\" }]"
				    +"  }"
				    +"},";	
		}
		
		String newChart =  "{\"chart\":{"
			    + "\"xaxisname\":\"Global eNodeB\","
			    + "  \"seriesnameintooltip\":0,"
			    + "  \"showvalues\":1,"
			    + "  \"caption\":\"Call Failure Analysis\","
			    + "  \"animation\":0,"
			    + "  \"formatenumberscale\":0,"
			    + "  \"showborder\":\"0\""
			    + " },"
			    + " \"categories\":["
			    + "   {"
			    + "     \"category\":["
			    + labelTag
			    +"         ]"
			    +"    }"
			    +"    ],"
			    +"    \"dataset\":["
			    +"       {"
			    +"          \"seriesname\":\"Call Fails\","
			    +"          \"data\":["
			    + valueSet1
			    +" ]	"		    
			    +"     },"
		        +"     {"
		        +"        \"seriesname\":\"Percentage of total\", "       
		        +"       \"parentyaxis\":\"S\","
		        +"        \"data\":["
		        + valueSet2
		        +" ],"		         
		        +" \"renderas\":\"Line\""
		        +"}"
		        +" ],"
		        +"\"linkeddata\":["
		        +linkedChart
			    +"]"
			    +"	}";
		
		String finalChart = "<script type=\"text/javascript\" src=\"FusionCharts.js\"></script>"					
				+ "<div id=\"chartContainer\" align=\"center\">FusionCharts will load here</div>"
				+ "<script type=\"text/javascript\">"
				+"FusionCharts.setCurrentRenderer('javascript');"
				+ "	var myChart = new FusionCharts(\"MSColumn3DLineDY\", \"myChartId\", \"800\", \"600\", \"0\", \"1\");	"	
				+ "FusionCharts(\"myChartId\").configureLink ("
				+ "		  {"
				+ "		    type : \"Pie3d\","
				+ "		    overlayButton:"
				+ "		    {    "
				+ "		      message: 'Back'"
				+ "		    }"
				+ "		  }, 0);"
				+ "	myChart.setJSONData("+newChart+");"
				+ "	myChart.render(\"chartContainer\");"
				+ "</script>";
		
			return finalChart;
	}
}
