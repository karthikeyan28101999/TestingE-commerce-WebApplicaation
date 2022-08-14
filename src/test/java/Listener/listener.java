package Listener;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.Base;
import extentreport.ExtentReporter;

public class listener extends Base implements ITestListener{

	ExtentReporter extentreport=new ExtentReporter();
	//its avenstack class
	ExtentReports reports=extentreport.report();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentThread=new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
	    String testName=result.getName();
			test= reports.createTest(testName+" execution started");
			
			//MAKE THREAD SAFE
			
			extentThread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    String testName=result.getName();
		test= reports.createTest(testName+" execution started");
		
		//MAKE THREAD SAFE
		
		extentThread.set(test);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		String testName=result.getName();
		
		//test.fail(result.getThrowable());
		extentThread.get().fail(result.getThrowable());
		
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//screenShot
		   String path=takeScreenShot(driver, testName);
		   extentThread.get().addScreenCaptureFromPath(path,testName);  
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}
   


}
