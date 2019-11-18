package main.java.assist;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import main.java.base.BasePage;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class ListenerImplementation extends BasePage implements ITestListener {



    @Override
    public void onTestStart(ITestResult result) {

        testLogger.pass("Test case passed using Listener  "+result.getName());



    }

    @Override
    public void onTestSuccess(ITestResult result)  {
        try {
            System.out.println("on Test Success "+driver);
            String path="";
            System.out.println(util.captureScreenshot(testLogger,driver));
            /*
            path = util.captureScreenshot(testLogger,driver);
            System.out.println("on Test Success path "+path);
            testLogger.pass(result.getName() + "Passing the function");
            testLogger.info("value of <img src='" + path + "'/>");
            //testLogger.addScreenCaptureFromPath(path);
            //testLogger.addScreencast(path);
            //logger.(test);
            */

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result){
        try {

            String path = util.captureScreenshot(testLogger,driver);
            testLogger.fail("Failed Test case " + result.getTestName());
            testLogger.addScreenCaptureFromPath(path);
            //testLogger.addScreencast(path);
            //logger.endTest(test);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test case skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
