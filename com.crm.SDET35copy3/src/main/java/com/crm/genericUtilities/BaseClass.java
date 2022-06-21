package com.crm.genericUtilities;
/**
 * 
 * @author SanjayBabu
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	public static WebDriver sdriver;
	public WebDriver driver;
	public DataBaseUtility dLib=new DataBaseUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public FileUtility fLib=new FileUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public JavaUtility jLib=new JavaUtility();
	
	/**
	 * connecting to database
	 */
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void dbConfig()
	{
		dLib.connectToDB();
	}
	/**
	 * launching the browser
	 * @throws Throwable
	 */
	//@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void launchTheBrowser() throws Throwable
	{
		//String BROWSER = System.getProperty("browser");
		//String URL = System.getProperty("url");
		String BROWSER = fLib.getPropertKeyValue("browser");
		String URL = fLib.getPropertKeyValue("url");
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			ChromeOptions chromeOptions=new ChromeOptions();
			chromeOptions.setBinary("C:\\Users\\SanjayBabu\\AppData\\Local\\Google\\Chrome\\Application");
		
			driver=new ChromeDriver(chromeOptions);
		}else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else {
			driver=new ChromeDriver();
		}
		System.out.println("Browser successfully launched");
		//implicitly wait
		sdriver=driver;
		wLib.waitForPageToLoad(driver);
		//enter the URL of the Application
		sdriver.get(URL);
		//maximize the screen
		driver.manage().window().maximize();
	}
	/**
	 * login to application
	 */
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void loginToAppln() throws Throwable
	{
		String USERNAME = fLib.getPropertKeyValue("username");
		String PASSWORD = fLib.getPropertKeyValue("password");
		
		LoginPage lpage=new LoginPage(driver);
		lpage.loginToAppli(USERNAME, PASSWORD);
		System.out.println("Login successful");
	}
	/**
	 * logout from application
	 */
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void logoutFromAppln()
	{
		HomePage hpage=new HomePage(driver);
		hpage.logout(driver);
		System.out.println("Logout successful");
	}
	/**
	 * close the browser
	 */
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void closeTheBrowser()
	{
		driver.quit();
		System.out.println("Browser successfully closed");
	}
	/**
	 * close database configuration
	 */
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void closeDBconfig()
	{
		dLib.closeDB();
	}
}

