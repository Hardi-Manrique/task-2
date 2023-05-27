package task2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class task2 {

    @BeforeMethod
    public void beforeMethod(Method method){
        System.out.println("------------------------------");
        System.out.println("Running: "+method.getName());
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("------------------------------");
    }

    @Test
    public void getRequestUS() throws Exception {
        Response response=	RestAssured.get("http://api.countrylayer.com/v2/alpha/US?access_key=53ad666052e0d086c01978126442dbc1");
        System.out.println(response.getStatusCode());
        ResponseBody responseBody=response.getBody();
        JsonPath responseJson = responseBody.jsonPath();
        String countryName = responseJson.get("name");
        String alpha2Code = responseJson.get("alpha2Code");
        String alpha3Code = responseJson.get("alpha3Code");
        System.out.println("Country Name: "+countryName);
        System.out.println("Country Alpha 2 Code: "+alpha2Code);
        System.out.println("Country Alpha 3 Code: "+alpha3Code);
        // ASSERTION
        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(countryName, "United States of America");
        Assert.assertEquals(alpha2Code, "US");
        Assert.assertEquals(alpha3Code, "USA");
        Thread.sleep(3000);
    }

    @Test
    public void getRequestDE() throws InterruptedException {
        Response response=	RestAssured.get("http://api.countrylayer.com/v2/alpha/DE?access_key=53ad666052e0d086c01978126442dbc1");
        System.out.println(response.getStatusCode());
        ResponseBody responseBody=response.getBody();
        JsonPath responseJson = responseBody.jsonPath();
        String countryName = responseJson.get("name");
        String alpha2Code = responseJson.get("alpha2Code");
        String alpha3Code = responseJson.get("alpha3Code");
        System.out.println("Country Name: "+countryName);
        System.out.println("Country Alpha 2 Code: "+alpha2Code);
        System.out.println("Country Alpha 3 Code: "+alpha3Code);
        // ASSERTION
        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(countryName, "Germany");
        Assert.assertEquals(alpha2Code, "DE");
        Assert.assertEquals(alpha3Code, "DEU");
        Thread.sleep(3000);
    }
    @Test
    public void getRequestGB() throws Exception
    {
        Response response=	RestAssured.get("http://api.countrylayer.com/v2/alpha/GB?access_key=53ad666052e0d086c01978126442dbc1");
        System.out.println(response.getStatusCode());
        ResponseBody responseBody=response.getBody();
        JsonPath responseJson = responseBody.jsonPath();
        String countryName = responseJson.get("name");
        String alpha2Code = responseJson.get("alpha2Code");
        String alpha3Code = responseJson.get("alpha3Code");
        System.out.println("Country Name: "+countryName);
        System.out.println("Country Alpha 2 Code: "+alpha2Code);
        System.out.println("Country Alpha 3 Code: "+alpha3Code);
        // ASSERTION
        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(countryName, "United Kingdom of Great Britain and Northern Ireland");
        Assert.assertEquals(alpha2Code, "GB");
        Assert.assertEquals(alpha3Code, "GBR");
        Thread.sleep(3000);
    }

    @Test
    public void getRequestInexistent() throws Exception
    {
        Response response=	RestAssured.get("http://api.countrylayer.com/v2/alpha/XX?access_key=53ad666052e0d086c01978126442dbc1");
        System.out.println(response.getStatusCode());
        ResponseBody responseBody=response.getBody();
        JsonPath responseJson = responseBody.jsonPath();
        System.out.println("Message: "+responseJson.get("message"));
        // ASSERTION
        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
        Thread.sleep(3000);
    }

    @Test
    public void postNewCountry() throws Exception
    {
        JSONObject newCountry = new JSONObject();
        newCountry.put("name","Test Country");
        newCountry.put("alpha2_code","TC");
        newCountry.put("alpha3_code","TCY");
        System.out.println(newCountry);

        RestAssured.baseURI = "http://api.countrylayer.com/v2/alpha/access_key=53ad666052e0d086c01978126442dbc1";
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(newCountry.toJSONString());

        Response response = request.post();
        System.out.println(response.getStatusCode());
        ResponseBody responseBody=response.getBody();
        JsonPath responseJson = responseBody.jsonPath();
        String countryName = responseJson.get("name");
        String alpha2Code = responseJson.get("alpha2Code");
        String alpha3Code = responseJson.get("alpha3Code");
        System.out.println("Country Name: "+countryName);
        System.out.println("Country Alpha 2 Code: "+alpha2Code);
        System.out.println("Country Alpha 3 Code: "+alpha3Code);
        // ASSERTION
        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        Assert.assertEquals(countryName, "Test Country");
        Assert.assertEquals(alpha2Code, "TC");
        Assert.assertEquals(alpha2Code, "TCY");
        Thread.sleep(3000);
    }
}
