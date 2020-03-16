package Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Day1Test {
	static WebDriver driver;
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\driver\\geckodriver.exe");
		int driverWaitTime = 20;
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(driverWaitTime, TimeUnit.SECONDS);
		String baseUrl= "http://www.demo.guru99.com/V4/";
		String usernamePath = "//input[@name='uid']";
		String passwordPath = "//input[@name = 'password']";
		String loginButtonPath = "//input[@name='btnLogin']";
		String usernameInput ="mngr247775";
		String passwordInput = "pYhYjYg";
		String HomePageTitle = "homepage";
		driver.get(baseUrl);
		driver.findElement(By.xpath(usernamePath)).clear();
		driver.findElement(By.xpath(usernamePath)).sendKeys(usernameInput);
		driver.findElement(By.xpath(passwordPath)).clear();
		driver.findElement(By.xpath(passwordPath)).sendKeys(passwordInput);
		driver.findElement(By.xpath(loginButtonPath)).click();
		if((driver.getTitle().toLowerCase().contains(HomePageTitle.toLowerCase())))
			System.out.println("passed");
		else
			System.out.println("failed");		
	}
}
