package Reporting;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Setup implements ITestListener {

   public static ExtentReports extentReports = null;
   
   public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
   
    @Override
	public void onStart(ITestContext context){

        String fileName = ExtentReportManager.getReportNameWithTimeStamp();
        String reportPath = System.getProperty("user.dir")+"\\Report\\"+fileName;
        extentReports = ExtentReportManager.createInstance(reportPath,"Report","Api automation");

    }

    @Override
	public  void onFinish(ITestContext context){
        if(extentReports != null){
            extentReports.flush();
        }
    }

    @Override
	public void onTestStart(ITestResult result){
        ExtentTest test = extentReports.createTest("Test Name: "+result.getMethod().getMethodName());
        extentTest.set(test);

    }

    public void onTestPassed(ITestResult result){
        ExtentTest test = extentReports.createTest("Test Name: "+result.getMethod().getMethodName());
        extentTest.set(test);

    }

    @Override
	public void onTestFailure(ITestResult result){
        ExtentReportManager.logFailDetails("<div style='color: #000000;'>"+result.getThrowable().getMessage()+"</div>");
        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        String stackTraceFormatted = stackTrace.replaceAll(",","<br>");
        
        String stackTraceViewMore = "<details>\n" +
                "<summary>View More Exception Details</summary>\n" +
                ""+stackTraceFormatted+"\n" +
                "</details>";
        ExtentReportManager.logExceptionDetails(stackTraceViewMore);

    }
    
    @Override
	public void onTestSkipped(ITestResult result){
        ExtentTest test = extentReports.createTest("Test Name: "+result.getMethod().getMethodName());
        extentTest.set(test);

    }

}
