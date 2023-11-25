package PetStore;

import java.io.IOException;

import Utility.ExcelUtiles;

public class DD_Stores {

   public static void main(String[] args) throws IOException {
       System.out.println(ExcelUtiles.getExcelDataAsListOfMap("StoreData", "Sheet1"));

   }
}