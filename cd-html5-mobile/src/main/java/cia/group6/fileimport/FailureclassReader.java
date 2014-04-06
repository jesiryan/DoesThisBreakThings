package cia.group6.fileimport;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

//import cia.group6.dao.jpa.PersistenceUtil;
import cia.group6.entities.Callfailure;
import cia.group6.entities.Failureclass;

public class FailureclassReader {

	private FileReader fileReader;

	public FailureclassReader(FileReader fileReader) {
		this.fileReader = fileReader;
//		fileReader = new FileReader();
	}

	public static void main(String[] args) {
//		FailureclassReader failureclassTable = new FailureclassReader();
//		failureclassTable.persistAllFailureclasses();
//		System.out.println("all persisted");
	}

//	public void persistAllFailureclasses() {
//		PersistenceUtil.persistAll(getAllFailureclassRows());
//	}

	// possibly this should be a set. not sure. i've far bigger problems at the
	// moment though.
	public List<Failureclass> getAllFailureclassRows() {
		System.out.println("Begin getting all failureClasses");
		int length = fileReader.getSheetColumnLength(2);
		ArrayList<Failureclass> failureclasses = new ArrayList<Failureclass>(length);
		for (int i = 1; i < length + 1; i++) {
			failureclasses.add(getOneFailureclassRow(i));
		}
		return failureclasses;
	}

	public Failureclass getOneFailureclassRow(int rowNumber) {
		return new Failureclass.Builder()
				.failureclass(getFailureclassCell(rowNumber))
				.description(getDescription(rowNumber)).build();
	}

	public int getFailureclassCell(int rowNumber) {
		Cell cell = fileReader.getCell(2, rowNumber, 0);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public String getDescription(int rowNumber) {
		Cell cell = fileReader.getCell(2, rowNumber, 1);
		return cell.getStringCellValue();
	}

}
