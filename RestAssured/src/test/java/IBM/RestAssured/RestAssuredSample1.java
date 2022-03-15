package IBM.RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredSample1 {
   
   @Test (enabled = false)
   
   public void testCase() {
	   
	   //Response res = RestAssured.get("http://localhost:3000/upskill"); // get all the records present in URL
	   Response res = RestAssured.get("http://localhost:3000/upskill/1"); // get the particular record present in URL
	   String resbody = res.asString();
	   System.out.println(resbody); // print the records
	   System.out.println("");
	   System.out.println(res.getStatusCode()); // print the response code
	   System.out.println("");
	   System.out.println(res.getStatusLine()); // print entire response line
	   System.out.println("");
	   System.out.println(res.getHeaders()); // print entire response
	   System.out.println("");
	   System.out.println(res.jsonPath().getString("address")); //print the address of the particular record
	   
   }
   
   @Test(enabled = false)
   
   public void testCase1() {
	   
	   RestAssured.baseURI = "http://localhost:3000";
	   //RestAssured.given().get("/upskill").then().statusCode(200).log().body(); // get all the records present in URL
	   RestAssured.given().get("/upskill/6").then().statusCode(200).log().body(); // get the particular record present in URL
	   
   }
   
   @Test
   
   public void testCase2() {
	
	   RestAssured.baseURI = "https://petstore.swagger.io/v2/user";
	   RestAssured.
	   given().contentType(ContentType.JSON).queryParam("username","Madhavi").queryParam("password", "Madhavi@123").
	   when().get("/login?username=Madhavi&password=Madhavi@123").then().statusCode(200).log().all();
	   
   }
   
}
