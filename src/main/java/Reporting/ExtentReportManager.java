package Reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;

public class ExtentReportManager {

    public static ExtentReports extentReports;
    public static ExtentReports createInstance(String fileName,String reportName,String documentTitle){
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);

        extentSparkReporter.config().setReportName(reportName);
        extentSparkReporter.config().setDocumentTitle(documentTitle);
        extentSparkReporter.config().setReportName(reportName);

        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        return extentReports;
    }

    public static String getReportNameWithTimeStamp(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh.mm.ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = "Report_"+dateTimeFormatter.format(localDateTime);

        return formattedTime;
    }

    public  static void logPassDetails(String log){
        Setup.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }

    public  static void logFailDetails(String log){
        Setup.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    public  static void logExceptionDetails(String log){
        Setup.extentTest.get().fail(log);
    }

    public  static void logInfoDetails(String log){
        Setup.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }

    public  static void logWarningDetails(String log){
        Setup.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }

    public static void logJson(String json){
        Setup.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }

    public static void logHeaders(List<Header> headersList){
        String[][] arrayHeaders =  headersList.stream().map(header -> new String[]{header.getName(), header.getValue()}).toArray(String[][] :: new);
      
        // //IT WILL PRINT TABLE WITHOUT VIEW MORE BUTTON
        //Setup.extentTest.get().info(MarkupHelper.createTable(arrayHeaders));
       
        //IT WILL ENABLES DROPDOWN BUTTON
        Markup tableHtml = MarkupHelper.createTable(arrayHeaders); 
        String arrayHeadersViewDetails = "<details><summary><span style='font-weight:bold';>View Headers</span></summary>" + tableHtml.getMarkup()+ "</details>";
        Setup.extentTest.get().info((arrayHeadersViewDetails));
        
       
    }

}
