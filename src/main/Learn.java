package main;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsuableMethods1;
import files.payload01;

public class Learn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParams("key","qaclick")
		.header("Content-Type","application/json")
		.body(payload01.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		System.out.println(response);
		
		JsonPath js1 = new JsonPath(response);
		String placeId = js1.getString("place_id");
		System.out.println(placeId);
		
		
		//Update Place API
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		 given().queryParams("key", "qaclick")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\"1234 Basveshwara Nagar Pipeline, India\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().body("msg", equalTo("Address successfully updated"));
		
		
		
		
		

	}

}
