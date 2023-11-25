package Test;

import java.io.File;
import java.util.List;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import Poiji.Poiji_Stores;

public class PoijiTest {

    public  static void main(String[] args){

      PoijiOptions options =   PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(",").build();
       
      List<Poiji_Stores> poijiStoresListData =  Poiji.fromExcel(new File(System.getProperty("user.dir")+"/TestData/StoreData.xlsx"),Poiji_Stores.class, options);
       //System.out.println("poijiStoresListData: " + poijiStoresListData);
      
    }
}
