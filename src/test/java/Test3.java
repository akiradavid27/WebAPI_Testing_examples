import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@RunWith(DataProviderRunner.class)
public class Test3 {
    @DataProvider
    public static Object[][] zipCodesAndPlace(){
        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us","12345","Schenectady"},
                {"ca","B2R","Waverley"}
        };
    }

    @Test
    @UseDataProvider("zipCodesAndPlace")
    public void requestUSZipcode90210_checkListOfPlaceNamesInResponseBody_expectBeverlyHills(String countryCode, String zipCode, String expectedCityName){
        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().get("http://zippopotam.us/{countryCode}/{zipCode}").
                then().
                assertThat().body("places[0].'place name'", equalTo(expectedCityName));
    }
}
