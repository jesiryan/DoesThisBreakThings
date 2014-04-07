package cia.group6.fileimport;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FileReader {
	
	public Workbook wb;

	public FileReader() {
		
	}

	public FileReader(String pathAndName) {
		try {
			wb = WorkbookFactory.create(new FileInputStream(pathAndName));
			System.out.println("Workbook set to: "+pathAndName);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
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