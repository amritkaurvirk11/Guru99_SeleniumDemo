package Test;
//testng, test data from excel, data provider, screenshot
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Util;
import utils.excelUtil;

public class Day5Test {
	static WebDriver driver;
	@DataProvider(name = "GuruTest")
	public Object[][] testData() throws Exception {
		return excelUtil.getDataFromExcel(Util.ExcelLocation,Util.SheetName);}
	@Test(dataProvider = "GuruTest")
	public void testCase04(String username, String password) throws Exception {
			driver.findElement(By.xpath(Util.usernamePath)).clear();
			driver.findElement(By.xpath(Util.usernamePath)).sendKeys(username);
			driver.findElement(By.xpath(Util.passwordPath)).clear();
			driver.findElement(By.xpath(Util.passwordPath)).sendKeys(password);
			driver.findElement(By.xpath(Util.loginButtonPath)).click();
			try{ 
				Alert alt = driver.switchTo().alert();
				String actualBoxtitle = alt.getText(); // get content of the Alter Message
				alt.accept();
				if (actualBoxtitle.contains(Util.expectedError)) { // Compare Error Text with Expected Error Value
					System.out.println("got expected error and test casePassed"); 
				} else {
					System.out.println("did not get expected error and test caseFailed");
				}
			}    
			catch (NoAlertPresentException Ex){ 			
//				if (driver.getTitle().toLowerCase().contains(Util.ExpecedHomePageTitle)) {
//					System.out.println("title match and test case Passed");
//				} else {
//					System.out.println("title not match title and test case Failed");}
//					takeScreenShot();
//				}
				String pageText =driver.findElement(By.tagName(Util.pageTextTagName)).getText();
				String[] parts = pageText.split(Util.pattern);
				String dynamicText = parts[1];
				assertTrue(dynamicText.substring(1, 5).equals(Util.firstPattern));
				String remainText = dynamicText.substring(dynamicText.length()-4);
				assertTrue(remainText.matches(Util.secondPattern));
				takeScreenShot();
			}
			}

			public void takeScreenShot() throws IOException{
				// Code to take Screenshot
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				// Code to save screenshot at desired location
				FileUtils.copyFile(scrFile, new File("C:\\Users\\amrit\\Desktop\\screenshot.png"));
			}
	@SuppressWarnings("unused")
	@BeforeMethod
	public void setUp(){
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
	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
}
