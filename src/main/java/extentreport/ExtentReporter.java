package extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	ExtentReports extentreports;
	
	public ExtentReports report()
	{
		String destination=System.getProperty("user.dir")+"\\reports\\extentreport.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(destination);
		reporter.config().setDocumentTitle("Test Result");
		reporter.config().setReportName("ninjawebseite testing");
		
		extentreports=new ExtentReports();
		extentreports.attachReporter(reporter);
		extentreports.setSystemInfo("opratingSytem","windows10");
		extentreports.setSystemInfo("tesBY","karthi");
		return extentreports;
	}
}
