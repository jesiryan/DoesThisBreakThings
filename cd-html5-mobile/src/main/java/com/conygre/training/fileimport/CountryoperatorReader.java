package com.conygre.training.fileimport;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.conygre.training.entities.Countryoperator;

public class CountryoperatorReader {

	private FileReader fileReader;

	public CountryoperatorReader() {
		fileReader = new FileReader();
	}

	// possibly this should be a set. not sure.
	public List<Countryoperator> getAllCountryoperatorRows() {
		int length = fileReader.getSheetColumnLength(4);
		ArrayList<Countryoperator> countryoperators = new ArrayList<Countryoperator>(length);
		for (int i = 1; i < length + 1; i++) {
			countryoperators.add(getOneCountryoperatorRow(i));
		}
		return countryoperators;
	}

	public Countryoperator getOneCountryoperatorRow(int rowNumber) {
		return new Countryoperator.Builder().mCC(getMCC(rowNumber))
				.mNC(getMNC(rowNumber))
				.country(getCountry(rowNumber))
				.operator(getOperator(rowNumber)).build();
	}

	public double getMCC(int rowNumber) {
		Cell cell = fileReader.getCell(4, rowNumber, 0);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return cell.getNumericCellValue();
	}

	public double getMNC(int rowNumber) {
		Cell cell = fileReader.getCell(4, rowNumber, 1);
		if (cell == null || cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
			return -1;
		return cell.getNumericCellValue();
	}

	public String getCountry(int rowNumber) {
		Cell cell = fileReader.getCell(4, rowNumber, 2);
		return cell.getStringCellValue();
	}

	public String getOperator(int rowNumber) {
		Cell cell = fileReader.getCell(4, rowNumber, 3);
		return cell.getStringCellValue();
	}

}
