package DataDrivenTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WrittingExcel {
	

	void write() throws Exception {
		FileOutputStream fos = new FileOutputStream("C:\\Users\\vilas\\Documents\\Result.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook();

		XSSFSheet sh = wb.createSheet("Data");

		for (int i = 0; i <= 5; i++) {

			XSSFRow row = sh.createRow(i);

			for (int j = 0; j <= 5; j++) {

				XSSFCell cell = row.createCell(j);

				cell.setCellValue("Vilas");

			}
		}
		System.out.println("Data entered in excel");
		wb.write(fos);
		wb.close();
		fos.close();
	}

	void read() throws Exception {
		FileInputStream fis = new FileInputStream("C:\\Users\\vilas\\Documents\\Result.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Data");
		
		int totalRows = sh.getLastRowNum();
		int totalColumns = sh.getRow(0).getLastCellNum();
		
		for(int i=0;i<=totalRows;i++)
		{
			XSSFRow row = sh.getRow(i);
			
			for(int j=0;j<totalColumns;j++)
			{
				System.out.print(row.getCell(j).toString()+" ");
			}
			System.out.println( );
		}
		wb.close();
		fis.close();
	}

	public static void main(String[] args) throws Exception {
		
		WrittingExcel we = new WrittingExcel();
		we.write();
		we.read();

	}

}
