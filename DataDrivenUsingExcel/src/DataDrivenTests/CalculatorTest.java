package DataDrivenTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CalculatorTest {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vilas\\Documents\\Jars\\chromedriver1.exe");

		WebDriver driver;
		
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(
				"https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india/fixed-deposit-calculator-SBI-BSB001.html?classic=true");


		FileInputStream fis = new FileInputStream("C:\\Users\\vilas\\Documents\\CalData.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sh = wb.getSheetAt(0);

		for (int i = 1; i <= sh.getLastRowNum(); i++) {

			XSSFRow row = sh.getRow(i);
			driver.findElement(By.xpath("//input[@name='principal']"))
					.sendKeys(String.valueOf((int) row.getCell(0).getNumericCellValue()));
			driver.findElement(By.xpath("//input[@id='interest']"))
					.sendKeys(String.valueOf((int) row.getCell(1).getNumericCellValue()));
			driver.findElement(By.xpath("//input[@id='tenure']"))
					.sendKeys(String.valueOf((int) row.getCell(2).getNumericCellValue()));
			Select years = new Select(driver.findElement(By.xpath("//select[@id='tenurePeriod']")));
			years.selectByIndex(2);
			Select frequency = new Select(driver.findElement(By.xpath("//select[@id='frequency']")));
			frequency.selectByVisibleText(row.getCell(3).toString());
			driver.findElement(By.xpath("//a[@onclick='return getfdMatVal(this);']")).click();

			double expected = (double) row.getCell(4).getNumericCellValue();
			// int actual =
			// driver.findElement(By.xpath("//span[@id='resp_matval']")).getText();

			Double actual = Double.parseDouble(driver.findElement(By.xpath("//span[@id='resp_matval']")).getText());

			if (expected == actual) {
				System.out.println("Test Case " + i + " is passed");
			} else {
				System.out.println("Test Case " + i + " is failed");
			}
			driver.findElement(By.xpath("//a[@onclick='return getfdMatVal(this);']/following::a[1]")).click();
		}
		driver.close();
		wb.close();
		fis.close();

	}

}
