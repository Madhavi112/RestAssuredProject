package IBM.RestAssured;

import java.io.FileInputStream;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;

public class RestAssuredSample2 {

	@Test(enabled = false) //Create an user by passing JSON body as String
	
	public void jsonToString() {
		
		String reqbody = "{\"employeenumber\":189,\"employeename\":\"Gayathri\",\"address\":\"bb2\",\"state\":\"AP\"}";
		RestAssured.baseURI = "http://localhost:3000";
		
		RestAssured.
		given().
		 contentType(ContentType.JSON).body(reqbody).
		when().
		 post("/upskill").
		then().
		 statusCode(201).
		 log().
		 all();
	}
	
	@Test (enabled = false) //Create an user by passing JSON body as .json file
	
	public void jsonInputFile() throws IOException
	
	{
		
		FileInputStream f1 = new FileInputStream(".\\JSON\\user.json");
		RestAssured.baseURI = "http://localhost:3000";
		RestAssured.
		given().
		 contentType(ContentType.JSON).body(IOUtils.toString(f1,"UTF-8")).
		when().
		 post("/upskill").
		then().
		 statusCode(201).
		 log().
		 all();
		
	}
	
	@Test //Create an user by passing JSON body as JSON Object & strore the id and pass to another method
	
	public void jsonObject(ITestContext testvar1) {
		
		JSONObject obj = new JSONObject();
		obj.put("employeenumber", 187);
		obj.put("employeename", "RM");
		obj.put("address", "dd2");
		obj.put("state", "AP");
		
        RestAssured.baseURI = "http://localhost:3000";
		
		Response resobj = RestAssured.given().contentType(ContentType.JSON).body(obj.toJSONString()).
		when().
		  post("/upskill").
		then().
		  statusCode(201).
		  log().
		  all().extract().response();
		
		String state = resobj.jsonPath().getString("state");
		String id = resobj.jsonPath().getString("id");
		testvar1.setAttribute("ID1", id);
		System.out.println("ID is :" +id);
		Assert.assertEquals(state, "AP");
	}
	
	@Test(dependsOnMethods = "jsonObject") // get the id from previous method & print the details of particular user
	
	public void getDetails(ITestContext testvar1) {
		
		String ID = testvar1.getAttribute("ID1").toString();
		RestAssured.baseURI = "http://localhost:3000";
		RestAssured.
		given().
		  get("/upskill/"+ID).
		then().
		 statusCode(200).
		 log().
		 body();
		
	}
}
