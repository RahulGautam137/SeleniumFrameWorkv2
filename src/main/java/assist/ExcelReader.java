package main.java.assist;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    XSSFWorkbook testData;
    XSSFSheet sheet1;


    public ArrayList<Object> getFileData(String fileName){

        try{

            ArrayList<Object> data=new ArrayList<Object>();

            FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+fileName));
            if(fileName.endsWith(".xlsx")) {

                 testData = new XSSFWorkbook(fis);
                 sheet1 = testData.getSheetAt(0);

                int row = 0;
                int col = 0;

                Iterator<Row> itr = sheet1.rowIterator();

                while (itr.hasNext()) {
                    Row r = itr.next();

                    Iterator<Cell> cellItr = r.cellIterator();
                    while (cellItr.hasNext()) {
                        Cell cell = cellItr.next();

                        switch (cell.getCellType()) {
                            case STRING:
                                // System.out.print(cell.getStringCellValue());
                                data.add(cell.getStringCellValue());
                                break;
                            case BOOLEAN:
                                //System.out.print(cell.getBooleanCellValue());
                                data.add(cell.getBooleanCellValue());
                                break;
                            case NUMERIC:
                                // System.out.print(cell.getNumericCellValue());
                                data.add(cell.getNumericCellValue());
                                break;

                        }


                    }


                }
                for (Object o : data) {
                    System.out.println(o);
                }
            }
            return data;


        }catch(FileNotFoundException f){
            System.out.println("Path is incorrect");
            //return data;

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception found"+e);
           //return data;
        }

        return null;

    }
    public int getRowCount(){
        try{
            if(sheet1==null){
                return -1;
            }else{
                System.out.println(sheet1.getLastRowNum()+1);
                return sheet1.getLastRowNum()+1;
            }

        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }


    }

    public HashMap getExcelDataAsMap(String fileName){

        try{
/*
            HashMap<String,HashMap<String,String>> inputData=new HashMap<String,HashMap<String, String>>();

            FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+fileName));
            if(fileName.endsWith(".xlsx")) {
                XSSFWorkbook testData = new XSSFWorkbook(fis);
                XSSFSheet sheet1 = testData.getSheetAt(0);
                int row = 0;
                int col = 0;

                Iterator<Row> itr = sheet1.rowIterator();

                while (itr.hasNext()) {
                    Row r = itr.next();

                    Iterator<Cell> cellItr = r.cellIterator();
                    while (cellItr.hasNext()) {
                        Cell cell = cellItr.next();

                        switch (cell.getCellType()) {
                            case STRING:
                                // System.out.print(cell.getStringCellValue());
                                data.add(cell.getStringCellValue());
                                break;
                            case BOOLEAN:
                                //System.out.print(cell.getBooleanCellValue());
                                data.add(cell.getBooleanCellValue());
                                break;
                            case NUMERIC:
                                // System.out.print(cell.getNumericCellValue());
                                data.add(cell.getNumericCellValue());
                                break;

                        }


                    }


                }
                for (Object o : data) {
                    System.out.println(o);
                }
            }

            return inputData;

        }catch(FileNotFoundException f){
            System.out.println("Path is incorrect");
            //return data;
*/
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception found"+e);
            //return data;
        }

        return null;


    }

    public static void main(String[] args){
        ExcelReader excelReader=new ExcelReader();
        excelReader.getFileData("TestData.xlsx");
        excelReader.getRowCount();
    }

}

