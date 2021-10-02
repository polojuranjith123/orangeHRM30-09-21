package com.orangeHRM30_09_21.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Testcase_loginpage_001 {
	
	WebDriver ldriver;
	public Testcase_loginpage_001(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
	}
	@FindBy(name="txtUsername") WebElement username;
	
	@FindBy(name="txtPassword") WebElement password;
	
	@FindBy(xpath="//input[@name=\"Submit\"]") WebElement logbtn;
	
	public void setUname(String uname) {
		username.clear();
		username.sendKeys(uname);
	}
	public void setPassword(String pwd) {
		password.clear();
		password.sendKeys(pwd);
	}
	public void clickBtn() {
		logbtn.isEnabled();
		logbtn.click();
	}

}
