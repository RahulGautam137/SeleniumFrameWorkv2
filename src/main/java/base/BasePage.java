package main.java.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import main.java.assist.BrowserHelper;
import main.java.assist.ExcelReader;
import main.java.assist.FrameHelper;
import main.java.assist.Util;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
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

    public static ExcelReader reader;
    public static ArrayList<Object> testData;
    public static ExtentReports extentRep;
    public static ExtentTest testLogger;
    public BrowserHelper browserHelper;
    public Util util;
    FrameHelper frameHelper;

    public BasePage(){



    }

        public void reportConfig(){
            try{

                LocalDateTime ldt= LocalDateTime.now();
                String getDatetime=ldt.toString();
                getDatetime=getDatetime.replace('-','_').replace(':','_');
                String dynamicPath="report"+getDatetime;

                ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\src\\main\\java\\report\\aventreports"+dynamicPath+".html");
                htmlReporter.config().setDocumentTitle("EXtent");

                extentRep = new ExtentReports();
                extentRep.attachReporter(htmlReporter);


            }catch (Exception excp){
                excp.printStackTrace();

            }

        }

        @BeforeSuite
        public void setUp(){

            try {
                reportConfig();
                testLogger = extentRep.createTest("InitialiseVariables", "Initialising All Class variables");
                frameHelper=new FrameHelper();
                browserHelper=new BrowserHelper();
                util = new Util();
                reader=new ExcelReader();
                config = new Properties();
                objectRepos = new Properties();

                FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//propertiesfiles//Config.properties");

                FileInputStream fis2 = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//propertiesfiles//ObjectRepos.properties");
                config.load(fis);
                objectRepos.load(fis2);
                testData = reader.getFileDataAsList("TestData.xlsx");

                driver=browserHelper.startBrowser(testLogger,config.getProperty("browser"));
                System.out.println("Check this ");

                System.out.println(System.getProperty("user.dir"));

            }catch (IOException ioexception){
                ioexception.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }




        }

        @AfterSuite
        public void tearDown(){
            if(driver!=null) {
                System.out.println("Quiting the Browser");
                extentRep.flush();
                driver.quit();
            }
        }

}
