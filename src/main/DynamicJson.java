package main;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReUsuableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(priority =2,dataProvider = "sendBooksInfo")
	
	public void addBook(String isbn,String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String resp = 	given().log().all().header("Content-Type","application/json")
		
		//Dynamically build json payload with external data inputs.	
		.body(payload.AddBooksLibrary(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		
		.extract().response().asString();
		
		JsonPath js1 = ReUsuableMethods.rawToJson(resp);
		System.out.println(resp);
		String id = js1.get("ID");
		String msg = js1.getString("Msg");
		System.out.println(id);
		System.out.println(msg);
	//	Assert.assertEquals(msg,"successfully added" );
	}
	
	@Test(priority =2,dataProvider = "deleteBooksLibrary" )
	
	public void deleteBooks(String id) {
		
		 RestAssured.baseURI = "http://216.10.245.166";
		 String res1 = given().log().all().header("Content-Type","application/json")
		.body(payload.DeleteBookLibrary(id))
		.when().delete("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("book is successfully deleted"))
		.extract().response().asString();
		 
		 JsonPath js1 = ReUsuableMethods.rawToJson(res1);
			System.out.println(res1);
			String id1 = js1.get("ID");
		
	
	
	}
	
	@DataProvider(name = "sendBooksInfo")
	
	public Object[][] getData() {
		
		return new Object [][] {{"BNN","123"},{"BMW","456"},{"BENZ","789"}};
	}
	
	@DataProvider(name = "deleteBooksLibrary")
	
	public Object[] deleteData() {
		
		return new Object [] {"BNN123","BMW456","BENZ789"};
	}

}
