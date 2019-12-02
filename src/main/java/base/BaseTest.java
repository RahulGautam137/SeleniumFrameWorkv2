package main.java.base;

import com.aventstack.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import main.java.assist.RetryClass;
import main.java.assist.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadExecutionException;

import java.util.Random;

public class BaseTest extends BasePage{


    @Test(retryAnalyzer = RetryClass.class)
    public void LoginAsBankManger(){

    try {

            ExtentTest testLogger= extentRep.createTest("login as bank manager");
            browserHelper.navigateToURL(testLogger,config.getProperty("testUrl"));
            util.isElementPresent(testLogger,driver,By.xpath(objectRepos.getProperty("bankManagerLoginBtn")));
            testLogger.pass("Start");
            util.waitTillElementIsPresentOnPage(testLogger,driver,By.xpath(objectRepos.getProperty("customerLoginBtn")));
            WebElement customerLogin=driver.findElement(By.xpath(objectRepos.getProperty("customerLoginBtn")));
            util.waitTillElementIsClickable(testLogger,driver,customerLogin);
            util.clickOnWebElement(testLogger,customerLogin);

            Thread.sleep(4000);
            testLogger.pass("Passing this test case");
            Random r=new Random();
            int t=r.nextInt(2);
            System.out.println("Value of t"+t) ;
            Assert.assertEquals(1,t);

            //util.captureScreenshot(testLogger,driver);



    }catch (Exception e){
        e.printStackTrace();
    }

    }

}
