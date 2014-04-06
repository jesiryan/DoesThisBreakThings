package cia.group6.all.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import cia.group6.entities.AllMasterTableRows;
import cia.group6.entities.Callfailure;
import cia.group6.fileimport.CallfailureReader;
import cia.group6.fileimport.CauseReader;
import cia.group6.fileimport.CountryoperatorReader;
import cia.group6.fileimport.EquipmentReader;
import cia.group6.fileimport.FailureclassReader;
import cia.group6.fileimport.FileReader;
import cia.group6.registration.MemberRegistration;
import cia.group6.repositories.CallfailureRepository;
import cia.group6.services.CompactDiscService;
import cia.group6.services.EntitiesEJB;
import cia.group6.validation.ValidateExcelFile;

@Path("/admin")
@RequestScoped
@Stateful
public class SystemAdministratorService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private CallfailureRepository repository;

    @Inject
    MemberRegistration upload;
    
    @Inject
	private EntitiesEJB entEJB;
	
  
	private final String UPLOADED_FILE_PATH = "C:\\Users\\Public\\";
    
    // EXAMPLE METHOD - path consists of user story number and variable to send to query; can also use @QueryParam instead
    @GET
    @Path("/usXX/{imsi}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Callfailure> findCauseCode_EventIDByIMSI(@PathParam("imsi") String IMSI) {	
    	List<Callfailure> callfailures = repository.findCauseCode_EventIDByIMSI(IMSI);
        if (callfailures == null) {
        	return null;
        }
        return callfailures;
    }  
    
    @POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) {
 
		String fileName = "";
 
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("uploadedFile");
 
		for (InputPart inputPart : inputParts) {
 
		 try {
 
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = getFileName(header);
 
			//convert the uploaded file to inputstream
			InputStream inputStream = inputPart.getBody(InputStream.class,null);
 
			byte [] bytes = IOUtils.toByteArray(inputStream);
 
			//constructs upload file path
			fileName = UPLOADED_FILE_PATH + fileName;
 
			writeFile(bytes,fileName);
 
//			FileReader filereader = new FileReader(fileName);
//			if(new ValidateExcelFile(filereader).isXLSValid()){
////				PersistenceUtil.persistAll(fileName);
////				System.out.println("Upload complete @ "
////						+new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
//
//				response.sendRedirect("success.html?invalidCount="+CallFailureReader.getNumOfInvalidRows()+"&validCount="+ CallFailureReader.getNumOfValidRows());
//			}
//			else{
//				System.out.println("The Excel file was not valid");
//			}
			FileReader fileReader = new FileReader(fileName);
			AllMasterTableRows allMasterTableRows = new AllMasterTableRows();
			List<Callfailure> callfailures = getCallFailures(fileReader, allMasterTableRows);
			
			entEJB.persistAllMasterTableRows(allMasterTableRows, callfailures);
			
			System.out.println("Done");			
		  } catch (IOException e) {
			e.printStackTrace();
		  }
 
		}
 
		return Response.status(200)
		    .entity("uploadFile is called, Uploaded file name : " + fileName).build();
 
	}
    
    public List<Callfailure> getCallFailures(FileReader fileReader, AllMasterTableRows allMasterTableRows) {
		FailureclassReader failureClassReader = new FailureclassReader(fileReader);		
		CauseReader causeReader = new CauseReader(fileReader);
		CountryoperatorReader countryOperatorReader = new CountryoperatorReader(fileReader);
		EquipmentReader equipmentReader = new EquipmentReader(fileReader);
		CallfailureReader callFailureReader = new CallfailureReader(fileReader);
		
		allMasterTableRows = new AllMasterTableRows();

		allMasterTableRows.setFailureclasses(failureClassReader.getAllFailureclassRows());
		allMasterTableRows.setCauses(causeReader.getAllEventCauseRows());
		allMasterTableRows.setCountryoperators(countryOperatorReader.getAllCountryoperatorRows());
		allMasterTableRows.setEquipment(equipmentReader.getAllEquipmentRows());

		return callFailureReader.getAllCallfailureRows(allMasterTableRows);
	}
 
	/**
	 * header sample
	 * {
	 * 	Content-Type=[image/png], 
	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	//get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {
 
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
 
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
 
				String[] name = filename.split("=");
 
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}
 
	//save to somewhere
	private void writeFile(byte[] content, String filename) throws IOException {
 
		File file = new File(filename);
 
		if (!file.exists()) {
			file.createNewFile();
		}
 
		FileOutputStream fop = new FileOutputStream(file);
 
		fop.write(content);
		fop.flush();
		fop.close();
 
	}
    
}