package Test;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;


import Payload.Payload;
import PetStore.StoreAPIs;
import Pojo.Pojo_Stores;
import RestAssured_Utility.AssertionUtiles;
import RestAssured_Utility.RA_Utiles;
import Utility.JsonUtiles;
import io.restassured.response.Response;

public class StoresTest extends  StoreAPIs{

    //@Test
    public  void createPetOrder1(){
          String endPoint =  "https://petstore.swagger.io/v2/store/order";
          String payload = Payload.getCreatePetOrderPayloadFromString("10","20","5","2023-11-21T09:51:58.683Z","placed",true);

          Response response = RA_Utiles.performPost(endPoint,payload, new HashMap<>());
          Assert.assertEquals(response.statusCode(),200);
    }

    //@Test
    public  void createPetOrder2(){

        Map<String,Object> data = JsonUtiles.getJsonDataAsMap("Qa/StoreApiData.json");
        String endPoint = (String)data.get("post_createPetOrderEndPoint");
        Map<String, Object> payload = Payload.getCreatePetOrderPayloadFromMap("10","20","5","2023-11-21T09:51:58.683Z","placed",true);

        Response response = RA_Utiles.performPost(endPoint, payload, new HashMap<>());
        Assert.assertEquals(response.statusCode(),200);
    }

    //@Test
    public  void createPetOrder3(){

        //IF YOU WILL NOT PASS ENVIRONMENT VALUE FROM THE COMMAND LINE ARGUMENT THEN IT TAKES DEFAULT AS QA
        String environment = System.getProperty("environment")==null ? "Qa": System.getProperty("environment");
        Map<String,Object> data = JsonUtiles.getJsonDataAsMap("/"+environment+"/StoreApiData.json");
        String endPoint = (String)data.get("post_createPetOrderEndPoint");

        Map<String, Object> payload = Payload.getCreatePetOrderPayloadFromMap("10","20","5","2023-11-21T09:51:58.683Z","placed",true);
        Response response = RA_Utiles.performPost(endPoint, payload, new HashMap<>());
        Assert.assertEquals(response.statusCode(),200);
    }

   //@Test
    public  void createPetOrder4(){

        Map<String, Object> payload = Payload.getCreatePetOrderPayloadFromMap("10","20","5","2023-11-21T09:51:58.683Z","placed",true);

        Response response = createPetOrder(payload);

        Assert.assertEquals(response.statusCode(),200);
    }

  //@Test
    public  void createPetOrder5(){

        Map<String, Object> payload = Payload.getCreatePetOrderPayloadFromMap();

        Response response = createPetOrder(payload);

        Assert.assertEquals(response.statusCode(),200);
    }

   //@Test
    public  void createPetOrder6(){

        Map<String, Object> payload = Payload.getCreatePetOrderPayloadFromMapAndEnum();

        Response response = createPetOrder(payload);

        Assert.assertEquals(response.statusCode(),200);
    }

   @Test
    public  void createPetOrder7(){

        Pojo_Stores payload = Payload.getCreatePetOrderPayloadFromPojo();
        
        Map<String,Object> expectedValueMap = new HashMap<>();
        expectedValueMap.put("id",payload.getId());
        expectedValueMap.put("petId",payload.getPetId());
        expectedValueMap.put("quantity",payload.getQuantity());
        expectedValueMap.put("complete",payload.getComplete());
        expectedValueMap.put("status",payload.getStatus());
        expectedValueMap.put("shipDate",payload.getShipDate());

        Response response = createPetOrder(payload);

        Assert.assertEquals(response.statusCode(),200);
        AssertionUtiles.assertExceptedValuseWithJsonPath(response,expectedValueMap);
    }


}
