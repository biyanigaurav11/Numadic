package Numadic.NUProducts.POM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Numadic.NUProducts.Capabilities.CapabilityBaseClass;

public class CareerPageObjects extends CapabilityBaseClass
{
     @FindBy(xpath="//div[@class='page-header']//h1")
    public WebElement careerPageHeaderValue;

     @FindBy(id="job_type")
     public WebElement jobType;
     

     @FindBy(xpath="//table[@id='job-posts-table']//tr//td")
    public WebElement internshipMessage;
     
     @FindBy(xpath="//a[text()='QA Engineer']")
     public WebElement QA_Engineer;
     
     @FindBy(xpath="//a[contains(@href,'careers#')]")
     public WebElement applyHereNow;
     
     @FindBy(xpath="//a[text()='QA Engineer']/../..//button[text()='Apply']")
     public WebElement applyButton;
     
     @FindBy(xpath="//input[@id='careers_form_btn_step1']")
     public WebElement NextButton;
     
     
	public CareerPageObjects()
	{
		PageFactory.initElements(driver, this);
	}
	
	
}
