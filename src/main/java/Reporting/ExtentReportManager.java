package Reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.http.Header;

public class ExtentReportManager {

	public static ExtentReports extentReports;

	public static ExtentReports createInstance(String fileName, String reportName, String documentTitle) {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(fileName);

		extentSparkReporter.config().setReportName(reportName);
		extentSparkReporter.config().setDocumentTitle(documentTitle);
		extentSparkReporter.config().setReportName(reportName);
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setEncoding("utf-8");

		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		extentReports.setSystemInfo("Applcation name", "Pet Store Users APIs");
		extentReports.setSystemInfo("User name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Operation System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("User", "Sanjiv");

		return extentReports;
	}

	public static String getReportNameWithTimeStamp() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh.mm.ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String formattedTime = "Report_" + dateTimeFormatter.format(localDateTime);
		return formattedTime;
	}

	public static void logPassDetails(String log) {
		Setup.extentTest.get().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
	}

	public static void logFailDetails(String log) {
		Setup.extentTest.get().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
	}

	public static void logExceptionDetails(String log) {
		Setup.extentTest.get().info(log);
	}

	public static void logInfoDetails(String log) {
		Setup.extentTest.get().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
	}

	public static void logSkippedDetails(String log) {
		Setup.extentTest.get().skip(MarkupHelper.createLabel(log, ExtentColor.GREY));
	}
	
	public static void logWarningDetails(String log) {
		Setup.extentTest.get().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
	}

	public static void logJson(String json) {
		Setup.extentTest.get().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
	}

	public static void logHeaders(List<Header> headersList) {
		String[][] arrayHeaders = headersList.stream()
				.map(header -> new String[] { header.getName(), header.getValue() }).toArray(String[][]::new);

		// IT WILL ENABLES DROPDOWN BUTTON
		Markup tableHtml = MarkupHelper.createTable(arrayHeaders);
		String arrayHeadersViewDetails = "<details><summary><span style='font-weight:bold';>View Headers</span></summary>"
				+ tableHtml.getMarkup() + "</details>";
		Setup.extentTest.get().info((arrayHeadersViewDetails));

	}
	
	public static void logTestDetails(Map<String, String> testDetails) {
	    String[][] arrayHeaders = testDetails.entrySet()
	            .stream()
	            .map(entry -> new String[]{entry.getKey(), entry.getValue()})
	            .toArray(String[][]::new);

	    Markup tableHtml = MarkupHelper.createTable(arrayHeaders);
	    String arrayHeadersViewDetails = "<details><summary><span style='font-weight:bold';>View Test Details </span></summary>"
	            + tableHtml.getMarkup() + "</details>";
	    Setup.extentTest.get().info(arrayHeadersViewDetails);
	}
	
	public static ExtentTest getExtentTest() {
	    return Setup.extentTest.get();
	}



}
