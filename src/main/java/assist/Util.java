package main.java.assist;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import java.io.*;
import java.time.LocalDateTime;


public class  Util {

    public boolean isElementPresent(ExtentTest testLogger, WebDriver driver, By by ) {

            try {

                driver.findElement(by);
                testLogger.info("Element is Present");
                return true;
            } catch (ElementNotVisibleException notVisible) {
                testLogger.info("Element is not Present");
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                testLogger.info("Element " + by + "is not visible to user");
                return false;
            }

    }


    public String captureScreenshot( ExtentTest testLogger,WebDriver driver){

            String pathname=null;
            try{
                //screenshot name
                LocalDateTime ldt= LocalDateTime.now();
                String getDatetime=ldt.toString();
                getDatetime=getDatetime.replace('-','_').replace(':','_');
                String Screenshotname="Screenshot"+getDatetime;


                //taking screenshot
                File screenShotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                pathname="C:\\Users\\Rahul\\IdeaProjects\\SeleniumFrameWork\\src\\main\\java\\Screenshots\\"+Screenshotname+".png";
                FileUtils.copyFile(screenShotFile, new File("C:\\Users\\Rahul\\IdeaProjects\\SeleniumFrameWork\\src\\main\\java\\Screenshots\\"+Screenshotname+".png"));
                testLogger.log(Status.PASS,"Screenshot Taken successfully !");
                testLogger.addScreencastFromPath(pathname);

                /* FileInputStream fis=new FileInputStream(screenShotFile);
                BufferedReader br=new BufferedReader(new InputStreamReader(fis));
                //Output file
                FileWriter fw=new FileWriter( new File("C:\\Users\\Rahul\\IdeaProjects\\SeleniumFrameWork\\src\\main\\java\\Screenshots\\"+Screenshotname+".png"));
                BufferedWriter bw=new BufferedWriter(fw);


                String str=null;
                while((str=br.readLine())!=null){
                    bw.write(str);
                }
                bw.close();
                br.close();
                */

            }catch(Exception e){
                e.printStackTrace();

            }
            return pathname;
    }

}
