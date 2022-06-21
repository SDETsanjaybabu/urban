package com.crm.createOrganisation;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.genericUtilities.BaseClass;
import com.crm.objectRepository.CreateNewOrganisationPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.OrganisationInfoPage;
import com.crm.objectRepository.OrganisationPage;


@Listeners(com.crm.genericUtilities.ItestListenerImtn.class)
public class ToCreateOrganisationTest extends BaseClass{
@Test(retryAnalyzer = com.crm.genericUtilities.RetryAnalyserImptn.class)
public void createOrganisationTest() throws Throwable{
	
	//To get random number
	int randNum = jLib.getRandomNumber();
	
	//Fetching data from excelSheet
	String orgName = eLib.readDataFromExcel("Sheet1", 4, 2)+randNum;
	
	//click on organization link
	HomePage homePage = new HomePage(driver);
	homePage.getOrganisationsLnk().click();
	//Assert.fail();
	
	//click on create organization icon
	OrganisationPage orgPage=new OrganisationPage(driver);
	orgPage.clickOnCreateOrgLkp();
	
	//enter the organization name and click on save button
	CreateNewOrganisationPage cnewOrgPage=new CreateNewOrganisationPage(driver);
	cnewOrgPage.enterOrgName(orgName);
	
	//verify whether the organization is created or not
	OrganisationInfoPage orgInfoPage = new OrganisationInfoPage(driver);
	String presentOrgName = orgInfoPage.getOgnHeaderTxt().getText();
	Assert.assertTrue(presentOrgName.contains(orgName));
	
	//Print output is report and console
	Reporter.log("createOrganisationTest is pass",true);
}
}