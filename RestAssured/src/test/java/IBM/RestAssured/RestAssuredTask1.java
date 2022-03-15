package IBM.RestAssured;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.PojoClass1;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredTask1 {

	@Test
	
	public void createUser(ITestContext var1 , ITestContext var2) throws JsonProcessingException {
		
		PojoClass1 obj1 = new PojoClass1();
		obj1.setId(0);
		obj1.setUsername("Gayathri");
		obj1.setFirstName("Gayathri");
		obj1.setLastName("R");
		obj1.setEmail("Gayathri@gmail.com");
		obj1.setPassword("Gayathri@123");
		obj1.setPhone("1234567890");
		obj1.setUserStatus(0);
		
		String uname = obj1.getUsername();
		String pwd = obj1.getPassword();
		
		System.out.println(obj1.getId());
		System.out.println(uname);
		System.out.println(obj1.getFirstName());
		System.out.println(obj1.getLastName());
		System.out.println(obj1.getEmail());
		System.out.println(pwd);
		System.out.println(obj1.getPhone());
		System.out.println(obj1.getUserStatus());
		
		ObjectMapper om1 = new ObjectMapper();
		
		String b1 = om1.writerWithDefaultPrettyPrinter().writeValueAsString(obj1);
		
		System.out.println("Body of the String: "+ "\n" + b1);
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		Response resobj = RestAssured.
		given().
		 contentType(ContentType.JSON).body(b1).
		when().
		 post("/user").
		then().
		 statusCode(200).
		 log().
		 body().extract().response();
		
		var1.setAttribute("UserName", uname);
		var2.setAttribute("Password", pwd);
		
		System.out.println("User Name : "+uname+" is created successfully ....");
	}
	
	@Test(dependsOnMethods = "createUser")
	
	public void modifyUser(ITestContext var1) throws JsonProcessingException {
		
		String UserName = var1.getAttribute("UserName").toString();
		System.out.println("User Name : "+"\n"+UserName);
		
		PojoClass1 obj1 = new PojoClass1();
		
		obj1.setId(0);
		obj1.setUsername("Gayathri");
		obj1.setFirstName("Gayathri");
		obj1.setLastName("R");
		obj1.setEmail("Gayathri@gmail.com");
		obj1.setPassword("Gayathri@123");
		obj1.setPhone("9087654321");
		obj1.setUserStatus(0);
				
		ObjectMapper om1 = new ObjectMapper();
		
		String b1 = om1.writerWithDefaultPrettyPrinter().writeValueAsString(obj1);
		
		System.out.println("Updated Body of the String: "+ "\n" + b1);
		
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		RestAssured.
		given().
		 contentType(ContentType.JSON).body(b1).
		when().
		 put("/user/"+UserName).
		then().
		 statusCode(200).
		 log().
		 body();
		
		System.out.println("User Name : "+UserName+" is Updated successfully !!!!!!!!!!");
	}
	
	@Test (dependsOnMethods = "createUser")
	
	public void userLogin(ITestContext var1 , ITestContext var2) {
		
		String UserName = var1.getAttribute("UserName").toString();
		String Password = var2.getAttribute("Password").toString();
		
		System.out.println("User Name : "+UserName + "\n" + "Password : "+Password);
		
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		RestAssured.
		given().
		 get("/user/login?username="+UserName+"&password="+Password).
		then().
		 statusCode(200).
		 log().
		 body();
		
		System.out.println("User Name : "+UserName+" is logged in successfully ....!!!!!");
	}
	
    @Test (dependsOnMethods = "userLogin")
	
	public void userLogout() {
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		RestAssured.
		given().
		 get("/user/logout").
		then().
		 statusCode(200).
		 log().
		 body();
		
		System.out.println("User successfully logged out !!!!!!");
	}
    
    @Test (dependsOnMethods = "userLogin")
    
    public void userDelete(ITestContext var1) {
		
    	String UserName = var1.getAttribute("UserName").toString();
    	
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		
		RestAssured.
		given().
		 delete("/user/"+UserName).
		then().
		 statusCode(200).
		 log().
		 body();
		
		System.out.println("User Name : "+UserName+" is deleted successfully.....");
	}
}
