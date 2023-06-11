import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Test01_GET {

    @Test
    void getRequestToTestResponseCode() {

        Response response = RestAssured.get("http://api.zippopotam.us/us/12345");
        System.out.println("status code :"+ response.getStatusCode());

    }

    @Test
    void getRequestToTestResponseCode_differentMethod(){
        //BDD tests
        given().
                get("http://api.zippopotam.us/us/12345").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    void requestToVerifyState(){
        //adding a comment and another comment
        given().
                get("http://api.zippopotam.us/us/12345").
                then().
                assertThat().
                body("places[0].state", equalTo("New York"));

    }

    @Test
    void requestToExtractResponsePlace(){
        //Make the api request
        //RestAssured.baseURI = "http://api.zippopotam.us/us/12345";
        Response response = RestAssured.get("http://api.zippopotam.us/us/12345");

        //Extract the state abbreviation and place name from the response

        String stateAbbreviation = response.path("places[0].'state abbreviation'");
        String placeName = response.path("places[0].'place name'");

        //Print the extracted values
        System.out.println("State Abbreviation:" + stateAbbreviation);
        System.out.println("Place Name :" + placeName);

    }


}


