package main.java.assist;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class ExcelReader {

    XSSFWorkbook testDataWorkBook;
    XSSFSheet testSheet;
    HashMap<String,HashMap<String,String>> inputData;
    HashMap<String,String> dataMap;



    public ArrayList<Object> getFileDataAsList(String fileName){

        try{

            ArrayList<Object> data=new ArrayList<Object>();

            FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+fileName));
            if(fileName.endsWith(".xlsx")) {

                testDataWorkBook = new XSSFWorkbook(fis);
                 testSheet = testDataWorkBook.getSheetAt(0);

                int row = 0;
                int col = 0;

                Iterator<Row> itr = testSheet.rowIterator();

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
            if(testSheet ==null){
                return -1;
            }else{
                System.out.println(testSheet.getLastRowNum()+1);
                return testSheet.getLastRowNum()+1;
            }

        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }


    }
    public void initializeFile(String fileName){
        try{
            FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+fileName));
            testDataWorkBook=new XSSFWorkbook(fis);
        }catch(IOException ioexp){
            ioexp.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void initializeSheet(String sheetName){
        try {
                testSheet = testDataWorkBook.getSheet(sheetName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int getColCount(int row){
         XSSFRow currentRow =testSheet.getRow(row-1);
        return currentRow.getLastCellNum();


    }

    public String getCellData(int row, int col){
        try{
            if((row<=0)||(col<=0)){
                return "";
            }
            String dataValue=null;
            XSSFRow rowcounter= testSheet.getRow(row-1);
            if(rowcounter!=null){
                XSSFCell cellCounter=null;

                cellCounter=rowcounter.getCell(col-1);
                if(cellCounter!=null) {

                    switch (cellCounter.getCellType()) {
                        case STRING:
                            dataValue = cellCounter.getStringCellValue();
                            break;

                        case NUMERIC:
                            Date d = cellCounter.getDateCellValue();
                            dataValue = d.toString();
                            break;

                        case BOOLEAN:
                            boolean b = cellCounter.getBooleanCellValue();
                            dataValue = Boolean.toString(b);
                            break;

                        case BLANK:
                            dataValue = "";
                            break;

                        case FORMULA:
                            XSSFFormulaEvaluator xf = new XSSFFormulaEvaluator(testDataWorkBook);
                            dataValue = xf.evaluate(cellCounter).toString();
                            break;


                        }
                    }else{
                        dataValue="";

                    }

            }else{
                dataValue="";
            }

            return dataValue;

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }


    }

    //public int getIndex

    public HashMap getExcelDataAsMap(String fileName,String sheetName,String startDataCell,String endingDataCell){

        try{

                    initializeSheet(fileName);
                    initializeSheet(sheetName);
                    inputData=new HashMap<String,HashMap<String, String>>();
                    int row=0;
                    int col=0;
                    int testDataStartRow=0;
                    int testDataEndRow=0;
                    row=testSheet.getLastRowNum()+1;
                    System.out.println("Last Row fffNumber is "+row);
                    for(int i=0;i<row;i++){
                        //Row r=testSheet.getRow(i);

                        if(startDataCell.equalsIgnoreCase(getCellData(i+1,1))){
                            testDataStartRow=i;
                        }


                        /*for(int j=0;j<col;j++){
                                cellItr=r.getCell(j);
                            if(startDataCell.equalsIgnoreCase(getCellData(i,j))){
                                testDataStartRow=i;
                            }
                        }

                         */
                    }
                for(int i=0;i<row;i++) {
                    //Row r = testSheet.getRow(i);
                    String Compare=getCellData(i+1, 1);
                    //System.out.println("Value of Compare "+Compare);
                    if (endingDataCell.equalsIgnoreCase(Compare)) {
                        testDataEndRow = i;

                    }
                }

                System.out.println("Start row is "+ testDataStartRow+" ENding row as "+testDataEndRow);


                String rowHeader=null;
                for(int rowStart=testDataStartRow+1;rowStart<testDataEndRow;rowStart++){
                    dataMap=new HashMap<String,String>();

                    Row tempRow=testSheet.getRow(rowStart);
                    int cellEnd=tempRow.getLastCellNum()+1;
                    Cell tempCell;
                    String tempData=null;
                    String tempkey=null;

                    for(int cellStart=2;cellStart<cellEnd;cellStart++){
                        tempkey= getCellData(testDataStartRow+1,cellStart);
                        tempData=getCellData(rowStart+1,cellStart);


                        //System.out.println("Value of Tempdata "+tempData);
                        if(!tempData.equalsIgnoreCase("")){
                            System.out.println("value of Temp Key "+tempkey+" Value of Tempdata "+tempData);
                            dataMap.put(tempkey,tempData);
                        }
                    }
                   // System.out.println("Value of KEY "+getCellData(rowStart+1,1));
                    inputData.put(getCellData(rowStart+1,1),dataMap);


                }

                    System.out.println("Value test "+inputData.get("invalidLogin").get("AdminPassword"));














            }catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception found"+e);
            //return data;
        }

        return null;


    }

    public static void main(String[] args){
        ExcelReader excelReader=new ExcelReader();
        excelReader.initializeFile("TestData.xlsx");
        excelReader.initializeSheet("test_data");
        excelReader.getRowCount();
        excelReader.getExcelDataAsMap("TestData.xlsx","test_data","TestData1","EndData1");
        System.out.println("Value of Columns in 2 row "+excelReader.getColCount(2));
        String value=excelReader.getCellData(4,15);
        System.out.println("Value of String "+value);

    }

}
class CellRowAndColumnIndex{
    int rowindex;
    int columnindex;

    public void getCellIndexes(String searchString,XSSFSheet sheet){
        int row=0;
        int column=0;
        for(int i=0;i<=sheet.getLastRowNum();i++){
            Row r=sheet.getRow(i);
            Iterator cellIterator=r.cellIterator();
            String cellValue=null;
            while(cellIterator.hasNext()){
                Cell cell=(Cell) cellIterator.next();


            }
        }


    }

}

