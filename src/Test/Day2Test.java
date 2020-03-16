package Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Util;

public class Day2Test {
	static WebDriver driver;
	public static void main(String[] args) {
		setUp();
		driver.findElement(By.xpath(Util.usernamePath)).clear();
		driver.findElement(By.xpath(Util.usernamePath)).sendKeys(Util.usernameInput);
		driver.findElement(By.xpath(Util.passwordPath)).clear();
		driver.findElement(By.xpath(Util.passwordPath)).sendKeys(Util.passwordInput);
		driver.findElement(By.xpath(Util.loginButtonPath)).click();
		
		if((driver.getTitle().toLowerCase().contains(Util.ExpecedHomePageTitle)))
			System.out.println("passed");
		else
			System.out.println("failed");	
		
		driver.close();
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
}}
