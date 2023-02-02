package rahulshettyacademy.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {

		String reportPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath); // It is responsible for creating report

		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports(); // This will drive all reporting execution
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Priyam");
		extent.createTest(reportPath);
		return extent;
	}
}
