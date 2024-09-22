package Business;

import Common.CommonVar;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class Setup {

    public static void setUp() {

    }

    public static void login() throws Exception{
        RestAssured.reset();
        RestAssured.baseURI = CommonVar.BASE_URI;

        Response re = given().auth().preemptive().basic("AUv8rrc_P-EbP2E0mpb49BV7rFt3Usr-vdUZO8VGOnjRehGHBXkSzchr37SYF2GNdQFYSp72jh5QUhzG"
                        , "EMnAWe06ioGtouJs7gLYT9chK9-2jJ--7MKRXpI8FesmY_2Kp-d_7aCqff7M9moEJBvuXoBO4clKtY0v")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .post("/v1/oauth2/token");
        JsonPath json = new JsonPath(re.asString());

        String accessToken = json.getString("access_token");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Prefer", "return=representation");
        headers.put("Authorization", "Bearer " + accessToken);
        RestAssured.requestSpecification = new RequestSpecBuilder().addHeaders(headers)
                .setConfig(RestAssured.config().encoderConfig(new EncoderConfig().defaultContentCharset("UTF-8")))
                .build();
    }
}
