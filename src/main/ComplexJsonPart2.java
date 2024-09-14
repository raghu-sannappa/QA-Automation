package main;

import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonPart2 {

	public static void main(String[] args) {
		
		String jsonArrayStr = "[\n" +
                "    {\n" +
                "        \"id\": \"1\",\n" +
                "        \"name\": \"Google Pixel 6 Pro\",\n" +
                "        \"data\": {\n" +
                "            \"color\": \"Cloudy White\",\n" +
                "            \"capacity\": \"128 GB\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"2\",\n" +
                "        \"name\": \"Apple iPhone 12 Mini, 256GB, Blue\",\n" +
                "        \"data\": null\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"3\",\n" +
                "        \"name\": \"Apple iPhone 12 Pro Max\",\n" +
                "        \"data\": {\n" +
                "            \"color\": \"Cloudy White\",\n" +
                "            \"capacity GB\": 512\n" +
                "        }\n" +
                "    }\n" +
                "]";

        // Parse the JSON array string
        JSONArray jsonArray = new JSONArray(jsonArrayStr);

        // Iterate over the JSON array
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // Extracting data from each JSON object
            String id = jsonObject.getString("id");
            String name = jsonObject.getString("name");

            // Handling the 'data' field
            JSONObject data = jsonObject.optJSONObject("data"); // Using optJSONObject to handle null values

            if (data != null) {
                String color = data.optString("color", "Not available"); // Default value if not found
                String capacity = data.optString("capacity", "Not available"); // Default value if not found
                System.out.println("ID: " + id + ", Name: " + name + ", Color: " + color + ", Capacity: " + capacity);
            } else {
                System.out.println("ID: " + id + ", Name: " + name + ", Data: null");
            }
        }
		
	}

}
