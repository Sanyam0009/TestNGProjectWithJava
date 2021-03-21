package com.sanyam.frameworkpackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
	public static WebDriver driver;
	private static Properties property;
	private static FileInputStream fis;

	public static WebDriver getDriver() {

		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/TestData/EnvironmentVariables.property");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties property = new Properties();
		try {
			property.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String browserName1 = property.getProperty("Browser");
		String URL = property.getProperty("URL");
		int ImplicitilyWait = Integer.parseInt(property.getProperty("ImplicitilyWait"));
		int PageLoadTimeOut = Integer.parseInt(property.getProperty("PageLoadTimeout"));
		if (browserName1.equalsIgnoreCase("chrome")) {
			System.out.println("Opening Chrome Browser");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(ImplicitilyWait, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(PageLoadTimeOut, TimeUnit.SECONDS);
			driver.get(URL);
		} else if (browserName1.toLowerCase().equals("firefox")) {
			System.out.println("Opening firefox Browser");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(ImplicitilyWait, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(PageLoadTimeOut, TimeUnit.SECONDS);
			driver.get(URL);
		}
		return driver;
	}
}
