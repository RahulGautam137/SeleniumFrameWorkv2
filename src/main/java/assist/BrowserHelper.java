package main.java.assist;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class BrowserHelper {

    public WebDriver driver=null;


    public WebDriver startBrowser(ExtentTest testlogger,String browserName){

        switch(browserName){

            case "chrome"   :   System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//resources//chromedriver.exe");
                                ChromeOptions options=new ChromeOptions();
                                options.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
                                options.addExtensions(new File(System.getProperty("user.dir")+"//src//main//resources//symatec.crx"));
                                driver=new ChromeDriver(options);


                                break;

            case "firefox"  :   System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//main//resources//gecko.exe");
                                driver=new FirefoxDriver();

                                break;

        }
        testlogger.info(browserName +" Browser initiated");
        System.out.println(browserName+" Browser initiated");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;

    }

    public void navigateToURL(ExtentTest testlogger,String url){

            driver.get(url);
            testlogger.info("Navigating to Following URL "+url);
    }


}
