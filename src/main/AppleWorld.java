package main;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;


import files.payload;

public class AppleWorld {
	
	String Obj_Id;
	
	@Test(priority = 1)
	public void addNewObjects() {
		
	String res = given().log().all().header("Content-Type","application/json")
		.body(payload.addObjects())
		.when().post("https://api.restful-api.dev/objects")
		.then().log().all().assertThat().statusCode(200)
		.body("name", equalTo("Apple MacBook Air Pro 18"))
		.extract().response().asString();
		System.out.println(res);
		
		JsonPath js1 = new JsonPath(res);
	
		 Obj_Id = js1.getString("id");
		 String Proname = js1.getString("name");
		 String CreatedDate = js1.getString("createdAt");
		 
		/* int data1 = js1.get("data['year']");
		 String model =  js1.get("data['CPU model']");
		System.out.println(Obj_Id);
		 System.out.println(Proname);
		 System.out.println(CreatedDate);
		 System.out.println(data1);
		 System.out.println(model);*/
		 
		 
		 
		// String respayload = payload.addObjects();
		 //JsonObject obj = new JsonObject(respayload);
		 
		   Map<String, Object> dataMap = js1.getMap("data");

	        // Loop through each key-value pair in the "data" object
	        System.out.println("Data:");
	        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
	            System.out.println(entry.getKey() + ": " + entry.getValue());
	        }
		 
		
	}
	
	@Test(priority = 2)
	public void getAllObjectDetails() {
		
		
		String res = given().log().all().queryParam("id",Obj_Id)
				.when().get("https://api.restful-api.dev/objects")
			    .then().assertThat().log().all().statusCode(200)
			    .extract().response().asString();	
		
		System.out.println(res);
	}
	

}
