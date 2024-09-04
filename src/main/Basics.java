package main;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsuableMethods;
import files.payload;


public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Given - All the input details
		//When - Submit the API (Resource and Http will go here)
		//Then - Validate the response
		
		RestAssured.baseURI	 = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick")
		.header("Content-Type","application/json")
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println(placeId);
		
		//Update Place API
		String newaddress = "Brundavana nagar";
		given().log().all().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place API
		String getplaceresponse = given().log().all().queryParam("key", "qaclick123")
	    .queryParam("place_id", placeId)
	    .when().get("maps/api/place/get/json")
	    .then().assertThat().log().all().statusCode(200)
	    .extract().response().asString();
		
		JsonPath js1 = ReUsuableMethods.rawToJson(getplaceresponse);
		String actualaddress=js1.getString("address");
		System.out.println(actualaddress);
		Assert.assertEquals(actualaddress, newaddress);
			

	}

}
