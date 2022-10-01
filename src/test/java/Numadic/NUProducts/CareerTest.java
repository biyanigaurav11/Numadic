package Numadic.NUProducts;

import java.io.IOException;

import org.testng.annotations.Test;

import Numadic.NUProducts.Capabilities.CapabilityBaseClass;

public class CareerTest extends CapabilityBaseClass{
	
	@Test
	public void TC_01_QA_Engineer() throws IOException
	{
		career.openURL();
		
		career.verifyCareerPageHeader("JOIN OUR CREW");
		
		career.selectJobType("Internship");
		career.verifyCareerInternshipMessage("There are no available job positions that match your filters.");
         
		career.selectJobType("Full time");
		
		career.clickQAEngineer();
		career.verifyQAEngineerUrl("https://numadic.com/careers/qa-engineer.php");
		
		career.verifyandClickApplyHereNow("Apply here now");
		career.verifyQAEngineerUrl("https://numadic.com/careers/#careersFormContainer");
		
		career.verifymouseHoverandClickApplyButton();
		String [ ] expectedValidation = { "Enter your first name","Enter your last name","Enter an email","Enter your mobile number","Enter your date of birth"};
		career.validateandClickonNextButton(expectedValidation);
	}

}
