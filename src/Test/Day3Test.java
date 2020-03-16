package Test;
//test data from excel
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.Util;
import utils.excelUtil;

public class Day3Test {
	static WebDriver driver;
	public static void main(String[] args) throws IOException {

		String[][] testData = excelUtil.getDataFromExcel(Util.ExcelLocation,Util.SheetName);
		for (int i = 0; i < testData.length; i++) {
			setUp();
			driver.findElement(By.xpath(Util.usernamePath)).clear();
			driver.findElement(By.xpath(Util.usernamePath)).sendKeys(testData[i][0]);
			driver.findElement(By.xpath(Util.passwordPath)).clear();
			driver.findElement(By.xpath(Util.passwordPath)).sendKeys(testData[i][1]);
			driver.findElement(By.xpath(Util.loginButtonPath)).click();
			try{ 
				Alert alt = driver.switchTo().alert();
				String actualBoxtitle = alt.getText(); // get content of the Alter Message
				alt.accept();
				if (actualBoxtitle.contains(Util.expectedError)) { // Compare Error Text with Expected Error Value
					System.out.println("Test case[" + i + "]: Passed"); 
				} else {
					System.out.println("Test case[" + i + "]: Failed");
				}
			}    
			catch (NoAlertPresentException Ex){ 
				String actualTitle = driver.getTitle().toLowerCase();
				// On Successful login compare Actual Page Title with Expected Title
				if (actualTitle.contains(Util.ExpecedHomePageTitle)) {
					System.out.println("Test case[" + i + "]: Passed");
				} else {
					System.out.println("Test case[" + i + "]: title not match title Failed");
				}}
			driver.close();
	}
		}
	@SuppressWarnings("unused")
	public static void setUp(){
		if(Util.driverType =="firefox")
		{
			System.setProperty("webdriver.gecko.driver",Util.FirefoxDriveLocation);
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Util.driverWaitTime, TimeUnit.SECONDS);
			driver.get(Util.baseUrl);
		}
		else 
		{
			System.setProperty("webdriver.chrome.driver",Util.ChromeDriveLocation);
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Util.driverWaitTime, TimeUnit.SECONDS);
			driver.get(Util.baseUrl);
		}
	}
}
