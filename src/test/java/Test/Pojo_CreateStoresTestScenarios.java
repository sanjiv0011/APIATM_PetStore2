package Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.Element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import PetStore.StoreAPIs;
import Pojo.Pojo_CraeteStores;
import Reporting.Setup;
import RestAssured_Utility.AssertionUtiles;
import Utility.ExcelUtiles;
import io.restassured.response.Response;

public class Pojo_CreateStoresTestScenarios extends StoreAPIs {

	Logger logger = LogManager.getLogger(Pojo_CreateStoresTestScenarios.class);
	SoftAssert softAssert;

	//@Test(dataProvider = "createPetOrderDataTestScenarios")
	public void createPetOrderAndVerifyData(Pojo_CraeteStores pojo_CraeteStores) {

		softAssert = new SoftAssert();
		logger.info("Start test: createPetOrderDataTestScenarios");

		Response response = createPetOrder(pojo_CraeteStores);
		Map<String, Object> expectedValueMap = new HashMap<>();
		if (Integer.parseInt(pojo_CraeteStores.getExpectedCode()) != 200) {
			softAssert.assertEquals(response.getStatusCode(), Integer.parseInt(pojo_CraeteStores.getExpectedCode()));
			softAssert.assertEquals(response.jsonPath().get("message"), pojo_CraeteStores.getExpectedErrorMessage());
		} else {

			expectedValueMap.put("id", pojo_CraeteStores.getId());
			expectedValueMap.put("petId", pojo_CraeteStores.getPetId());
			expectedValueMap.put("quantity", pojo_CraeteStores.getQuantity());
			expectedValueMap.put("complete", pojo_CraeteStores.getComplete());
			expectedValueMap.put("status", pojo_CraeteStores.getStatus());
			expectedValueMap.put("shipDate", pojo_CraeteStores.getShipDate());

			if (pojo_CraeteStores.getTestScenarioDescription().split("_")[0].equalsIgnoreCase("MISSING")) {
				expectedValueMap.remove(pojo_CraeteStores.getTestScenarioDescription().split("_")[1]);
			}

		}

		softAssert.assertAll();
		AssertionUtiles.assertExceptedValuseWithJsonPath(response, expectedValueMap);

		logger.info("End test: createPetOrderDataTestScenarios");

	}

	@Test(dataProvider = "createPetOrderDataTestScenarios")
	public void createPetOrderAndVerifyData2(Pojo_CraeteStores pojo_CraeteStores) {
		logger.info("Start test: createPetOrderDataTestScenarios");
		testCaseDetails(pojo_CraeteStores);
		
		softAssert = new SoftAssert();

		Response response = createPetOrder(pojo_CraeteStores);
		Map<String, Object> expectedValueMap = new HashMap<>();
		if (Integer.parseInt(pojo_CraeteStores.getExpectedCode()) != 200) {
			softAssert.assertEquals(response.getStatusCode(), Integer.parseInt(pojo_CraeteStores.getExpectedCode()));
			softAssert.assertEquals(response.jsonPath().get("message"), pojo_CraeteStores.getExpectedErrorMessage());
		} else {

			expectedValueMap.put("id", pojo_CraeteStores.getId());
			expectedValueMap.put("petId", pojo_CraeteStores.getPetId());
			expectedValueMap.put("quantity", pojo_CraeteStores.getQuantity());
			expectedValueMap.put("complete", pojo_CraeteStores.getComplete());
			expectedValueMap.put("status", pojo_CraeteStores.getStatus());
			expectedValueMap.put("shipDate", pojo_CraeteStores.getShipDate());

			if (pojo_CraeteStores.getTestScenarioDescription().split("_")[0].equalsIgnoreCase("MISSING")) {
				expectedValueMap.remove(pojo_CraeteStores.getTestScenarioDescription().split("_")[1]);
			}

		}

		softAssert.assertAll();
		AssertionUtiles.assertExceptedValuseWithJsonPath(response, expectedValueMap);

		logger.info("End test: createPetOrderDataTestScenarios");

	}


	public void testCaseDetails(Pojo_CraeteStores pojo_CraeteStores) {
	    String callerMethod = Thread.currentThread().getStackTrace()[2].getMethodName();

	    Setup setup = new Setup();
	    Map<String, String> labelsToAddOnReport = new LinkedHashMap<>(); // Use LinkedHashMap
	    labelsToAddOnReport.put("TestCaseID", pojo_CraeteStores.getTestCaseID());
	    labelsToAddOnReport.put("TestMethodName", callerMethod);
	    labelsToAddOnReport.put("TestScenarioDescription", pojo_CraeteStores.getTestScenarioDescription());
	    setup.writeTestDetailsOnReport(labelsToAddOnReport);
	}

	
	
	@DataProvider(name = "createPetOrderDataTestScenarios")
	public Iterator<Pojo_CraeteStores> getCreateStoreData() throws IOException {

		List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelUtiles
				.getExcelDataAsListOfMap("StoreDataTestScenarios", "Sheet1");

		List<Pojo_CraeteStores> pojo_CraeteStores = new ArrayList<>();

		for (LinkedHashMap<String, String> data : excelDataAsListOfMap) {

			Pojo_CraeteStores pojoStoredata = getCustomizedStoreData(data);
			pojo_CraeteStores.add(pojoStoredata);

		}

		return pojo_CraeteStores.iterator();

	}

	private Pojo_CraeteStores getCustomizedStoreData(LinkedHashMap<String, String> data) {
		Pojo_CraeteStores createStoreData = new Pojo_CraeteStores();

		createStoreData.setExpectedCode(data.get("ExpectedCode"));
		System.out.println("ExpectedCode: " + createStoreData.getExpectedCode());

		createStoreData.setTestCaseID(data.get("TestCaseID"));
		System.out.println("TestCaseID: " + createStoreData.getTestCaseID());

		createStoreData.setTestScenarioDescription(data.get("TestScenarioDescription"));
		System.out.println("TestScenarioDescription: " + createStoreData.getTestScenarioDescription());

		if (!data.get("ExpectedErrorMessage").equals("NO_DATA")) {
			createStoreData.setExpectedErrorMessage(data.get("ExpectedErrorMessage"));
			System.out.println("ExpectedErrorMessage: " + createStoreData.getExpectedErrorMessage());
		}

		if (!data.get("id").equals("NO_DATA")) {
			createStoreData.setId(data.get("id"));
			System.out.println("id: " + createStoreData.getId());
		}
		if (!data.get("petId").equals("NO_DATA")) {
			createStoreData.setPetId(data.get("petId"));
			System.out.println("petId: " + createStoreData.getPetId());
		}
		if (!data.get("quantity").equals("NO_DATA")) {
			createStoreData.setQuantity(data.get("quantity"));
			System.out.println("quantity: " + createStoreData.getQuantity());
		}
		if (!data.get("shipDate").equals("NO_DATA")) {
			createStoreData.setShipDate(data.get("shipDate"));
			System.out.println("shipDate: " + createStoreData.getShipDate());
		}
		if (!data.get("status").equals("NO_DATA")) {
			createStoreData.setStatus(data.get("status"));
			System.out.println("status: " + createStoreData.getStatus());
		}
		if (!data.get("complete").equals("NO_DATA")) {
			createStoreData.setComplete(data.get("complete"));
			System.out.println("complete: " + createStoreData.getComplete());
		}

		return createStoreData;
	}

}
