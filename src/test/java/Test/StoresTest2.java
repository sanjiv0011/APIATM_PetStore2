package Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.poiji.bind.Poiji;

import Payload.Payload;
import PetStore.StoreAPIs;
import Poiji.Poiji_Stores;
import Pojo.Pojo_Poiji_Stores;
import Pojo.Pojo_Stores;
import RestAssured_Utility.AssertionUtiles;
import Utility.ExcelUtiles;
import io.restassured.response.Response;

public class StoresTest2 {

	//@Test
    public  void createPetOrder6(){
    	StoreAPIs  storeApis =  new StoreAPIs();

        Pojo_Stores  payload = Payload.getCreatePetOrderPayloadFromPojo();
       
        Map<String,Object> expectedValueMap = new HashMap<>();
        expectedValueMap.put("id",payload.getId());
        expectedValueMap.put("petId",payload.getPetId());
        expectedValueMap.put("quantity",payload.getQuantity());
        expectedValueMap.put("complete",payload.getComplete());
        expectedValueMap.put("status",payload.getStatus());
        expectedValueMap.put("shipDate",payload.getShipDate());

        Response response = storeApis.createPetOrder(payload);
        
        Assert.assertEquals(response.statusCode(),200);
        AssertionUtiles.assertExceptedValuseWithJsonPath(response,expectedValueMap);
    }


    //@Test(dataProvider = "createPetOrderData")
    public  void createPetOrderByDataDriven(Pojo_Stores pojoStores){
    	
    	 StoreAPIs  storeApis =  new StoreAPIs();
    	
    	 Map<String,Object> expectedValueMap = new HashMap<>();
         expectedValueMap.put("id",pojoStores.getId());
         expectedValueMap.put("petId",pojoStores.getPetId());
         expectedValueMap.put("quantity",pojoStores.getQuantity());
         expectedValueMap.put("complete",pojoStores.getComplete());
         expectedValueMap.put("status",pojoStores.getStatus());
         expectedValueMap.put("shipDate",pojoStores.getShipDate());

         
        Response response = storeApis.createPetOrder(pojoStores);

       
        Assert.assertEquals(response.statusCode(),200);
        AssertionUtiles.assertExceptedValuseWithJsonPath(response,expectedValueMap);


    }
    
    
   
    @DataProvider(name = "createPetOrderData")
    public Iterator<Pojo_Stores> getCreateStoreData() throws IOException {
    	
        List<LinkedHashMap<String,String>> excelDataAsListOfMap = ExcelUtiles.getExcelDataAsListOfMap("StoreData","Sheet1");

        List<Pojo_Stores> pojoStoresData = new ArrayList<>();

        for (LinkedHashMap<String,String> data :excelDataAsListOfMap){
            Pojo_Stores pojoStores = Pojo_Stores.builder()
                    .id(data.get("id"))
                    .petId(data.get("petId"))
                    .quantity(data.get("quantity"))
                    .shipDate(data.get("shipDate"))
                    .complete(data.get("complete"))
                    .status(data.get("status"))
                    .build();

            pojoStoresData.add(pojoStores);
           
        }

        return pojoStoresData.iterator();

    }
    
    
    
    
    
    @Test(dataProvider = "createPetOrderData_Pojo_Poiji")
    public  void createPetOrderByDataDriven_Pojo_Poiji(Pojo_Poiji_Stores pojo_Poiji_Stores){
    	StoreAPIs  storeApis =  new StoreAPIs();
    	String cellValue = pojo_Poiji_Stores.getIdValue();
    	
    	 int size = 8;
         if(cellValue.contains("RandomNumber")){
                if(cellValue.contains("_")){
                    size  = Integer.parseInt((cellValue.split("_"))[1]);
                }
                Faker faker = new Faker();
                cellValue = faker.number().digits(size);
               
         }
         
         pojo_Poiji_Stores.setIdValue(cellValue);
    	
        Response response = storeApis.createPetOrder(pojo_Poiji_Stores);

        Map<String,Object> expectedValueMap = new HashMap<>();
        expectedValueMap.put("id",pojo_Poiji_Stores.getId().trim());
        expectedValueMap.put("petId",pojo_Poiji_Stores.getPetId().trim());
        expectedValueMap.put("quantity",pojo_Poiji_Stores.getQuantity().trim());
        expectedValueMap.put("complete",pojo_Poiji_Stores.getComplete().trim());
        expectedValueMap.put("status",pojo_Poiji_Stores.getStatus().trim());
        expectedValueMap.put("shipDate",pojo_Poiji_Stores.getShipDate().trim());

        Assert.assertEquals(response.statusCode(),200);
        AssertionUtiles.assertExceptedValuseWithJsonPath(response,expectedValueMap);


    }

    
    @DataProvider(name = "createPetOrderData_Pojo_Poiji")
    public Iterator<Pojo_Poiji_Stores> getCreateStoreData_Pojo_Poiji() throws IOException {
    	List<Pojo_Poiji_Stores> poijiStoresListDataFromExcelSheet =  Poiji.fromExcel(new File(System.getProperty("user.dir")+"/TestData/StoreData.xlsx"),Pojo_Poiji_Stores.class);
       
        return poijiStoresListDataFromExcelSheet.iterator();

    }

}
