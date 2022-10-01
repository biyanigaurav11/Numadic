package Numadic.NUProducts.Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Base64;
import java.time.Duration;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Numadic.NUProducts.Capabilities.CapabilityBaseClass;
import Numadic.NUProducts.Utilities.FileUtilities;

public class BasePage extends CapabilityBaseClass{

	
	public BasePage()
	{
		super();
	}
	public static Logger log = Logger.getLogger(BasePage.class.getName());
	public String screenShotPath = "";
	public WebDriverWait wait;
	
	public void onSuccess(String screenShotPath,WebDriver driver,String message)
	{
		log.info(message);
		extentTest.log(LogStatus.PASS, message);
	}
	
	public void onFailure(String screenShotPath,WebDriver driver,String message) throws IOException
	{
		
		File shot =  ( (TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(shot, new File(screenShotPath));
        InputStream is = new FileInputStream(screenShotPath);
        byte[] ssBytes= IOUtils.toByteArray(is);
        String base64 = Base64.getEncoder().encodeToString(ssBytes);
        log.error(message);
        extentTest.log(LogStatus.FAIL,message + extentTest.addBase64ScreenShot("data:image/png;base64," + base64));
        
		Assert.fail(message);
	}
	
	public void waitForElementVisibility(WebElement element,Duration second,String elementName,WebDriver driver,ExtentTest extentTest) throws IOException
	{
		screenShotPath = path.getAbsolutePath() + "\\waitForElementVisibility_" +FileUtilities.Timestamp()+ ".png";
		try {
		wait = new WebDriverWait(driver, second); 
		
		wait.until(ExpectedConditions.visibilityOf(element));
		onSuccess(screenShotPath,driver,elementName + "element wait is successful");
	}
		catch(Exception e)
		{
			onFailure(screenShotPath, driver, elementName + "element wait is not successful");
		}
	}
	
	public void waitForElementClickable(WebElement element,Duration second,String elementName,WebDriver driver,ExtentTest extentTest) throws IOException
	{
		screenShotPath = path.getAbsolutePath() + "\\waitForElementClickable_" +FileUtilities.Timestamp()+ ".png";
		try {
		wait = new WebDriverWait(driver,second); 
		
		wait.until(ExpectedConditions.elementToBeClickable(element));
		onSuccess(screenShotPath,driver,elementName + "element clickable is successful");
	}
		catch(Exception e)
		{
			onFailure(screenShotPath, driver, elementName + "element clickable is not successful");
		}
	}
	
	public void maxiMizeWindow(WebDriver driver , ExtentTest extentTest) throws IOException
	{
		
		screenShotPath = path.getAbsolutePath() + "\\maxiMizeWindow_" +FileUtilities.Timestamp()+ ".png";
		try {
			driver.manage().window().maximize();
			onSuccess(screenShotPath,driver,"Maximize browser window is successful");
		}
			catch(Exception e)
			{
				onFailure(screenShotPath,driver, "Maximize browser window is not successful");
			}
	}
	
	public void get(WebDriver driver ,String url, ExtentTest extentTest) throws IOException
	{
		
		screenShotPath = path.getAbsolutePath() + "\\get_" +FileUtilities.Timestamp()+ ".png";
		try {
			driver.get(url);;
			onSuccess(screenShotPath,driver,"Url loaded successfully");
		}
			catch(Exception e)
			{
				onFailure(screenShotPath,driver, "Url not loaded  successful");
			}
	}
	
	public void click(WebElement element,String elementName,WebDriver driver,ExtentTest extentTest) throws IOException
	{
		screenShotPath = path.getAbsolutePath() + "\\click_" +FileUtilities.Timestamp()+ ".png";
		try {
			waitForElementVisibility(element, Duration.ofSeconds(120), elementName, driver, extentTest);
			waitForElementClickable(element, Duration.ofSeconds(120), elementName, driver, extentTest);
		element.click();
		onSuccess(screenShotPath,driver,elementName + "element click is successful");
	}
		catch(Exception e)
		{
			onFailure(screenShotPath, driver, elementName + "element click is not successful");
		}
	}
	
	public void sendKeys(WebElement element,String elementName,WebDriver driver,ExtentTest extentTest,String value) throws IOException
	{
		screenShotPath = path.getAbsolutePath() + "\\sendKeys_" +FileUtilities.Timestamp()+ ".png";
		try {
			waitForElementVisibility(element, Duration.ofSeconds(120), elementName, driver, extentTest);
			waitForElementClickable(element, Duration.ofSeconds(120), elementName, driver, extentTest);
		element.sendKeys(value);
		onSuccess(screenShotPath,driver,elementName + "send value is successful");
	}
		catch(Exception e)
		{
			onFailure(screenShotPath, driver, elementName + "send value is  not successful");
		}
	}
	
	public String getText(WebElement element,String elementName,WebDriver driver,ExtentTest extentTest) throws IOException
	{
		 String textValue = "";
		screenShotPath = path.getAbsolutePath() + "\\getText_" +FileUtilities.Timestamp()+ ".png";
		try {
			waitForElementVisibility(element, Duration.ofSeconds(120), elementName, driver, extentTest);
			waitForElementClickable(element, Duration.ofSeconds(120), elementName, driver, extentTest);
		 textValue = element.getText();
		onSuccess(screenShotPath,driver,elementName + "element get Value is successful");
	}
		catch(Exception e)
		{
			onFailure(screenShotPath, driver, elementName + "element get Value is not successful");
		}
		return textValue;
	}
	public void selectByVisibleText(WebElement element,String elementName,WebDriver driver,ExtentTest extentTest,String value) throws IOException
	{
		screenShotPath = path.getAbsolutePath() + "\\selectByVisibleText_" +FileUtilities.Timestamp()+ ".png";
		try {
			waitForElementVisibility(element, Duration.ofSeconds(120), elementName, driver, extentTest);
			waitForElementClickable(element, Duration.ofSeconds(120), elementName, driver, extentTest);
		Select dropdownValue = new Select(element);
		dropdownValue.selectByVisibleText(value);
		onSuccess(screenShotPath,driver,elementName + "selectByVisibleText is successful");
	}
		catch(Exception e)
		{
			onFailure(screenShotPath, driver, elementName + "selectByVisibleText is not successful");
		}
	}
	
	public void mouseHover(WebElement element,String elementName,WebDriver driver,ExtentTest extentTest) throws IOException
	{
		screenShotPath = path.getAbsolutePath() + "\\mouseHover_" +FileUtilities.Timestamp()+ ".png";
		try {
			waitForElementVisibility(element, Duration.ofSeconds(120), elementName, driver, extentTest);
			waitForElementClickable(element, Duration.ofSeconds(120), elementName, driver, extentTest);
			Actions action = new Actions(driver);
			action.moveToElement(element).perform();
		onSuccess(screenShotPath,driver,elementName + "element mouseHover is successful");
	}
		catch(Exception e)
		{
			onFailure(screenShotPath, driver, elementName + "element mouseHover is not successful");
		}
	}
	
	
}
