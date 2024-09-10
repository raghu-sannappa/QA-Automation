package main;

import org.testng.annotations.Test;

import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
import files.ReUsuableMethods;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class DemoOAuthTest {
	

	public static void main (String [] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		String accessToken = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type","client_credentials")
		.formParam("scope","scope")
		.when().log().all().post().then().log().all().extract().response().asString();
		
		JsonPath js1 =    ReUsuableMethods.rawToJson(accessToken);
		String token = js1.get("access_token");
		System.out.println(token);
		
		//Get Course Details
		// GetCourse is a POJO Class and gc is a object
		GetCourse gc = given().queryParam("access_token",token)
		.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.as(GetCourse.class);
		
		/*JsonPath js2 =    ReUsuableMethods.rawToJson(getRes);
		System.out.println(getRes);
		String instructor = js2.get("instructor");
		System.out.println(instructor);*/
		
		System.out.println(gc.getLinkedIN());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getExpertise());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		
		//Using List Array to traverse through array of Api course to get price of that course
		List<Api>apicourses = gc.getCourses().getApi();
		for(int i=0;i<apicourses.size();i++) {
			if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				
				System.out.println(   apicourses.get(i).getPrice());
			}
			
		}
		
		List<WebAutomation> courses = gc.getCourses().getWebAutomation();
		
		for(int i=0;i<courses.size();i++) {
			
			System.out.println(courses.get(i).getCourseTitle() +" :-"+ courses.get(i).getPrice());
		}
		
	}

}
