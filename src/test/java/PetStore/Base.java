package PetStore;

import java.util.Map;

import Utility.JsonUtiles;

public class Base {
	
    public static Map<String,Object> dataFromJsonFile;
    static{
        //IF YOU WILL NOT PASS ENVIRONMENT VALUE FROM THE COMMAND LINE ARGUMENT THEN IT TAKES DEFAULT AS QA
        String environment = System.getProperty("environment")==null ? "Qa": System.getProperty("environment");
        try{
            dataFromJsonFile = JsonUtiles.getJsonDataAsMap("/"+environment+"/StoreApiData.json");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
