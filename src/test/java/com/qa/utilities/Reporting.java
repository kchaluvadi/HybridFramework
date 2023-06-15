package com.qa.utilities;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.TestListenerAdapter;

public class Reporting extends TestListenerAdapter {

    //builds a new report using the html template
    ExtentHtmlReporter htmlReporter;

    ExtentReports extent;
    //helps to generate the logs in test report.
    ExtentTest test;
    public void startReport(String OS, String browser) {
        // initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        //To add system or environment info by using the setSystemInfo method.
        extent.setSystemInfo("OS", OS);
        extent.setSystemInfo("Browser", browser);

        //configuration items to change the look and feel
        //add content, manage tests etc
        //htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
    }

}
