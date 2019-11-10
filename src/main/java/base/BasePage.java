package main.java.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import main.java.assist.ExcelReader;
import main.java.assist.Util;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BasePage {

    public static WebDriver driver;
    public static Properties config;
    public static Properties objectRepos;
    public static Logger log=Logger.getLogger("devpinoyLogger");
    public static Util util;
    public static ExcelReader reader=new ExcelReader();
    public static ArrayList<Object> testData;
    public static ExtentReports logger;
    public static ExtentTest test;

        @BeforeSuite
        public void setUp(){

            try {

                if(driver==null) {
                   //
                    util=new Util();
                    ExtentHtmlReporter avent = new ExtentHtmlReporter("C:\\Users\\Rahul\\IdeaProjects\\SeleniumFrameWork\\src\\main\\java\\report\\aventreports.html");
                    logger=new ExtentReports();
                    logger.attachReporter(avent);
                    test=logger.createTest("first Test","First Test Description");

                    config = new Properties();
                    objectRepos = new Properties();
                    System.out.println(System.getProperty("user.dir"));
                    FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//propertiesfiles//Config.properties");
                    test.pass("Hello Passing this test");
                    log.debug("Hello Logging");
                    log.debug("Hello Logging2");
                    log.debug("Hello Logging3");
                    FileInputStream fis2 = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//propertiesfiles//ObjectRepos.properties");
                    config.load(fis);
                    objectRepos.load(fis2);
                    testData=reader.getFileData("TestData.xlsx");


                    if(config.getProperty("browser").equalsIgnoreCase("chrome")){
                        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//resources//chromedriver.exe");
                        test.pass("Passing the function");
                        System.out.println("Inside chrome driver if");
                        driver=new ChromeDriver();

                    }
                    if(config.getProperty("browser")=="firefox"){
                        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//src//main//resources//gecko.exe");
                        driver=new FirefoxDriver();
                    }


                    driver.get(config.getProperty("testUrl"));
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
                    driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.getProperty("pageLoadTime")), TimeUnit.SECONDS);

                }

            }catch(IOException e){
                System.out.println(e);
            }

        }

        @AfterSuite
        public void tearDown(){
            if(driver!=null) {

                logger.flush();
                driver.quit();
            }
        }

}
