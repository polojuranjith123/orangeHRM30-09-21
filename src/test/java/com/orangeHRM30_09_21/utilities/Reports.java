package com.orangeHRM30_09_21.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reports implements ITestListener {

	public ExtentHtmlReporter reporter;
	public ExtentReports extent;
	public ExtentTest logger;
	String timeStamp;
	
	@Override
	public void onStart(ITestContext context) {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "TestReport-"+timeStamp+".html";
		reporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		extent=new ExtentReports();
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Ranjith");
		
		reporter.config().setDocumentTitle("Facebook Test Project");
		reporter.config().setReportName("Functional Test Report");
		reporter.config().setTestViewChartLocation(ChartLocation.TOP);
		reporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger =extent.createTest(result.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger =extent.createTest(result.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		String path=System.getProperty("user.dir")+"/Screenshots/"+result+timeStamp+".png";
		File f=new File(path);
		if(f.exists()) {
			try {
				logger.addScreenCaptureFromPath(path);
			} catch (Exception e) {
				System.out.println("File Not Found  " +e);	
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger =extent.createTest(result.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
		
		}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		}
	

	
	
}
