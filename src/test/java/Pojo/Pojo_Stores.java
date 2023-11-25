package Pojo;

import lombok.Builder;
import lombok.Data;

@Data()
@Builder()
public class Pojo_Stores {
	
    private String id;
    private String petId;
    private String quantity;
    private String shipDate;
    private String status;
    private String complete;
    
}