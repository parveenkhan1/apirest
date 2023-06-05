import io.restassured.RestAssured;
import io.restassured.response.Response;
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
        given().
                get("http://api.zippopotam.us/us/12345").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    void requestToVerifyState(){
        given().
                get("http://api.zippopotam.us/us/12345").
                then().
                assertThat().
                body("places.state", equalTo("New York"));

    }
}


