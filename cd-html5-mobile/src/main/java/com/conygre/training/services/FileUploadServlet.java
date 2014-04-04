package com.conygre.training.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.conygre.training.entities.AllMasterTableRows;
import com.conygre.training.entities.Callfailure;
import com.conygre.training.fileimport.CallfailureReader;
import com.conygre.training.fileimport.CauseReader;
import com.conygre.training.fileimport.CountryoperatorReader;
import com.conygre.training.fileimport.EquipmentReader;
import com.conygre.training.fileimport.FailureclassReader;
import com.conygre.training.fileimport.FileReader;
//import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
//import org.dt340a.group6.sprint1.persistence.UploadService;
import com.conygre.training.validation.ValidateExcelFile;

@MultipartConfig
@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String pathAndName;
	public static List<Object> callFailures;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// PrintWriter writer = response.getWriter();
		// String fileName = "";
		// for(Part part : request.getParts()) {
		// String name = part.getName();
		// writer.println("<p>"+name);
		// InputStream is = request.getPart(name).getInputStream();
		// fileName = fileName+getUploadedFileName(part);
		// FileOutputStream fos = new FileOutputStream("/"+fileName);
		// int data = 0;
		// while((data = is.read()) != -1) {
		// fos.write(data);
		// }
		// fos.close();
		// is.close();
		// writer.println("<p>end");
		// }

		uploadFile(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		// uploadFile(request, response);
	}

	/**
	 * uploadFile method used to upload file to server.
	 * 
	 ***/
	private void uploadFile(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String fileName = "/home/ubuntu/temp/";
		String fileName = "/call failures/";
		for (Part part : request.getParts()) {
			String name = part.getName();
			InputStream is = request.getPart(name).getInputStream();
			fileName = fileName + getUploadedFileName(part);
			pathAndName = fileName;
			FileOutputStream fos = new FileOutputStream(fileName);
			int data = 0;
			while ((data = is.read()) != -1) {
				fos.write(data);
			}
			fos.close();
			is.close();
			// response.sendRedirect("success.html");
		}
		/*
 * 
 */
		FileReader filereader = new FileReader(fileName);
		if (new ValidateExcelFile(filereader).isXLSValid()) {
			// UploadService service = new UploadService();
			// service.persistAll(fileName);
			// PersistenceUtil.persistAll(fileName);
			// System.out.println("Upload complete @ "
			// +new
			// SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));

			response.sendRedirect("success.html?invalidCount="
					+ CallfailureReader.getNumOfInvalidRows() + "&validCount="
					+ CallfailureReader.getNumOfValidRows());
			
			callFailures = getCallFailures();
			Callfailure failure1 = ((Callfailure) callFailures.get(0));
			Callfailure failure2 = ((Callfailure) callFailures.get(40));
			System.out.println(failure1.getDateTime()+"" + failure1.getIMSI());
			System.out.println(failure2.getDateTime()+"" + failure2.getIMSI());
		} else {
			System.out.println("The Excel file was not valid");
		}
	}

	/**
	 * Method used to get uploaded file name. This will parse following string
	 * and get filename Content-Disposition: form-data; name="content";
	 * filename="a.txt"
	 **/
	private String getUploadedFileName(Part p) {
		String file = "", header = "Content-Disposition";
		String[] strArray = p.getHeader(header).split(";");
		for (String split : strArray) {
			if (split.trim().startsWith("filename")) {
				file = split.substring(split.indexOf('=') + 1);
				file = file.trim().replace("\"", "");
				System.out.println("File name : " + file);
			}
		}
		return file;
	}

	public List<Object> getCallFailures() {
		AllMasterTableRows allMasterTableRows;
		FailureclassReader failureClassReader;
		CauseReader causeReader;
		CountryoperatorReader countryOperatorReader;
		EquipmentReader equipmentReader;
		CallfailureReader callFailureReader;
		

		failureClassReader = new FailureclassReader();
		causeReader = new CauseReader();
		countryOperatorReader = new CountryoperatorReader();
		equipmentReader = new EquipmentReader();
		callFailureReader = new CallfailureReader();
		allMasterTableRows = new AllMasterTableRows();

		allMasterTableRows.setFailureclasses(failureClassReader
				.getAllFailureclassRows());

		allMasterTableRows.setCauses(causeReader.getAllEventCauseRows());

		allMasterTableRows.setCountryoperators(countryOperatorReader
				.getAllCountryoperatorRows());

		allMasterTableRows.setEquipment(equipmentReader.getAllEquipmentRows());

		return callFailureReader.getAllCallfailureRows(allMasterTableRows);
	}

}
