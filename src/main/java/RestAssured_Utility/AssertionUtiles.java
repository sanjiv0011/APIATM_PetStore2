package RestAssured_Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Reporting.ExtentReportManager;
import Reporting.Setup;
import io.restassured.response.Response;

public class AssertionUtiles {
	
	
	
    public static void assertExceptedValuseWithJsonPath(Response response, Map<String,Object> expectedValuesMap)
    {
    	SoftAssert softAssert = new SoftAssert();

        List<AssertionKeys> actualValuesMap = new ArrayList<>();

        actualValuesMap.add(new AssertionKeys("JSON_PATH", "EXPECTED_VALUE", "ACTUAL_VALUE", "RESULT"));
        boolean allMatched = true;

        Set<String> jsonPaths = expectedValuesMap.keySet();
        
        for (String jsonPath : jsonPaths) {
            Optional<Object> actualValue = Optional.ofNullable(response.jsonPath().get(jsonPath));

            if (actualValue.isPresent()) {
                Object value = actualValue.get();
                String stringValue = String.valueOf(value);
                String expectedStringValue = String.valueOf(expectedValuesMap.get(jsonPath));

                if (stringValue.equals(expectedStringValue)) {
                    actualValuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "Matched"));
                    
                } else {
                    allMatched = false;
                    actualValuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "Not Matched"));
                    //softAssert.assertEquals(expectedValuesMap.get(jsonPath), value);
                    softAssert.fail("Check: View Assertion Details");
                }
            } else {
                allMatched = false;
                actualValuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), "Value not found", "Not Matched"));
                //softAssert.fail(expectedValuesMap.get(jsonPath)+" : Value not found");
                softAssert.fail("Check: View Assertion Details");
            }
        }


        if(allMatched){
            ExtentReportManager.logPassDetails("<span style='font-weight:bold';>All Assertions are passed ✔✔✔</span>");
            
        }else{
            ExtentReportManager.logFailDetails("<span style='font-weight:bold';>All Assertions are not passed ❌❌❌</span>");
        }
            String[][] finalAssertionMap =  actualValuesMap.stream().map(assertions -> new String[]{assertions.getJsonPath(),String.valueOf(assertions.getExpectedValue()),String.valueOf(assertions.getActualValue()),String.valueOf(assertions.getResult())}).toArray(String[][] :: new);
            
            ////IT WILL PRINT WITHOUT DROP BUTTONS
            //Setup.extentTest.get().info(MarkupHelper.createTable(finalAssertionMap));
            
            
            //IT WILL ENABLES DROPDOWN BUTTON
            Markup tableHtml = MarkupHelper.createTable(finalAssertionMap); 
            String arrayAssertionViewDetails = "<details><summary><span style='font-weight:bold';>View Assertion Details</span></summary>" + tableHtml.getMarkup()+ "</details>";
            Setup.extentTest.get().info((arrayAssertionViewDetails));
           
            softAssert.assertAll();
    }

}
