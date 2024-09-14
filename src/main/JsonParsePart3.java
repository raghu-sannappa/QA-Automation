package main;

import java.util.List;


import io.restassured.internal.path.json.mapping.JsonPathGsonObjectDeserializer;
import io.restassured.path.json.JsonPath;

public class JsonParsePart3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsoninput = "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Padma S Testing Academy\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
		
		JsonPath js1 = new JsonPath(jsoninput);
		int count = js1.getInt("types.size()");
		float latitude = js1.get("location.lat");
		float longitude = js1.get("location.lng");
		int accu = js1.getInt("accuracy");
		String web = js1.getString("website");

		System.out.println("Latitude is:"+latitude);
		System.out.println("Longitude is:"+longitude);
		System.out.println("accuracy is:"+accu);
		System.out.println("Website is:"+web);
		System.out.println("Count of types Array is:"+count);
		
		for(int i=0;i<count;i++) {
			String typesnames = js1.getString("types["+i+"]");
			System.out.println(typesnames);
		}
		
		
		
	}

}
