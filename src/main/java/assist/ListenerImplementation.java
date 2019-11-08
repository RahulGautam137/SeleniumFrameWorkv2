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

        test.log(LogStatus.PASS,"Test case passed using Listener  "+result.getName());



    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String path=util.captureScreenshot(driver);
        test.log(LogStatus.PASS,result.getName()+"Passing the function");
        test.log(LogStatus.INFO,"value of <img src='"+path+"'/>");
        test.addScreenCapture(path);
        test.addScreencast(path);
        logger.endTest(test);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String path=util.captureScreenshot(driver);
        test.log(LogStatus.FAIL,"Failed Test case "+result.getTestName());
        test.addScreenCapture(path);
        test.addScreencast(path);
        logger.endTest(test);


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
