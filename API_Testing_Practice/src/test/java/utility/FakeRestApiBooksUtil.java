package utility;

import api.FakeRestApiBooks;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static libraries.TestBase.fakeApiRequestActivitiesUrl;

public class FakeRestApiBooksUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeRestApiBooksUtil.class);

    public static Response getFakeApiBooks(String id){
        String endpoint;
        if(!id.isEmpty()){
            endpoint = fakeApiRequestActivitiesUrl()+"/Books/"+id;
        }
        else {
            endpoint = fakeApiRequestActivitiesUrl() + "/Books";
        }

        Response response = RestAssured.given()
                .urlEncodingEnabled(false).log().all()
                .when().get(endpoint)
                .then().log().all().extract().response();

        LOGGER.info("FakeRestApiBooks Response HttpStatusCode [{}]", response.statusCode());

        return response;
    }

    public static Response PostFakeApiBooks(String uri, JsonObject body) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .body(body.toString())
                .post(fakeApiRequestActivitiesUrl() + uri)
                .then().log().all().extract().response();

        LOGGER.info("PostFakeRestApiBooks Response HttpStatusCode [{}]", response.statusCode());

        return response;
    }
}
