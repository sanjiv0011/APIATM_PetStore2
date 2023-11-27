package Reporting;

import java.util.Arrays;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Setup implements ITestListener {

	public static ExtentReports extentReports = null;

	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {
		String fileName = ExtentReportManager.getReportNameWithTimeStamp();
		String reportPath = System.getProperty("user.dir") + "\\Report\\" + fileName;
		extentReports = ExtentReportManager.createInstance(reportPath, "Report", "Api automation");
	}

	@Override
	public void onFinish(ITestContext context) {
		if (extentReports != null) {
			extentReports.flush();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest1 = extentReports.createTest("Test Name: " + result.getMethod().getMethodName());
		extentTest.set(extentTest1);

	}

	public void writeTestDetailsOnReport(Map<String, String> dataThatYouWantToAddOnReport) {
		ExtentReportManager.logTestDetails(dataThatYouWantToAddOnReport);
		String testCaseId = dataThatYouWantToAddOnReport.get("TestCaseID");
		updateTestCaseNameOnTheReport(testCaseId);
	}

	public void updateTestCaseNameOnTheReport(String testCaseId) {
		ExtentTest extentTest = ExtentReportManager.getExtentTest();
		if (extentTest != null) {
			extentTest.getModel().setName("TestCaseId: " + testCaseId);
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReportManager.logPassDetails("Passsed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReportManager
				.logFailDetails("<div style='color: #000000;'>" + result.getThrowable().getMessage() + "</div>");
		String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
		String stackTraceFormatted = stackTrace.replaceAll(",", "<br>");

		String stackTraceViewMore = "<details>\n"
				+ "<summary><span style='font-weight:bold';>View Exception Details</span></summary>\n" + ""
				+ stackTraceFormatted + "\n" + "</details>";
		ExtentReportManager.logExceptionDetails(stackTraceViewMore);
		ExtentReportManager.logFailDetails("Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
		String stackTraceFormatted = stackTrace.replaceAll(",", "<br>");

		String stackTraceViewMore = "<details>\n"
				+ "<summary><span style='font-weight:bold';>View Exception Details</span></summary>\n" + ""
				+ stackTraceFormatted + "\n" + "</details>";
		ExtentReportManager.logExceptionDetails(stackTraceViewMore);
		ExtentReportManager.logSkippedDetails("Skipped");
	}

}
