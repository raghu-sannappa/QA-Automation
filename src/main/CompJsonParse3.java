package main;

import io.restassured.path.json.JsonPath;

public class CompJsonParse3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsoninput2 = "{\r\n"
				+ "    \"instructor\": \"RahulShetty\",\r\n"
				+ "    \"url\": \"rahulshettycademy.com\",\r\n"
				+ "    \"services\": \"projectSupport\",\r\n"
				+ "    \"expertise\": \"Automation\",\r\n"
				+ "    \"courses\": {\r\n"
				+ "        \"webAutomation\": [\r\n"
				+ "            {\r\n"
				+ "                \"courseTitle\": \"Selenium Webdriver Java\",\r\n"
				+ "                \"price\": \"50\"\r\n"
				+ "            },\r\n"
				+ "            {\r\n"
				+ "                \"courseTitle\": \"Cypress\",\r\n"
				+ "                \"price\": \"40\"\r\n"
				+ "            },\r\n"
				+ "            {\r\n"
				+ "                \"courseTitle\": \"Protractor\",\r\n"
				+ "                \"price\": \"40\"\r\n"
				+ "            }\r\n"
				+ "        ],\r\n"
				+ "        \"api\": [\r\n"
				+ "            {\r\n"
				+ "                \"courseTitle\": \"Rest Assured Automation using Java\",\r\n"
				+ "                \"price\": \"50\"\r\n"
				+ "            },\r\n"
				+ "            {\r\n"
				+ "                \"courseTitle\": \"SoapUI Webservices testing\",\r\n"
				+ "                \"price\": \"40\"\r\n"
				+ "            }\r\n"
				+ "        ],\r\n"
				+ "        \"mobile\": [\r\n"
				+ "            {\r\n"
				+ "                \"courseTitle\": \"Appium-Mobile Automation using Java\",\r\n"
				+ "                \"price\": \"50\"\r\n"
				+ "            }\r\n"
				+ "        ]\r\n"
				+ "    },\r\n"
				+ "    \"linkedIn\": \"https://www.linkedin.com/in/rahul-shetty-trainer/\"\r\n"
				+ "}";
		
		JsonPath js1 = new JsonPath(jsoninput2);
		int count = js1.getInt("courses.size()");
		String inst = js1.get("instructor");
		String exper = js1.getString("expertise");
		String linked = js1.getString("linkedIn");
		
		System.out.println("Instructor is :"+inst);
		System.out.println("Expertise is :"+exper);
		System.out.println("Linked In profile URL is :"+linked);
		System.out.println("Total Courses Array Size is :"+count);
		
		for(int i=0;i<count;i++) {

			String courses = js1.get("webAutomation["+i+"]");
			System.out.println(courses);
		
		
		}
	}

}
