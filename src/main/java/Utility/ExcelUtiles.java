package Utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.github.javafaker.Faker;

public class ExcelUtiles {

    public static List<LinkedHashMap<String,String>> getExcelDataAsListOfMap(String excelFileName, String sheetName) throws IOException
    {
        List<LinkedHashMap<String,String>> dataFromExcel = new ArrayList<>();

       Workbook workbook  = WorkbookFactory.create(new File(System.getProperty("user.dir")+"/TestData/"+excelFileName+".xlsx"));
       Sheet sheet  = workbook.getSheet((sheetName));

       int totalRows = sheet.getPhysicalNumberOfRows();
       LinkedHashMap<String,String> mapData;
       List<String> allKeys = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();

       for(int i = 0; i<totalRows; i++)
       {
           mapData = new LinkedHashMap<>();
           if(i ==0)
           {
               int totalColumn = sheet.getRow(i).getPhysicalNumberOfCells();
               for (int j =0; j<totalColumn; j++){
                   allKeys.add(sheet.getRow(i).getCell(j).getStringCellValue());
               }
           }else{
               int totalColumn = sheet.getRow(i).getPhysicalNumberOfCells();
               for (int j =0; j<totalColumn; j++){

                 String cellValue =  dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));

                 int size = 8;
                 if(cellValue.contains("RandomNumber")){
                        if(cellValue.contains("_")){
                            size  = Integer.parseInt((cellValue.split("_"))[1]);
                        }
                        Faker faker = new Faker();
                        cellValue = faker.number().digits(size);
                       
                 }
                 
                 mapData.put(allKeys.get(j),cellValue);

               }
               dataFromExcel.add(mapData);
           }

       }

       dataFromExcel = dataFromExcel.stream().filter(keyValuePair -> keyValuePair.get("IsEnabled").equalsIgnoreCase("Y")).collect(Collectors.toList());
        return dataFromExcel;
    }

}
