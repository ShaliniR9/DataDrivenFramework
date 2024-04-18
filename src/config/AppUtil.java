package config;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	public static Properties con;
	public static WebDriver driver;
	@BeforeTest
	public static void SetUp() throws Throwable 
	{
		con = new Properties();
		con.load(new FileInputStream("C:\\Users\\smile\\Selenim_Workspace\\DDT_FrameWork\\PropertyFile\\Environment.properties"));
		if(con.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(con.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			Reporter.log("Browser value is Not Matching");
		}
	}
	@AfterTest
	public static void tearDown()
	{
		driver.close();
	}
}