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
           ExtentTest test2= logger.createTest("login as bank manager");
            Assert.assertTrue(util.isElementPresent(driver, log, By.xpath(objectRepos.getProperty("customerLoginBtn"))), "Customer login was Present");
            test2.pass("Hello bettjnjnn");
            driver.findElement(By.xpath(objectRepos.getProperty("customerLoginBtn"))).click();
            Thread.sleep(4000);
            test2.pass("ygygyh");
            test2.addScreenCaptureFromPath(util.captureScreenshot(driver));



    }catch (Exception e){
        e.printStackTrace();
    }

    }

}
