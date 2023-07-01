import Model.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Test6 {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                setContentType(ContentType.JSON).
                build();
    }
    @BeforeClass
    public static void createResponseSpecification(){
        responseSpec = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectStatusCode(200).
                build();
    }
    @Test
    public void requestUSZipcode90210_checkPlaceNameInResponseBody_expectBeverlyHills(){
        Location location =
                given().
                        spec(requestSpec).
                        when().
                        get("us/90210").
                        as(Location.class);

        Assert.assertEquals("Beverly Hills", location.getPlaces().get(0).getPlaceName());
    }
    @Test
    public void sendLvZipCode1050_checkStatusCode_expect200(){
        Location location = new Location();
        //location.setCountry("Netherlands");
        given().
                spec(requestSpec).
                body(location).log().body().
                when().
                post("/lv/1050").
                then().
                assertThat().statusCode(200);
    }
}
