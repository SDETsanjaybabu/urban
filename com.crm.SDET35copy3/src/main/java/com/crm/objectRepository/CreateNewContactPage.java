package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtilities.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility{
//initialization
	public CreateNewContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
//declaretion
	@FindBy(name = "firstname") private WebElement firstnameTxtEdt;
	
	@FindBy(name = "lastname") private WebElement lastnameTxtEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']") private WebElement saveBtn;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']") private WebElement selectOrgLkpImg;
	
	@FindBy(id = "search_txt") private WebElement searchTxtEdt;
	
	@FindBy(name = "search") private WebElement searchBtn;
	
	@FindBy(xpath = "//a[.='TYSS54']") private WebElement selectOrgName;
	
//utilization	

	public WebElement getFirstnameTxtEdt() {
		return firstnameTxtEdt;
	}

	public WebElement getLastnameTxtEdt() {
		return lastnameTxtEdt;
	}
	
	public WebElement getSelectOrgLkpImg() {
		return selectOrgLkpImg;
	}

	public WebElement getSearchTxtEdt() {
		return searchTxtEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSelectOrgName() {
		return selectOrgName;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void enterContactInfo(String firstname,String lastname)
	{
		firstnameTxtEdt.sendKeys(firstname);
		lastnameTxtEdt.sendKeys(lastname);
		saveBtn.click();
	}
	
//utilization
	public void enterContactsWithOrg(String firstname,String lastname,WebDriver driver)
	{
		firstnameTxtEdt.sendKeys(firstname);
		lastnameTxtEdt.sendKeys(lastname);
		selectOrgLkpImg.click();
		switchToWindow(driver, "Accounts&action");
		searchTxtEdt.sendKeys("Tyss");
		searchBtn.click();
		selectOrgName.click();
		switchToWindow(driver,"Contacts&action");	
		saveBtn.click();
		
	}

}
