package cucumber.Options;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	static RequestSpecification req;
	static String place_id;

	public static RequestSpecification requestBuilder() throws IOException
	{
		if(req==null)
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		 req = new RequestSpecBuilder().setBaseUri(globalFileScanner("baseUrl")).addQueryParam("key","qaclick123").
				setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		return req;
		}
		return req;
	}
    public static ResponseSpecification responseBuilder()
    {
    	ResponseSpecification resspc = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	return resspc;
    }
    
    public static String globalFileScanner(String key) throws IOException
    {
    	Properties ps = new Properties();
    	FileInputStream fis = new FileInputStream("C:\\Users\\satheess\\eclipse-workspace\\RestAssure\\APITesting\\src\\test\\java\\cucumber\\Options\\file.properties");
    	ps.load(fis);
    	return ps.getProperty(key);
    }
    
    public static String getJsonResponseString(Response response,String key)
    {
    	JsonPath js = new JsonPath(response.asString());
    	return js.getString(key);
    	
    }
    public static String JsonToStringConverter(String place_id)
    {
    	return "{\r\n"
    			+ "    \"place_id\":\""+place_id+"\"\r\n"
    			+ "}";
    }
    public enum ApiResource {
    	
    	addPlaceAPI("/maps/api/place/add/json"),
    	getPlaceAPI("/maps/api/place/get/json"),
    	deletePlaceAPI("/maps/api/place/delete/json");
        public String resource;
        
        ApiResource(String resource)
        {
        	this.resource = resource;
        }
        
        public String getResource()
        {
        	return resource;
        }

    }
}
