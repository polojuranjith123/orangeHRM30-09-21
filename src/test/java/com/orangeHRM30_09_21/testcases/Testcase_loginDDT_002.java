package com.orangeHRM30_09_21.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangeHRM30_09_21.pages.Testcase_loginpage_001;
import com.orangeHRM30_09_21.utilities.Xlutils;

public class Testcase_loginDDT_002 extends BaseClass{
	
	@Test(dataProvider="data-Provider")
	public void loginDDT(String uname,String pwd) throws Exception {
		
		Testcase_loginpage_001 lp=new Testcase_loginpage_001(driver);
		
		lp.setUname(uname);
		logger.info("user name is entered");
		Thread.sleep(2000);
		
		lp.setPassword(pwd);
		logger.info("password name is entered");
		
		lp.clickBtn();
		logger.info("login button name is entered");
		
		Thread.sleep(2000);
		if(isAlertPresent()==true) {
			driver.switchTo().alert().dismiss();
			driver.switchTo().defaultContent();
		
		}
		Thread.sleep(1000);
		
		if(isAlertPresent()==true) {
			driver.switchTo().alert().dismiss();
			driver.switchTo().defaultContent();
		}
		String act=driver.findElement(By.xpath("//b[.='Admin']")).getText();
		if(act.contains("Admin")) {
			Assert.assertTrue(true);
		}else
		{
			logger.info("login failed");
			Assert.assertTrue(false);
		}
		
		
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		
		if(driver.getTitle().equals("OrangeHRM")) {
			Assert.assertTrue(true);
		}else {
			takeScreenshot(driver,"Testcase_loginDDT_002");
			logger.warn("login failed");
			Assert.assertTrue(false);
		}
	}
	@DataProvider(name="data-Provider")
	public String[][] getData() throws IOException {
		
		String xlfile=System.getProperty("user.dir")+"/config.properties/login.xlsx";
		String shname="sheet1";
		int rowcount=Xlutils.getrowCount(xlfile, shname);
		int cellcount=Xlutils.getcellCount(xlfile, shname, 1);
		String[][] data=new String[rowcount][cellcount];
		
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<cellcount;j++) {
				
				data[i-1][j]=Xlutils.getcellData(xlfile, shname, i, j);
			}
		}
		
		return data;
	
	}

}
