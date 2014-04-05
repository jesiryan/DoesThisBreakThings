package com.conygre.training.fileimport;

import com.conygre.training.entities.*;
import com.conygre.training.dao.jpa.PersistenceUtil;
//import org.dt340a.group6.sprint1.persistence.test.*;
//import com.conygre.training.services.FileUploadServlet;
import com.conygre.training.validation.ValidateExcelFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileReader {
	public Workbook wb;

	public FileReader(String fileName) {
		try {
			// wb = WorkbookFactory.create(new FileInputStream("C:\\upload\\"+
			// fileName));
			wb = WorkbookFactory.create(new FileInputStream(fileName));

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		// processFile();
	}

	public FileReader() {
//		this(FileUploadServlet.pathAndName);
	}


	public Cell getCell(int sheetNumber, int rowNumber, int columnNumber) {
		Sheet sheet = wb.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(columnNumber);
		return cell;
	}

	public int getSheetColumnLength(int sheetNumber) {
		return wb.getSheetAt(sheetNumber).getLastRowNum();
	}

}

