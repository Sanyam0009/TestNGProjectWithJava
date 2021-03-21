package com.sanyam.testproject;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sanyam.frameworkpackage.BrowserFactory;
import com.sanyam.frameworkpackage.ExtentManager;
import com.sanyam.frameworkpackage.ReadExcelFile;
import com.sanyam.frameworkpackage.TestListners;
import com.sanyam.uipackage.LoginPage;
import com.sanyam.uipackage.RegisterUser;

public class LoginPageTest extends BrowserFactory {
	// method>> Class >> test >> Suite
	public HashMap<String, String> datamap;
	@BeforeClass
	public void setUp() throws IOException {
		// ReadExcelFile excelFactory = new ReadExcelFile(); // made the method
		// "testDataCollector" static so no need to create an obj as can be
		// accessed by class name
		getDriver();
		datamap =  ReadExcelFile.testDataCollector("TC_01");
	}

	@Test
	public void LoginFreeCRM() {
		String email_Id = datamap.get("Email_Id");
		String uname = datamap.get("UserName");
		String Pwd = datamap.get("Password");

		System.out.println("Excel data" + uname + " " + Pwd + " " + email_Id);
		System.out.println("Register and Login");
		//RegisterUser RegisterUserObj = PageFactory.initElements(driver, RegisterUser.class//(POM class name));
		//or
		// RegisterUser RegisterUserObj = new RegisterUser(driver); // In this
		// case we will have to keep PageFactory.initElement(driver, this); in constructor
		LoginPage LoginPageObject = new LoginPage();
		LoginPageObject.login(uname, Pwd);
	}

	@Test
	public void testSuccess() {

		System.out.println("Inside Pass Report");
	}
	 
	@Test
	public void testCase2() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("Inside TC 2");
		TestListners.customOnTestFailure(driver,"System should display Sanyam","System is Displaying Sakshi","Test to take custome SC");
		String path2 = ExtentManager.getScreenshot(driver, "Fail2");
		softAssert.fail("Test Fail 2"  + "-" + path2);
		
		softAssert.fail("Test Fail 3" );
		softAssert.assertAll();
		
	}

	/*	@Test
	public void testCase3() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("Inside TC 3");
		softAssert.fail("Test Fail 1");
		softAssert.fail("Test Fail 2" );
		softAssert.fail("Test Fail 3" );
		softAssert.assertAll();
		System.out.println("After Fail");
	}*/

	@Test
	public void testSkip() {
		System.out.println("Inside Skip Report");
		throw new SkipException("Testig Skip test");
	}

	@AfterClass
	public void teardown() {
		System.out.println("Closing Browser");
		driver.quit();
	}

}