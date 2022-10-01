package Numadic.NUProducts.Capabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import Numadic.NUProducts.Pages.Career;
import Numadic.NUProducts.Utilities.FileUtilities;

public class CapabilityBaseClass {
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public static File path;
	public static String subFolderPath = "";
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(CapabilityBaseClass.class.getName());
	public Career career;
	
	public static void generateReport()
	{
		DOMConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\java\\Numadic\\NUProducts\\Resources\\log4j.xml" );
		subFolderPath =  System.getProperty("user.dir") + "/Reports/";
		
		path = FileUtilities.CreateFolder(subFolderPath);
		String userName = System.getProperty("user.name");
        String timeStamp =  FileUtilities.Timestamp();

        path = FileUtilities.CreateFolder(subFolderPath);
        path =  FileUtilities.CreateFolder(path.getAbsolutePath() + "/" + userName);
        path = FileUtilities.CreateFolder(path.getAbsolutePath()+ "/" + timeStamp);
		
        extentReport = new ExtentReports(path.getAbsolutePath() + "\\ExtentReport.html");
        path = FileUtilities.CreateFolder(path.getAbsolutePath()+"\\ScreenShots");
		extentReport.addSystemInfo("Environment", "SIT");
        extentReport.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\java\\Numadic\\NUProducts\\Resources\\Extent\\extent-config.xml"));
        log.info("Extent Report has Started");
        
	}
	
	public static WebDriver launchBrowser(String browserName)
	{
		if(browserName.equalsIgnoreCase("Chrome"))
		{
		String ChromePath = "J:\\NewWorkspace\\NUProducts\\src\\main\\java\\Executable2\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver", ChromePath);
		 driver = new ChromeDriver();
		 log.info("Chrome browser has launched");
		}
		driver.manage().deleteAllCookies();
		return driver;
		
	}
	
	@BeforeSuite
	public void beforeSuite()
	{
		generateReport();
	}
	
	
	@BeforeMethod
	public void beforeMethod(ITestResult result)
	{
		String className = result.getMethod().getMethodName();
        extentTest = extentReport.startTest(className);

		launchBrowser("Chrome");
		career = new Career();
	}	
	
	@AfterMethod
    public static void getResult(ITestResult result) throws Exception {
        extentReport.endTest(extentTest);
        if (result.getStatus() == ITestResult.FAILURE) {

        	String screenshotPath = path.getAbsolutePath() + "\\FailedTestCase_" + FileUtilities.Timestamp() + ".png";

            File shot =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(shot, new File(screenshotPath));
            InputStream is = new FileInputStream(screenshotPath);
            byte[] ssBytes= IOUtils.toByteArray(is);
            String base64 = Base64.getEncoder().encodeToString(ssBytes);

            extentTest.log(LogStatus.FAIL, extentTest.addBase64ScreenShot("data:image/png;base64," + base64));
            extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());


        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
        }
       // driver.quit();
	}
	
        @AfterSuite
        public void afterSuite(){
          
            extentReport.flush();
        
}
}
