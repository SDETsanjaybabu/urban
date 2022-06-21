package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {
//initialization
	public ContactsInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
//declaretion
	@FindBy(xpath = "//span[@class='dvHeaderText']") private WebElement contactsHeaderTxt;

//utilization
	public WebElement getContactsHeaderTxt() {
		return contactsHeaderTxt;
	}
	
	
	
}
