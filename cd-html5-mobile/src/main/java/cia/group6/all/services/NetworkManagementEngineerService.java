package cia.group6.all.services;

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

import cia.group6.registration.MemberRegistration;
import cia.group6.repositories.CallfailureRepository;
import cia.group6.story.structures.UserStory09Structure;
import cia.group6.story.structures.UserStory10Structure;
import cia.group6.story.structures.UserStory11Structure;
import cia.group6.story.structures.UserStory12Structure;
import cia.group6.story.structures.UserStory13Structure;

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
    public List<UserStory10Structure> findEventCauseByModel( @PathParam("model") String modelString) {

    	List<UserStory10Structure> UserStory10Structures = repository.findEventCauseForModel(modelString);
        if (UserStory10Structures == null) {
        	return null;
        }
        return UserStory10Structures;
    }   

	
	@GET
	@Path("/us11/{start}/{end}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findTop10eNodeBFails(
			@PathParam("start") String startString,
			@PathParam("end") String endString) {
		startString = startString.replaceAll("T", " ");
		endString = endString.replaceAll("T", " ");

		List<UserStory11Structure> userStory11Structures = repository
				.findTop10failsForENodeB(startString, endString);
		
		if (userStory11Structures == null) {
			return "No Results";
		}

		String chart, linkedChart = "";
			
			chart="{ \"chart\": { \"caption\" : \"Total Call Fails\" ,	\"xAxisName\" : \"IMSI\", \"yAxisName\" : \"Call Fails\",	\"animation\":\"0\" }, 	\"data\" : [ ";
			linkedChart = linkedChart + "], "
			+"\"linkeddata\":[";
			
			String table = "<div class='wrapper' id='inner-container' ><table id='results' >"
			+"<tr>"
			 +" <th>MCC</th>"
			  +"<th>MNC</th> "
			  +"<th>cellID</th> "
			  +"<th>Country</th> "
			  +"<th>Operator</th> "
			  +"<th>Total Call Failures</th> "
			+"</tr>";
			
			String eNodeB="";
			for(UserStory11Structure current : userStory11Structures){
				eNodeB = current.getMCC()+""+current.getMNC()+""+current.getCellID();
				chart = chart + "{ \"label\" : \""+eNodeB+"\", \"value\" : \""+current.getTotalFails()+"\" , \"link\":\"newchart-json-"+eNodeB+"\" },";
				
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
				
				table = table +"                    <tr>"
				+"                      <td>" + current.getMCC() + "</td>"
				+"                      <td>" + current.getMNC() + "</td>"
				+"                      <td>" + current.getCellID() + "</td>"
				+"                      <td>" + current.getCountry() + "</td>"
				+"                      <td>" + current.getOperator() + "</td>"
				+"                      <td>" + current.getTotalFails() + "</td>"
				+"                    </tr>";
			
			}
			table = table + "</table></div><div id='eric-multi1'><img src='images/ebottomgrad.jpg' ></div>";
		    linkedChart = linkedChart +"]	"
		    +	"}";
			
			String finalChart = "<script type=\"text/javascript\">"
					+"FusionCharts.setCurrentRenderer('javascript');"
					+ "	var myChart = new FusionCharts(\"Column3D\", \"myChartId\", \"800\", \"600\", \"0\", \"1\");	"			
					+ "	myChart.setJSONData("+chart+linkedChart+");"
					+ "	myChart.render(\"chartContainer\");"
					+ "</script>";
		
		return finalChart + table;
	}
	
	
	
	@GET
	@Path("/us12/{start}/{end}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllCallFailuresBetween(
			@PathParam("start") String startString,
			@PathParam("end") String endString) {
		startString = startString.replaceAll("T", " ");
		endString = endString.replaceAll("T", " ");

		List<UserStory12Structure> userStory12Structures = repository
				.findTop10CountBetweenTimesTotalDuration(startString, endString);
		
		if (userStory12Structures == null) {
			return "No Results";
		}
	
		String chart, linkedChart = "";
		chart="{ \"chart\": { \"caption\" : \"Total Call Fails\" ,	\"xAxisName\" : \"IMSI\", \"yAxisName\" : \"Call Fails\",	\"animation\":\"0\" }, 	\"data\" : [ ";
					linkedChart = linkedChart + "], "
					+"\"linkeddata\":[";
					
					
					
					String table = "<div class='wrapper' id='inner-container'><table id='results'  >"
							+"<tr>"
							 +" <th>IMSI</th>"
							  +"<th>Call Failures</th> "
							+"</tr>";
					
					
					
					for(UserStory12Structure current : userStory12Structures){
						chart = chart + "{ \"label\" : \""+current.getImsi()+"\", \"value\" : \""+current.getCount()+"\" , \"link\":\"newchart-json-"+current.getImsi()+"\" },";
						
						linkedChart = linkedChart +"  {\"id\":\""+current.getImsi()+"\","
								+"  \"linkedchart\":{"
								+"    \"chart\":{"
								+"      \"caption\":\"Failure Types\","
								+"      \"subcaption\":\"For the IMSI "+current.getImsi()+"\","
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
								
											table = table +"                    <tr>"
					+"                      <td>" + current.getImsi() + "</td>"
					+"                      <td>" + current.getCount() + "</td>"
					+"                    </tr>";
					
					}
					table = table + "</table></div><div id='eric-multi1'><img src='images/ebottomgrad.jpg' ></div>";
					linkedChart = linkedChart +"]	"
					+	"}";
					
					String finalChart = "<script type=\"text/javascript\">"
							+"FusionCharts.setCurrentRenderer('javascript');"
							+ "	var myChart = new FusionCharts(\"Column3D\", \"myChartId\", \"800\", \"600\", \"0\", \"1\");	"			
							+ "	myChart.setJSONData("+chart+linkedChart+");"
							+ "	myChart.render(\"chartContainer\");"
							+ "</script>";
				
				
		return finalChart + table;
	}
	
	

	@GET
	@Path("/us13/{start}/{end}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAllTimeTop(
			@PathParam("start") String startString,
			@PathParam("end") String endString) {
		startString = startString.replaceAll("T", " ");
		endString = endString.replaceAll("T", " ");

		List<UserStory13Structure> userStory13Structures = repository.findAllTimeTop(startString, endString);
		
		if (userStory13Structures == null) {
			return "No Results";
		}
		
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
		
		String finalChart ="<script type=\"text/javascript\">"
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
