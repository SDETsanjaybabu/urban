package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
//intilaization
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
//declaretion
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']") private WebElement createContactLkpImg;

//utilization
	public WebElement getCreateContactLkpImg() {
		return createContactLkpImg;
	}

 
	public void clickOnCreateCtctLkpImg() {
		createContactLkpImg.click();
	}
	
}
