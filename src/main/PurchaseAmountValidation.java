package main;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class PurchaseAmountValidation {
	
	@Test
	public void sumOfCourses(){
		
		JsonPath js1 = new JsonPath(payload.CoursePrice());
		int count = js1.getInt("courses.size()");
		
		System.out.println("No of Courses Offered is:"+count);
		System.out.println("Verify If Sum of All Courses matches with Purchase amount.");
		int sum = 0;
		for(int i=0;i<count;i++) {
			
			
			int price = js1.getInt("courses["+i+"].price") ;
			int copies = js1.getInt("courses["+i+"].copies");
			int total = price * copies;
			sum = sum + total;
			
			
		}
		System.out.println("Total amount purchase is:"+sum);
		
		int total_amount = js1.getInt("dashboard.purchaseAmount");
		System.out.println("Total Purchase Amount is: "+total_amount);
		Assert.assertEquals(total_amount, sum);
	}
	

}
