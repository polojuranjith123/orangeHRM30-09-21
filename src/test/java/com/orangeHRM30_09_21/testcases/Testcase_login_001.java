package com.orangeHRM30_09_21.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangeHRM30_09_21.pages.Testcase_loginpage_001;

public class Testcase_login_001 extends BaseClass {

	Testcase_loginpage_001 loginpage;
	@Test
	public void login() throws InterruptedException, IOException {
		loginpage=new Testcase_loginpage_001(driver);
		
		loginpage.setUname(config.name());
		logger.info("User name is entered");
		
		loginpage.setPassword(config.pwd());
		logger.info("password is entered");
	
		loginpage.clickBtn();
		Thread.sleep(1000);
		logger.info("login button is clicked");
		
		//webDriverWait(driver,20,driver.findElement(By.name("Submit")));
		
		if(isAlertPresent()==true) {
			driver.switchTo().alert().dismiss();
			driver.switchTo().defaultContent();
			logger.info("alert");
		}
		Thread.sleep(1000);
		if(isAlertPresent()==true) {
			driver.switchTo().alert().dismiss();
			driver.switchTo().defaultContent();
			logger.info("alert");
		}
		
		String act=driver.findElement(By.xpath("//b[text()='Admin']")).getText();
		String expt="Admin";
		
		if(act.contains("Admin")) {
			Assert.assertTrue(true);
			
		}else {
			takeScreenshot(driver,"Testcase_login_001");
			logger.warn("login failed");
			logger.info("Screenshot is taken");
			
			Assert.assertTrue(false);
			
		}
		
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		
		Thread.sleep(1000);
		
		if(driver.getTitle().contains("OrangeHRM")) {
			logger.info("success");
			Assert.assertTrue(true);
		}else {
			logger.warn("login failed");
			takeScreenshot(driver,"Testcase_login_001");
			logger.info("Screenshot is taken");
			Assert.assertTrue(false);
		}
		logger.info("login out successful...");
	}
	
}
