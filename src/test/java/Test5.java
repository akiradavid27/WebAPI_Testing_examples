import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test5 {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").build();
    }
    @BeforeClass
    public static void createResponseSpecification(){
        responseSpec = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectStatusCode(200).
                build();
    }
    @Test
    public void requestBookingId1_checkPersonNameInResponseBody_expectSusan(){
        given().
                spec(requestSpec).
                when().
                get("booking/1").
                then().
                spec(responseSpec).and().
                assertThat().body("firstname", equalTo("Susan"));
    }
    @Test
    public void requestAllBookings_getAllBookingsInResponseBody(){
        Gson gson = new Gson();
        String jsonResponse =
        given().
                spec(requestSpec).
                when().
                get("booking/").
                then().
                spec(responseSpec).and().extract().toString();
        System.out.println(jsonResponse);
    }
}
