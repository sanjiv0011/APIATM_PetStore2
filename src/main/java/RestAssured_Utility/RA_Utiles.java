package RestAssured_Utility;

import java.util.Map;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.fge.jsonschema.core.util.AsJson;

import Reporting.ExtentReportManager;
import Reporting.Setup;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class RA_Utiles {
    private static RequestSpecification getRequestSpecifications(String endPoint, Object payload, Map<String,String> headers){

        return RestAssured.given()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(payload);

    }

    public static void printRequestLogInReport(RequestSpecification requestSpecification){

        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("<div style='color: #000000;'><span style='font-weight:bold';>End Point: </span>"+queryableRequestSpecification.getBaseUri()+"</div>");
        ExtentReportManager.logInfoDetails("<div style='color: #000000;'><span style='font-weight:bold';>Request Type: </span>"+queryableRequestSpecification.getMethod()+"</div>");
        ExtentReportManager.logInfoDetails("<div style='color: #000000;'><span style='font-weight:bold';>Request Header: </span></div>");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        
        ExtentReportManager.logInfoDetails("<div style='color: #000000;'><span style='font-weight:bold';>Request Body: </span> </div>");
        ////IT WILL NOT ANABLE THE DROPDOWN BUTTON
        //ExtentReportManager.logJson(queryableRequestSpecification.getBody());
        
        //IT WILL ENABLES DROPDOWN BUTTON
        String requestAsString = queryableRequestSpecification.getBody();
        String requestMarkup = MarkupHelper.createCodeBlock(requestAsString, CodeLanguage.JSON).getMarkup();
        String requestDetails = "<details><summary><span style='font-weight:bold';>View Request Details</span></summary>"+requestMarkup+"</details>";
        Setup.extentTest.get().info(requestDetails);

    }

    public static void  printResponseLogInReport(Response response){
        ExtentReportManager.logInfoDetails("<div style='color: #000000;'><span style='font-weight:bold';>Response Status Code: </span>"+response.getStatusCode()+"</div>");
        ExtentReportManager.logInfoDetails("<div style='color: #000000;'><span style='font-weight:bold';>Response Time: </span>"+response.getTime()+"</div>");

        ExtentReportManager.logInfoDetails("<div style='color: #000000;'><span style='font-weight:bold';>Response Header: </span></div>");
        ExtentReportManager.logHeaders(response.getHeaders().asList());
        
        ExtentReportManager.logInfoDetails("<div style='color: #000000;'><span style='font-weight:bold';>Response Body: </span></div>");
        ////IT WILL NOT ANABLE THE DROPDOWN BUTTON
        //ExtentReportManager.logJson(response.getBody().prettyPrint());
        
        //IT WILL ENABLES DROPDOWN BUTTON
        String responseAsString = response.getBody().prettyPrint();
        String responseMarkup = MarkupHelper.createCodeBlock(responseAsString, CodeLanguage.JSON).getMarkup();
        String responseDetails = "<details><summary><span style='font-weight:bold';>View Response Details </span></summary>"+responseMarkup+"</details>";
        Setup.extentTest.get().info(responseDetails);
        
    }


    //String payload
    public  static Response   performPost(String endPoint, String payload, Map<String,String> headers){
        RequestSpecification requestSpecification = getRequestSpecifications(endPoint,payload,headers);
        Response  response = requestSpecification.post();

        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);

        return response;
    }

    //Map payload
    public  static  Response performPost(String endPoint, Map<String, Object> payload, Map<String,String> headers){
        RequestSpecification requestSpecification = getRequestSpecifications(endPoint,payload,headers);
        Response  response = requestSpecification.post();

        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);

        return  response;
    }

    public  static  Response performPost(String endPoint, Object payloadFromPojo, Map<String,String> headers){
        RequestSpecification requestSpecification = getRequestSpecifications(endPoint,payloadFromPojo,headers);
        Response  response = requestSpecification.post();

        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);

        return  response;
    }

}
