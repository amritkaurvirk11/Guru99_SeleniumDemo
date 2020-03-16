package utils;

public class Util{
	public static final String driverType ="chrome";
	public static final String FirefoxDriveLocation= System.getProperty("user.dir")+"\\driver\\geckodriver.exe";
	public static final String ChromeDriveLocation= System.getProperty("user.dir")+"\\driver\\chromedriver.exe";
	public static final int driverWaitTime = 20;	
	public static final String baseUrl= "http://www.demo.guru99.com/V4/";
	public static final	String usernamePath = "//input[@name='uid']";
	public static final String passwordPath = "//input[@name = 'password']";
	public static final String loginButtonPath = "//input[@name='btnLogin']";
	public static final String usernameInput ="mngr247775";
	public static final String passwordInput = "pYhYjYg";
	public static final String ExpecedHomePageTitle = "homepage";
	public static final String expectedError = "User or Password is not valid";
	public static final String ExcelLocation = System.getProperty("user.dir")+"\\excels\\TestData.xlsx";
	public static final String SheetName = "Login";
	public static final String pageTextTagName = "tbody";
	public static final String pattern = ":";
	public static final String firstPattern = "mngr";
	public static final String secondPattern= "[0-9]+";

}
