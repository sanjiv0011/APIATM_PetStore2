package Payload;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import Enum.Status;
import Pojo.Pojo_Stores;

public class Payload {

	public static Logger logger = LogManager.getLogger(Payload.class);

    static Faker faker = new Faker();
    //THIS IS THE WAY TO PASS FIXED VALUES(BY USING STREAM CONCEPT)
    static String statusFromStream = Stream.of("Available","Not Available","Placed").findAny().get();
    static  String completeFromStream = Stream.of("true","false").findAny().get();

    //THIS IS THE WAY TO PASS FIXED VALUES(BY USING ARRAY LIST)
    static String statusFromArrayList = Arrays.asList("Available","Not Available","Placed").get(faker.number().numberBetween(0,3));

    //THIS IS USED TO TAKES PAYLOAD FROM THE ENUM CLASS
    static Status statusFromEnum = Stream.of(Status.values()).findAny().get();

    public  static String getCreatePetOrderPayloadFromString(String id,String petId,String quantity,String shipdate,String status,boolean complete ){
    	logger.info("Start Payload: getCreatePetOrderPayloadFromString");
        String payload = "{\n" +
                "  \"id\": "+id+",\n" +
                "  \"petId\": "+petId+",\n" +
                "  \"quantity\": "+quantity+",\n" +
                "  \"shipDate\": \""+shipdate+"\",\n" +
                "  \"status\": \""+status+"\",\n" +
                "  \"complete\": "+complete+"\n" +
                "}";

        return  payload; 
       
    }

    public  static Map<String, Object> getCreatePetOrderPayloadFromMap(String id, String petId, String quantity, String shipDate, String status, boolean complete ){
    	logger.info("Start Payload: getCreatePetOrderPayloadFromMap");
    	Map<String,Object> payload = new HashMap<>();

        payload.put("id",id);
        payload.put("petId",petId);
        payload.put("quantity",quantity);
        payload.put("shipDate",shipDate);
        payload.put("status",status);
        payload.put("complete",complete);

        return  payload;
    }



    public  static Map<String, Object> getCreatePetOrderPayloadFromMap(){
    	logger.info("Start Payload: getCreatePetOrderPayloadFromMap");
        Map<String,Object> payload = new HashMap<>();
        Faker faker = new Faker();
        payload.put("id",faker.number().digits(5));
        payload.put("petId",faker.number().digits(5));
        payload.put("quantity",faker.number().digits(3));
        payload.put("shipDate","2023-11-21T09:51:58.683Z");
        payload.put("status",completeFromStream);
        payload.put("complete",completeFromStream);

        return  payload;
    }


    public  static Map<String, Object> getCreatePetOrderPayloadFromMapAndEnum(){
    	logger.info("Start Payload: getCreatePetOrderPayloadFromMapAndEnum");
        Map<String,Object> payload = new HashMap<>();
        Faker faker = new Faker();
        payload.put("id",faker.number().digits(5));
        payload.put("petId",faker.number().digits(5));
        payload.put("quantity",faker.number().digits(3));
        payload.put("shipDate","2023-11-21T09:51:58.683Z");
        payload.put("status",statusFromEnum);
        payload.put("complete",completeFromStream);

        return  payload;
    }
    
    
    public static Pojo_Stores getCreatePetOrderPayloadFromPojo() {
    	logger.info("Start Payload: getCreatePetOrderPayloadFromPojo");
        Faker faker = new Faker();
	     return   Pojo_Stores.builder()
		        .id(faker.number().digits(5))
		        .petId(faker.number().digits(5))
		        .quantity(faker.number().digits(3))
		        .shipDate("2023-11-21T09:51:58.683Z")
		        .status("placed")
		        .complete("true")
		        .build();
	     
	}



}
