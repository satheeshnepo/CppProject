package googlemapspojo;

import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;

public class Addplace extends GoogleBody{

	public GoogleBody getGoogleBody(String name, String language, String address) {
		GoogleBody p = new GoogleBody();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLat(33.427362);
		p.setLocation(l);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setName(name);
		List<String> value = new ArrayList<String>();
		value.add("shoe park");
		value.add("shop");
		p.setTypes(value);
		return p;
		}
}
//    public static void main(String[] args)
//    {
//    	Addplace a = new Addplace();
//    	System.out.println(a.getGoogleBody());
//		RestAssured.baseURI = "https://rahulshettyacademy.com";
//		String response = RestAssured.given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").
//		body(a.getGoogleBody()).when().post("/maps/api/place/add/json").then().statusCode(200).body("scope",equalTo("APP")).header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
//		System.out.println(response);
//    }
//}
