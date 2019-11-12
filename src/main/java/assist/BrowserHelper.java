package main.java.assist;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserHelper {
    public WebDriver driver=null;

    public WebDriver startBrowser(String browserName){
        ExtentTest
        switch(browserName){

            case "chrome"   :   System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//resources//chromedriver.exe");
                                driver=new ChromeDriver();

                                break;

            case "firefox"  :   System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//main//resources//gecko.exe");
                                driver=new FirefoxDriver();
                                break;

        }
        System.out.println(browserName+" initiated");

        return driver;

    }


}
