package Saurabh.Resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Test Results");	
        reporter.config().setReportName("Saurabh");
        
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        
        extent.setSystemInfo("Tester", "Saurabh");
        extent.setSystemInfo("App", "V0.1");
        return extent;
	}

}
