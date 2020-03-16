package Test;
//testng, dataprovider, screenshot
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


public class Day4Test {
	static WebDriver driver;
	@DataProvider(name = "GuruTest")
	public Object[][] testData() throws Exception {
		Object[][]data = new Object[4][2];
		data[0][0]= Util.usernameInput;
		data[0][1]= Util.passwordInput;
		data[1][0] = "invalid";
		data[1][1] = "valid";
		data[2][0] = "valid";
		data[2][1] = "invalid";
		data[3][0] = "invalid";
		data[3][1] = "invalid";
		return data;}


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
				System.out.println("Passed"); 
			} else {
				System.out.println("Failed");
			}
		}    
		catch (NoAlertPresentException Ex){ 
			String actualTitle = driver.getTitle().toLowerCase();
			// On Successful login compare Actual Page Title with Expected Title
			if (actualTitle.contains(Util.ExpecedHomePageTitle)) {
				System.out.println("Passed");
			} else {
				System.out.println("title not match title Failed");
			}
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
