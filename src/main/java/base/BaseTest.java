package main.java.base;

import com.aventstack.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import main.java.assist.Util;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadExecutionException;

public class BaseTest extends BasePage{


    @Test
    public void LoginAsBankManger(){

    try {
            ExtentTest testLogger= extentRep.createTest("login as bank manager");
            Assert.assertTrue(util.isElementPresent(driver, log, By.xpath(objectRepos.getProperty("customerLoginBtn"))), "Customer login was Present");
            testLogger.pass("Hello bettjnjnn");
            driver.findElement(By.xpath(objectRepos.getProperty("customerLoginBtn"))).click();
            Thread.sleep(4000);
            testLogger.pass("ygygyh");
            testLogger.addScreenCaptureFromPath(util.captureScreenshot(driver));



    }catch (Exception e){
        e.printStackTrace();
    }

    }

}
