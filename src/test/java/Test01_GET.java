import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

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

        //Store the extracted values in variables
        String stateAbbreviationVariable = stateAbbreviation;
        String  placeNameVariable = placeName;

        //Use the stored values as path parameters in the second API Call
        Response response2 = RestAssured.get("http://api.zippopotam.us/us/" + stateAbbreviationVariable + "/" + placeNameVariable);

        //Print the response from second API call
        System.out.println("Second API Response");
        System.out.println(response2.asString());

    }

    @Test
    void requestToVerifyTheList(){
        //Make the api request
        //RestAssured.baseURI = "http://api.zippopotam.us/us/12345";
        Response response = RestAssured.get("http://api.zippopotam.us/us/12345");

        //Extract the state abbreviation and place name from the response

        String stateAbbreviation = response.path("places[0].'state abbreviation'");
        String placeName = response.path("places[0].'place name'");

        //Print the extracted values
        System.out.println("State Abbreviation:" + stateAbbreviation);
        System.out.println("Place Name :" + placeName);

        //Store the extracted values in variables
        String stateAbbreviationVariable = stateAbbreviation;
        String  placeNameVariable = placeName;

        //Use the stored values as path parameters in the second API Call
        Response response2 = RestAssured.get("http://api.zippopotam.us/us/" + stateAbbreviationVariable + "/" + placeNameVariable);

        //Get the list of places from the second api response
        List<Object> places = response2.path("places");

        //Verify the number of entries in the places list
        int numberOfPlaces = places.size();
        System.out.println("Number of places : " + numberOfPlaces);

        //Check if the number of places is 11
        if(numberOfPlaces == 11){
            System.out.println("The list of places has 11 entries");
        } else {
            System.out.println("The list of places does not have " + numberOfPlaces + " entries");
        }

    }




}


