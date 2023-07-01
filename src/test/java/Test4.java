import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class Test4 {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").build();
    }
    @BeforeClass
    public static void createResponseSpecification(){
        responseSpec = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectStatusCode(200).
                build();
    }

    @Test
    public void requestUSZipcode90210_checkListOfPlaceNamesInResponseBody_expectBeverlyHills(){
        given().
                spec(requestSpec).
        when().
                get("us/90210").
        then().
                spec(responseSpec).
        and().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }
    @Test
    public void requestUSZipcode90210_checkPlaceNameInResponseBody_expectBeverlyHills(){
        String placeName =
        given().
                spec(requestSpec).
                when().
                get("us/90210").
                then().
                spec(responseSpec).
                and().
                extract().
                path("places[0].'place name'");

        Assert.assertEquals(placeName, "Beverly Hills");
    }
}
