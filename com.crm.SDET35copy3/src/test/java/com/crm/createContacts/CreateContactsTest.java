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
public class CreateContactsTest extends BaseClass{
@Test(retryAnalyzer = com.crm.genericUtilities.RetryAnalyserImptn.class)
	public void createContactTest() throws Throwable{
		
		//To get random number
		int randNum = jLib.getRandomNumber();
		
		//Fetch first name from excelSheet
		String FirstName = eLib.readDataFromExcel("Sheet1", 7, 2)+randNum;

		//Fetch LastName from excelSheet
		String LastName = eLib.readDataFromExcel("Sheet1", 8, 2)+randNum;
		
		//click on contacts link
		HomePage hpage=new HomePage(driver);
		hpage.getContactsLnk().click();
		
		//click on create contacts icon
		ContactsPage cpage=new ContactsPage(driver);
		cpage.clickOnCreateCtctLkpImg();
		
		//enter the first name,Last name and click on save button
		CreateNewContactPage cnpage=new CreateNewContactPage(driver);
		cnpage.enterContactInfo(FirstName, LastName);
		Assert.fail();
		
		//verify whether the Contacts is created or not
		ContactsInfoPage cipage=new ContactsInfoPage(driver);
		String presentContactName = cipage.getContactsHeaderTxt().getText();
		Assert.assertTrue(presentContactName.contains(LastName));
		
		//Print output is report and console
		Reporter.log("createContactTest is pass", true);
	}
}
