package com.sanyam.uipackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage{
	private WebDriver driver;
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="user-display")
	WebElement userLogged;
	
	@FindBy (xpath = "//div[@id='main-nav']//a[@href='/contacts']")
	WebElement contacts;
	
	public void userLoggedIn(){
		Assert.assertEquals(userLogged.getText(), "Sanyam Choudhary","User was not able to login successfully");
	}
	
	public ContactPage clickOnContactsButton(){
		Actions action = new Actions(driver);
		action.moveToElement(contacts).click().moveToElement(userLogged).build().perform();
		//contacts.click();
		return new ContactPage(driver);
	}

}
