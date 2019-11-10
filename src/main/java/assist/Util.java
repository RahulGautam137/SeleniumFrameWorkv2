package main.java.assist;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import java.io.*;
import java.time.LocalDateTime;


public class  Util {

    public boolean isElementPresent(WebDriver driver, Logger log,By by ){
        try{

                driver.findElement(by);
                log.debug("Element is Present");
                return true;



        }catch(NoSuchElementException e){
            log.debug("Element is not Present");
            return false;

        }catch (Exception t){
            log.debug("Exception found"+t);
            return false;
        }

    }

    public String captureScreenshot( WebDriver driver){
//ExtentTest logger,
            //logger.setDescription("function used for capturing screesnhot");
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
