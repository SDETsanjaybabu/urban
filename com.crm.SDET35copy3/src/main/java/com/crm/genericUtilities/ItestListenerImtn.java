package com.crm.genericUtilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.Args;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ItestListenerImtn implements ITestListener{
	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		
	}

	public void onTestSuccess(ITestResult result) {
	test.log(Status.PASS,result.getMethod().getMethodName()+"is passed");
	}
	
	/**
	 * To take screenshot of failed test scripts
	 */
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL,result.getMethod().getMethodName()+"is failed");
		test.log(Status.FAIL,result.getThrowable());
		
		//String screenShotName=null;
		try {
		   String screenShotName=WebDriverUtility.takeScreenShot(BaseClass.sdriver,result.getMethod().getMethodName());
		   test.addScreenCaptureFromPath(screenShotName);
		}
        catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP,result.getMethod().getMethodName()+"is skipped");
		test.log(Status.SKIP,result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReports/report.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("my Document");
		spark.config().setReportName("sanjay Report");
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("created By", "sanjay");
		report.setSystemInfo("reviewed By", "babu");
		report.setSystemInfo("platform", "windows 11");	
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
	}
}