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

        test.pass("Test case passed using Listener  "+result.getName());



    }

    @Override
    public void onTestSuccess(ITestResult result)  {
        try {
            String path = util.captureScreenshot(driver);
            test.pass(result.getName() + "Passing the function");
            test.info("value of <img src='" + path + "'/>");
            test.addScreenCaptureFromPath(path);
            //test.addScreencast(path);
            //logger.(test);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result){
        try {
            String path = util.captureScreenshot(driver);
            test.fail("Failed Test case " + result.getTestName());
            test.addScreenCaptureFromPath(path);
            //test.addScreencast(path);
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
