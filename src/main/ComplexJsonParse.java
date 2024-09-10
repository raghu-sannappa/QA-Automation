package main;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js1 = new JsonPath(payload.CoursePrice());
		
		//Print NO of Courses Returend by API
		// size method works only on array.
		int count = js1.getInt("courses.size()");
		System.out.println("No of Courses Offered is:"+count);
		
		//Print Total Purchase Amount.
		int total_amount = js1.getInt("dashboard.purchaseAmount");
		System.out.println("Total Purchase Amount is: "+total_amount);
		
		//Print Title of First Course.
		String FisrtCourseTitle = js1.getString("courses[0].title");
		System.out.println("First Couse Title is :" +FisrtCourseTitle);
		
		//Print all courses and prices consider if array is dynamic.
		for(int i=0;i<count;i++) {
		String CourseTitles = js1.get("courses["+i+"].title");
		System.out.println(CourseTitles);
		System.out.println( js1.get("courses["+i+"].price").toString());
		
		}
		
		// Print no of copies sold by RPA
		
		System.out.println("Print No of Copies Sold By RPA");
		
		for(int i=0;i<count;i++) {
			
			String coursetitles = js1.get("courses["+i+"].title");
			if(coursetitles.equalsIgnoreCase("RPA")) {
				
				System.out.println("No of copies sold by RPA :" + js1.get("courses["+i+"].copies") );
				break; //Once RPA found it will come out of loop.
			}
		}
		
		System.out.println("Verify If Sum of All Courses matches with Purchase amount.");
		int sum = 0;
		for(int i=0;i<count;i++) {
			
			
			int price = js1.getInt("courses["+i+"].price") ;
			int copies = js1.getInt("courses["+i+"].copies");
			int total = price * copies;
			sum = sum + total;
			
			
		}
		System.out.println("Total amount purchase is:"+sum);
		
		if(total_amount==sum) {
			
			System.out.println("Total Purchase amount is:"+sum);
		}
		
		
		
	}

}
