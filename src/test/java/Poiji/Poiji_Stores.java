package Poiji;

import java.util.List;
import java.util.Map;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;

import lombok.ToString;

//NOTE: HERE @ToString 	ANNOTATION PASS THE EXCEL VALUE AS A STRING

@ToString
public class Poiji_Stores {

    @ExcelCellName("id")
    private String id;

    @ExcelCellName("petId")
    private String petId;

    @ExcelCellName("quantity")
    private String quantity;

    @ExcelCellName("shipDate")
    private String shipDate ;
    
    @ExcelCellName("status")
    private String status ;

//    //for more than one status at a time
//    @ExcelCellName("status")
//    private List<String> status;

    @ExcelCellName("complete")
    private String complete;

    //IT WILL HANDLE UNKNOWS EXCEL HEADING VALUSE
    @ExcelUnknownCells
    private Map<String,String> extracells;

}
