package main.java.assist;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;

public class BrowserHelper {
    public WebDriver driver=null;

    public WebDriver startBrowser(String browserName){
        ExtentTest
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
        System.out.println(browserName+" initiated");

        return driver;

    }


}
