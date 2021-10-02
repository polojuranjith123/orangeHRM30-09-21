package com.orangeHRM30_09_21.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.orangeHRM30_09_21.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public ReadConfig config;
	public Logger logger;
	public static WebDriver driver;
	
	
	@BeforeSuite
	public void startsuite() throws IOException {
		config=new ReadConfig();
		logger=Logger.getLogger("BaseClass");
		PropertyConfigurator.configure("Log4j.properties");	
	}
	
	@BeforeClass
	@Parameters("brName")
	public void startTest(String brName) {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		if(brName.equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(brName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(brName.equals("ie")) {
			WebDriverManager.edgedriver().setup();
			driver=new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
	//	driver.manage().timeouts().implicitlyWait(40, TimeUnit.MILLISECONDS);
		//driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.MILLISECONDS);	
	}
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	
	
	//Screenshots
	public void takeScreenshot(WebDriver driver,String testName) throws IOException {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File target= new File(System.getProperty("user.dir")+"/Screenshots/"+testName+timeStamp+".png");
		FileUtils.copyFile(src, target);
	}
	
	
	//Alert 
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}catch(NoAlertPresentException e) {
			return false;
		}
		
	}
	
	public void webDriverWait(WebDriver driver ,long timeUnit,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver,timeUnit);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

}
