package main.java.assist;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryClass implements IRetryAnalyzer {
    int count=0;
    public static int  max=3;
    @Override
    public boolean retry(ITestResult result) {
        if(!result.isSuccess()){

            if(count<max){
                count++;
                result.setStatus(ITestResult.FAILURE);
                return true;

            }else{
                result.setStatus(ITestResult.FAILURE);


            }

        }else{
            result.setStatus(ITestResult.SUCCESS);

        }
        return false;

    }
}
