package PetStore;

import java.util.HashMap;
import java.util.Map;

import Pojo.Pojo_CraeteStores;
import Pojo.Pojo_Poiji_Stores;
import Pojo.Pojo_Stores;
import RestAssured_Utility.RA_Utiles;
import io.restassured.response.Response;

public class StoreAPIs {
    Response response= null;
    public Response createPetOrder(Map<String,Object> createPetOrderPayload){

        String endPoint = (String)Base.dataFromJsonFile.get("post_createPetOrderEndPoint");

        response = RA_Utiles.performPost(endPoint, createPetOrderPayload, new HashMap<>());

        return  response;
    }

    public Response createPetOrder(Pojo_Stores createPetOrderPayload){
    	
        String endPoint = (String)Base.dataFromJsonFile.get("post_createPetOrderEndPoint");

        response = RA_Utiles.performPost(endPoint, createPetOrderPayload, new HashMap<>());

        return  response;
    }
    
    public Response createPetOrder(Pojo_Poiji_Stores createPetOrderPayloadFromPojoPoiji){
    	
        String endPoint = (String)Base.dataFromJsonFile.get("post_createPetOrderEndPoint");

        response = RA_Utiles.performPost(endPoint, createPetOrderPayloadFromPojoPoiji, new HashMap<>());

        return  response;
    }
    
    public Response createPetOrder(Pojo_CraeteStores pojo_CraeteStoresDataDrivern){
    	
        String endPoint = (String)Base.dataFromJsonFile.get("post_createPetOrderEndPoint");

        response = RA_Utiles.performPost(endPoint, pojo_CraeteStoresDataDrivern, new HashMap<>());

        return  response;
    }


}
