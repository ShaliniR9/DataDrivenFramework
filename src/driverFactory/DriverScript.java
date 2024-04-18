package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFuncton.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
String inputpath ="C:\\Users\\smile\\Selenim_Workspace\\DDT_FrameWork\\FileInput\\LoginData.xlsx"; 
String outputpath ="C:\\Users\\smile\\Selenim_Workspace\\DDT_FrameWork\\FileOutput\\DataDrivenResults.xlsx";
@Test
public void startTest() throws Throwable
{
	//create object for excel file util class
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in login sheet
	int rc = xl.rowCount("Login");
	Reporter.log("No of rows"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		//reading username and password
		String username = xl.getCellData("Login", i, 0);
		String password = xl.getCellData("Login",i, 1);
		//calling login method
		boolean res = FunctionLibrary.verify_Login(username, password);
		if(res)
		{
			//if it is true login success into result cell
			xl.setCellData("Login", i, 2, "Login Success", outputpath);
			xl.setCellData("Login", i, 3, "Pass", outputpath);
		}
		else
		{
			
			File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"Loginpage.png"));
			//if it is false login success into result cell
			xl.setCellData("Login", i, 2, "Login Fail", outputpath);
			xl.setCellData("Login", i, 3, "Fail", outputpath);
		}
	}
}
}
