package com.conygre.training.fileimport;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.conygre.training.entities.*;

public class CallfailureReader {

	private FileReader fileReader;
	private List<Object> failureclasses;
	private List<Object> causes;
	private List<Object> countryoperators;
	private List<Object> equipmentList;
	private static int numOfInvalidRows;
	private static int numOfValidRows;
	
	public CallfailureReader() {
		fileReader = new FileReader();
	}

	// possibly this should be a set. not sure.
	public List<Object> getAllCallfailureRows(
			AllMasterTableRows allMasterTableRows) {
		setLocalTableLists(allMasterTableRows);
		int length = fileReader.getSheetColumnLength(0);
		numOfInvalidRows=0;
		numOfValidRows=0;
		ArrayList<Object> callFailures = new ArrayList<Object>(length);
		for (int i = 1; i < length + 1; i++) {
			try {
				callFailures.add(getOneCallfailureRow(i));
				numOfValidRows++;
			} catch (ForeignTableException exception) {
				//System.out.print("i = " + i + " ");
				//System.out.println(exception.getMessage());
				numOfInvalidRows++;
			}
		}
		System.out.println("Invalid rows = " + numOfInvalidRows + " ");
		System.out.println("Valid rows = " + numOfValidRows + " ");
		return callFailures;
	}

	public void setLocalTableLists(AllMasterTableRows allMasterTableRows) {
		failureclasses = allMasterTableRows.getFailureclasses();
		causes = allMasterTableRows.getCauses();
		countryoperators = allMasterTableRows.getCountryoperators();
		equipmentList = allMasterTableRows.getEquipment();
	}

	public CallFailure getOneCallfailureRow(int rowNumber)
			throws ForeignTableException {
		return new CallFailure.Builder().dateTime(getDateTime(rowNumber))
				.cause(getCause(rowNumber))
				.failureclass(getFailureclass(rowNumber))
				.equipment(getEquipment(rowNumber))
				.countryoperator(getCountryoperator(rowNumber))
				.cellId(getCellId(rowNumber)).duration(getDuration(rowNumber))
				.nEVersion(getNEVersion(rowNumber)).iMSI(getIMSI(rowNumber))
				.hier3(getHier3(rowNumber)).hier32(getHier32(rowNumber))
				.hier321(getHier321(rowNumber)).build();
	}

	public java.util.Date getDateTime(int rowNumber)  {
		//Date will always be on sheet zero in column zero
		Cell cell = fileReader.getCell(0, rowNumber, 0);
		try{
			return  cell.getDateCellValue();		
		}catch(Exception e){
			return null;
		}		
	}

	public Cause getCause(int rowNumber) throws ForeignTableException {
		double eventId = getEventId(rowNumber);
		double causeCode = getCauseCode(rowNumber);
		for (Object object : causes) {
			Cause cause = (Cause) object;
			if (cause.getId().getEventId() == eventId
					&& cause.getId().getCauseCode() == causeCode)
				return cause;
		}
		// return new Cause();
		throw new ForeignTableException("Cause not in table");
	}

	public double getEventId(int rowNumber) {
		// EventId appears in two table, this is only for the one in base Data
		// It will always be on sheet zero in column one
		Cell cell = fileReader.getCell(0, rowNumber, 1);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return cell.getNumericCellValue();
	}

	public double getCauseCode(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 8);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return cell.getNumericCellValue();
	}

	public Failureclass getFailureclass(int rowNumber)
			throws ForeignTableException {
		int failureclassCell = getFailureclassCell(rowNumber);
		for (Object object : failureclasses) {
			Failureclass failureclass = (Failureclass) object;
			if (failureclass.isFailureclassEqual(failureclassCell))
				return failureclass;
		}
		// return new Failureclass();
		throw new ForeignTableException("Failure Class not in table");
	}

	public int getFailureclassCell(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 2);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public Equipment getEquipment(int rowNumber) throws ForeignTableException {
		int tAC = getTAC(rowNumber);
		for (Object object : equipmentList) {
			Equipment equipment = (Equipment) object;
			if (equipment.isTACEqual(tAC))
				return equipment;
		}
		throw new ForeignTableException("Equipment not in table");
	}

	public int getTAC(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 3);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public Countryoperator getCountryoperator(int rowNumber)
			throws ForeignTableException {
		int mCC = getMCC(rowNumber);
		int mNC = getMNC(rowNumber);
		for (Object object : countryoperators) {
			Countryoperator countryoperator = (Countryoperator) object;
			if (countryoperator.isMCCEqual(mCC)
					&& countryoperator.isMNCEqual(mNC))
				return countryoperator;
		}
		throw new ForeignTableException("Country or Operator not in table");
	}

	public int getMNC(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 5);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public int getMCC(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 4);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public int getCellId(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 6);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public int getDuration(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 7);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return (int) cell.getNumericCellValue();
	}

	public String getNEVersion(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 9);
		// TODO Validation
		return cell.getStringCellValue();
	}

	public String getIMSI(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 10);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}

	public String getHier3(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 11);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}

	public String getHier32(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 12);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return "NOT VALID!!";
		cell.setCellType(1);
		return cell.getStringCellValue();
	}

	public String getHier321(int rowNumber) {
		Cell cell = fileReader.getCell(0, rowNumber, 13);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return "NOT VALID!!";
		// Set the cell type to string to avoid number rounding errors
		cell.setCellType(1);
		return cell.getStringCellValue();
	}

	public static int getNumOfInvalidRows() {
		return numOfInvalidRows;
	}
	public static int getNumOfValidRows() {
		return numOfValidRows;
	}

}
