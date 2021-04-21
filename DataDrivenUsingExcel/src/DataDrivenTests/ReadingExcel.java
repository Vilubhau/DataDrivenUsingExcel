package DataDrivenTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcel {

	public static void main(String[] args) throws Exception {

		// Remember :- Excel-->Work book-->Sheet-->Row-->Cell

		FileInputStream fis = new FileInputStream("C:\\Users\\vilas\\Documents\\Data.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int totalRows = sheet.getLastRowNum();

		System.out.println("Total rows are: " + totalRows);

		int totalColumns = sheet.getRow(0).getLastCellNum();

		System.out.println("Total Columns are: " + totalColumns);

		System.out.println("Excel data as below: ");

		for (int i = 0; i < totalRows; i++) {
			XSSFRow row = sheet.getRow(i);

			for (int j = 0; j < totalColumns; j++) {
				String value = row.getCell(j).toString();
				System.out.print(value + " || ");
			}
			System.out.println();
		}
		workbook.close();
		fis.close();
	}

}
