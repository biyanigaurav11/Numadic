package Numadic.NUProducts.Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import Numadic.NUProducts.Capabilities.CapabilityBaseClass;
import Numadic.NUProducts.POM.CareerPageObjects;

public class Career extends CapabilityBaseClass {

	
	BasePage bp;
	CareerPageObjects careerPageObjects ;
	public Career()
	{
		bp = new BasePage();
		careerPageObjects = new CareerPageObjects();
	}
	
	public void openURL() throws IOException
	{
		bp.get(driver, "https://numadic.com/careers/", extentTest);
		bp.maxiMizeWindow(driver, extentTest);
	}
	
	public void verifyCareerPageHeader(String expectedCareerPageHeader) throws IOException
	{
	String actualCareerPageHeader=bp.getText(careerPageObjects.careerPageHeaderValue,"Career Header", driver, extentTest);
	Assert.assertEquals(actualCareerPageHeader, expectedCareerPageHeader);
	}
	
	public void selectJobType(String jobTypeValue) throws IOException
	{
		bp.selectByVisibleText(careerPageObjects.jobType, "jobType", driver, extentTest, jobTypeValue);
	}
	
	public void verifyCareerInternshipMessage(String expectedCareerInternshipMessage) throws IOException
	{
	String actualCareerInternshipMessage=bp.getText(careerPageObjects.internshipMessage,"Career Internship Message", driver, extentTest);
	Assert.assertEquals(actualCareerInternshipMessage, expectedCareerInternshipMessage);
	}
	
	public void clickQAEngineer() throws IOException
	{
	bp.click(careerPageObjects.QA_Engineer, "QA Engineer", driver, extentTest);
	}
	
	public void verifyQAEngineerUrl(String expectedQAEngineerUrl) throws IOException
	{
	
		String actualQAEngineerUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualQAEngineerUrl, expectedQAEngineerUrl);
	}
	
	public void verifyandClickApplyHereNow(String expectedButtonValue) throws IOException
	{
	
		String actualButtonValue = bp.getText(careerPageObjects.applyHereNow, "Apply Here Now ", driver, extentTest);
		Assert.assertEquals(actualButtonValue, expectedButtonValue);
		bp.click(careerPageObjects.applyHereNow, "Click on Apply Here Now", driver, extentTest);
	}
	
	public void verifymouseHoverandClickApplyButton() throws IOException
	{
		bp.mouseHover(careerPageObjects.QA_Engineer,"Mouse Hover on QA engineer", driver, extentTest);
		
		bp.click(careerPageObjects.applyButton, "Click on Apply Button", driver, extentTest);
	}
	

	public void validateandClickonNextButton(String[] expectedValidationsMandatoryFields) throws IOException
	{
		
		bp.click(careerPageObjects.NextButton, "Click on Next Button", driver, extentTest);
		for(int i=0;i<5;i++)
		{
			String actualValidationsMandatoryFields = driver.findElement(By.xpath("(//div[@id='step1']//div//li[@class='parsley-required'])["+(i+1)+"]")).getText();
			Assert.assertEquals(actualValidationsMandatoryFields, expectedValidationsMandatoryFields[i]);
		}
	}
}
