package IBM.RestAssured;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.PojoClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAssuredSample3 {

	@Test
	
	public void pojoExample() throws JsonProcessingException {
		
		PojoClass obj = new PojoClass();
		obj.setEmployeenumber("456");
		obj.setEmployeename("ML");
		obj.setAddress("ee");
		obj.setState("TN");
		
		System.out.println(obj.getEmployeenumber());
		System.out.println(obj.getEmployeename());
		System.out.println(obj.getAddress());
		System.out.println(obj.getState());
		
		ObjectMapper obj1 = new ObjectMapper();
		
		String body = obj1.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		
		System.out.println(body);
		
		RestAssured.baseURI = "http://localhost:3000";
		RestAssured.
		given().
		  contentType(ContentType.JSON).body(body).
		when().
		  post("/upskill").
		then().
		  statusCode(201).
		  log().
		  body();
	}
}
