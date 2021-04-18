package gridDemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class LoginTestOnChrome {

	WebDriver driver;

	@Test(priority = 1)
	void setup() throws Exception {
		
		String nodeURL = "http://192.168.68.114:21997/wd/hub";
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setBrowserName("chrome");
		cap.setVersion("90.0.4430.72");
		cap.setPlatform(Platform.WINDOWS);
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vilas\\Documents\\Jars\\chromedriver1.exe");
		driver = new RemoteWebDriver(new URL(nodeURL), cap);
	}

	@Test(priority = 2)
	void login() throws InterruptedException {
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("vilasblokhande@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Sanvi@1411#");
		driver.findElement(By.name("login")).click();
		
		Thread.sleep(5000);

		String usernametext = driver.findElement(By.xpath(
				"/html/body/div[1]/div/div[1]/div/div[2]/div[4]/div[1]/div[4]/a/span/span"))
				.getText();

		if (usernametext.contains("Vilas")) {
			System.out.println("Login Test Passed");
		} else {
			System.out.println("Login Test Failed");
		}
	}
}
