import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test2 {
    @Test
    public void requestUSZipcode90210_checkStatusCode_expect200(){
        given().
                when().get("http://zippopotam.us/us/90210").
                then().
                assertThat().statusCode(200);
    }

    @Test
    public void requestUSZipcode90210_checkContentType_expectApplicationJson(){
        given().
                when().get("http://zippopotam.us/us/90210").
                then().
                assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void requestUSZipcode90210_logRequestAndResponseDetails(){
        given().log().all().
                when().
                get("http://zippopotam.us/us/90210").
                then().log().body();
    }
    @Test
    public void requestUSZipcode90210_checkStateNameInResponseBody_expectCalifornia(){
        given().
                when().get("http://zippopotam.us/us/90210").
                then().
                assertThat().body("places[0].state", equalTo("California"));
    }
    @Test
    public void requestUSZipcode90210_checkListOfPlaceNamesInResponseBody_expectBeverlyHills(){
        given().
                when().get("http://zippopotam.us/us/90210").
                then().
                assertThat().body("places.'place name'", hasItem("Beverly Hills"));
    }
    @Test
    public void requestUSZipcode90210_checkNumberOfPlaceNamesInResponseBody_expectOne(){
        given().
                when().get("http://zippopotam.us/us/90210").
                then().
                assertThat().body("places.'place name'", hasSize(1));
    }
}
