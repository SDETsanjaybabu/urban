package com.crm.createContacts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.genericUtilities.BaseClass;
import com.crm.objectRepository.ContactsInfoPage;
import com.crm.objectRepository.ContactsPage;
import com.crm.objectRepository.CreateNewContactPage;
import com.crm.objectRepository.HomePage;

@Listeners(com.crm.genericUtilities.ItestListenerImtn.class)
public class CreateContactsWithOrganisationTest extends BaseClass{
@Test(retryAnalyzer = com.crm.genericUtilities.RetryAnalyserImptn.class)
	public void createContactsWithOrganisationTest() throws Throwable{

			
			//To get random number
		    int randNum = jLib.getRandomNumber();
			
			//Fetching the firstName from excelSheet
		    String FirstName = eLib.readDataFromExcel("Sheet1", 7, 2)+randNum;
	
			//fetching the lastname from excelSheet
		    String LastName = eLib.readDataFromExcel("Sheet1", 8, 2)+randNum;
		    			
			//click on contacts link
			HomePage hpage=new HomePage(driver);
			hpage.getContactsLnk().click();
			
			//click on create contacts icon
			ContactsPage cpage=new ContactsPage(driver);
			cpage.clickOnCreateCtctLkpImg();
			
			//click on select icon in the organization name,search for orgname,select org name and click on submit button
			CreateNewContactPage cnpage=new CreateNewContactPage(driver);
			cnpage.enterContactsWithOrg(FirstName, LastName, driver);
			
			//verify whether the contact is created or not
			ContactsInfoPage cipage=new ContactsInfoPage(driver);
			String presentContactName =cipage.getContactsHeaderTxt().getText();
			Assert.assertTrue(presentContactName.contains(LastName));
			
			//Print output is report and console
			Reporter.log("createContactsWithOrganisationTest is pass", true);		
	}
}