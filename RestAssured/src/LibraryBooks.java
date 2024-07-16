import org.testng.annotations.Test;

import files.ReUsuableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class LibraryBooks {
	
	@Test
	public void addBook() {
		RestAssured.baseURI ="http://216.10.245.166";
			String response = given().header("Content-Type","application/json").
			body(payload.AddBooks()).
			when()
			.post("/Library/Addbook.php").
			then().assertThat().statusCode(200)
			.extract().response().asString();
			
			JsonPath js=ReUsuableMethods.rawToJson(response);
			String id = js.get("ID");
			System.out.println(id);
		
		
	}
	

}
