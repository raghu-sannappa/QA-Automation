package main;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;

public class JiraIssueCreate {

	public static void main(String[] args) {
		
		
		RestAssured.baseURI = "https://raghu-sdet.atlassian.net/";
		
		String createIssueRes = given().header("Content-Type","application/json")
		.header("Authorization","Basic cmFnaHUuc2RldDEwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjAtUWZzd0xuaENORVZJYUZYQUJueXljdHdMbTh1dDZHSWtVcVlTYWVQRDhLa2NzTjBWS1NMNWx3QkJUQkRPM2poQ18wd3l0T2k5YXFkTkI5QXE3RmhuanRwOXhaY293QnRCOXVQZFpQVEVodmd5TkVTRC1WNFR6NDFqazZIMlItVTlCOGhCWDFGd2VUVWFVRS1hT3UzOEVUVE5mcnlOQmFlNTlwRjJoQlRfRHM9NzQxOTBGM0Y=")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"KAN\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"USB items Not Working- RestAssured Automation\",\r\n"
				+ "      \r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Task\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}")
		.log().all()
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js1 = new JsonPath(createIssueRes);
		String issueId = js1.get("id");
		System.out.println(issueId);
		
		//Add Attachments
		
		String attachResponse = given().pathParam("key", issueId)
		.header("Authorization","Basic cmFnaHUuc2RldDEwQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjAtUWZzd0xuaENORVZJYUZYQUJueXljdHdMbTh1dDZHSWtVcVlTYWVQRDhLa2NzTjBWS1NMNWx3QkJUQkRPM2poQ18wd3l0T2k5YXFkTkI5QXE3RmhuanRwOXhaY293QnRCOXVQZFpQVEVodmd5TkVTRC1WNFR6NDFqazZIMlItVTlCOGhCWDFGd2VUVWFVRS1hT3UzOEVUVE5mcnlOQmFlNTlwRjJoQlRfRHM9NzQxOTBGM0Y=")
		.header("X-Atlassian-Token","no-check")
		.multiPart("file", new File("C:/Users/Soumya/OneDrive/Pictures/Screenshots/RSA.png")).log().all()
		.when().post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	/*	JsonPath js2 = new JsonPath(attachResponse);
		String attachId = js2.get("id");
		String fileName = js2.get("filename");
		System.out.println(fileName);*/
		
		
		JSONArray jsonArray = new JSONArray(attachResponse);
		
		for(int i=0;i<jsonArray.length();i++) {
			
			// Get each JSON object
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            
            // Print details of each object
            System.out.println("Self: " + jsonObject.getString("self"));
            System.out.println("ID: " + jsonObject.getString("id"));
            System.out.println("Filename: " + jsonObject.getString("filename"));
            System.out.println("Created: " + jsonObject.getString("created"));
            System.out.println("Size: " + jsonObject.getInt("size"));
            System.out.println("Mime Type: " + jsonObject.getString("mimeType"));
            System.out.println("Content: " + jsonObject.getString("content"));
            System.out.println("Thumbnail: " + jsonObject.getString("thumbnail"));
            
            // Access nested 'author' object
            JSONObject author = jsonObject.getJSONObject("author");
            System.out.println("Author Display Name: " + author.getString("displayName"));
            System.out.println("Author Email: " + author.getString("emailAddress"));
            
            // Access nested 'avatarUrls' object inside 'author'
            JSONObject avatarUrls = author.getJSONObject("avatarUrls");
            System.out.println("Author Avatar 48x48: " + avatarUrls.getString("48x48"));
            
            System.out.println("-------------");
			
			
		}
        
        // Get the first object in the array
		
		
        JSONObject attachmentId = jsonArray.getJSONObject(0);
        JSONObject attachmentfileName  = jsonArray.getJSONObject(0);
        
        // Access the 'filename' field
        String attachId  = attachmentId.getString("id");
        String attachfilename = attachmentfileName.getString("filename");
        
        System.out.println("Attachment ID -: " + attachId);
        System.out.println("Attachment Filename-: " + attachfilename);
		
		

	}

}
