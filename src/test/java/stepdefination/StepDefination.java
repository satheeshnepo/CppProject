package stepdefination;

import static io.restassured.RestAssured.given;
//import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import cucumber.Options.ApiResource;
import cucumber.Options.Utils;
import googlemapspojo.Addplace;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

public class StepDefination {
	RequestSpecification res;
	RequestSpecification req;
	ResponseSpecification resspc;
	Response response;
	static String placeId;
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException 
	{
        req = Utils.requestBuilder();
		res = given().spec(req).body(new Addplace().getGoogleBody(name,language,address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource,String method) throws IOException {
		
	    cucumber.Options.Utils.ApiResource apiResource = Utils.ApiResource.valueOf(resource);
	    resspc = Utils.responseBuilder();
	    
	    if(method.equalsIgnoreCase("post"))
	   
	    	response = res.when().post(apiResource.getResource());
	    if(method.equalsIgnoreCase("get"))
	    	response= res.when().get(apiResource.getResource()).then().extract().response();
	    
	    if(method.equalsIgnoreCase("delete"))
	    	response= res.when().delete(apiResource.getResource()).then().extract().response();
	    System.out.println("Response ------------");
	    System.out.println(response.asString());
	    }   
	@Then("the API Call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    Assert.assertEquals(response.getStatusCode(),200);
		

	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String ExpectedValue) {
		
	   String resp = response.asString();
	   JsonPath js = new JsonPath(resp);
	   Assert.assertEquals(js.getString(key),ExpectedValue);
	}
	
	@Then("checkplaceid to {string} by using {string}")
	public void checkplaceid_to_by_using(String name, String resource) throws IOException {
		placeId = Utils.getJsonResponseString(response,"place_id");
		res = given().spec(req).queryParam("place_id",placeId);
		user_calls_with_post_http_request(resource,"GET");
		
		String afterResposneName = Utils.getJsonResponseString(response, "name");
		Assert.assertEquals(name, afterResposneName);
		
	}
	

	@Given("deletePlaceAPI")
	public void delete_place_api() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
//		System.out.println(response.asString());
//		System.out.println("Place id is = " + Utils.getJsonResponseString(response, "place_id"));
		System.out.println("place_id is for delete api is = " + placeId);
	     req = Utils.requestBuilder();
	     res = given().spec(req).body(Utils.JsonToStringConverter(placeId));
	     
	}


}
