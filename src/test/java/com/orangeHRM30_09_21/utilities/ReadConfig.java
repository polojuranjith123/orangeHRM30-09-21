package com.orangeHRM30_09_21.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	public ReadConfig() throws IOException {
		pro=new Properties();
		FileInputStream fis = new FileInputStream("./config.properties/congigfile");
		pro.load(fis);
		
	}
	public void setempname() {
		pro.getProperty("empname");
	}
	public String pwd() {
		return pro.getProperty("pwd");
	}
	public String name() {
		return pro.getProperty("uname");
		
	}
}
