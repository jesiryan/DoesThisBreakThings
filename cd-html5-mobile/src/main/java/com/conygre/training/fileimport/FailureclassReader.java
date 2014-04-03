package com.conygre.training.fileimport;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.conygre.training.dao.jpa.PersistenceUtil;
import com.conygre.training.entities.Callfailure;
import com.conygre.training.entities.Failureclass;

public class FailureclassReader {

	private FileReader fileReader;

	public FailureclassReader() {
		fileReader = new FileReader();
	}

	public static void main(String[] args) {
		FailureclassReader failureclassTable = new FailureclassReader();
		failureclassTable.persistAllFailureclasses();
		System.out.println("all persisted");
	}

	public void persistAllFailureclasses() {
		PersistenceUtil.persistAll(getAllFailureclassRows());
	}

	// possibly this should be a set. not sure. i've far bigger problems at the
	// moment though.
	public List<Object> getAllFailureclassRows() {
		int length = fileReader.getSheetColumnLength(2);
		ArrayList<Object> failureclasses = new ArrayList<Object>(length);
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
