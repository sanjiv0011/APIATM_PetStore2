package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poiji.annotation.ExcelCellName;

import Utility.RandomDataGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data()
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pojo_Poiji_Stores {

	private final String id = RandomDataGenerator.gerRandomNumber(3);
	
	@ExcelCellName("id")
	private String idValue;

	@ExcelCellName("petId")
	private String petId;

	@ExcelCellName("quantity")
	private String quantity;

	@ExcelCellName("shipDate")
	private String shipDate;

	@ExcelCellName("status")
	private String status;

	@ExcelCellName("complete")
	private String complete;

}