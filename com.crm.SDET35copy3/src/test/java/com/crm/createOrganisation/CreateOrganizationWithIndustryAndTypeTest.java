package com.crm.createOrganisation;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.genericUtilities.BaseClass;
import com.crm.objectRepository.CreateNewOrganisationPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.OrganisationInfoPage;
import com.crm.objectRepository.OrganisationPage;

@Listeners(com.crm.genericUtilities.ItestListenerImtn.class)
public class CreateOrganizationWithIndustryAndTypeTest extends BaseClass{
	@Test(retryAnalyzer = com.crm.genericUtilities.RetryAnalyserImptn.class)
	public void createOrganizationWithIndustryAndTypeTest() throws Throwable 
	{
		//To get random number
		int randNum = jLib.getRandomNumber();
		
		//Fetching data from excelSheet
		String orgName = eLib.readDataFromExcel("Orgnasation", 1, 2)+randNum;
		String industryName = eLib.readDataFromExcel("Orgnasation", 4, 3);
		String typeName = eLib.readDataFromExcel("Orgnasation", 4, 4);
		
		//click on organization link
		HomePage hpage =new HomePage(driver);
		hpage.getOrganisationsLnk().click();
		
		//click on create organization icon
		OrganisationPage opage= new OrganisationPage(driver);
		opage.clickOnCreateOrgLkp();
		 
		//enter the organization name with industry and type
		CreateNewOrganisationPage cpage=new CreateNewOrganisationPage(driver);
		cpage.enterOrgInfo(orgName);
		cpage.selectIndustry(industryName);
		cpage.selectType(typeName);
		
		//verify whether the organization is created or not
		OrganisationInfoPage oginfo=new OrganisationInfoPage(driver);
		String presentOrgName=oginfo.getOgnHeaderTxt().getText();
		SoftAssert sart=new SoftAssert();
		sart.assertTrue(presentOrgName.contains(orgName));
		
		//Print output is report and console
		Reporter.log("CreateOrganizationWithIndustryAndTypeTest is pass",true);
		sart.assertAll();
	}
}
