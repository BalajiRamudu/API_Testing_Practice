package utility;

import libraries.TestBase;
import api.FakeRestAPIActivities;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import static libraries.TestBase.fakeApiRequestActivitiesUrl;
import static libraries.TestBase.reqresBaseUrl;

public class FakeRestApiActivitiesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FakeRestApiActivitiesUtil.class);

    public static Response getFakeApiActivities(String id){
        String endpoint;
        if(!id.isEmpty()){
            endpoint = fakeApiRequestActivitiesUrl()+"/Activities/"+id;
        }
        else {
            endpoint = fakeApiRequestActivitiesUrl() + "/Activities";
        }

        Response response = RestAssured.given()
                .urlEncodingEnabled(false).log().all()
                .when().get(endpoint)
                .then().log().all().extract().response();

        LOGGER.info("FakeRestApiActivities Response HttpStatusCode [{}]", response.statusCode());

        return response;
    }

    public static Response PostFakeApiActivities(String uri, JsonObject body) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .body(body.toString())
                .post(fakeApiRequestActivitiesUrl() + uri)
                .then().log().all().extract().response();

        LOGGER.info("PostFakeRestApiActivities Response HttpStatusCode [{}]", response.statusCode());

        return response;
    }

    public static void verifyFakeApiActivityResponseCode(Response FakeApiActivityRespone, String statusCode) {

        int expectedStatusCode = Integer.parseInt(statusCode);
        int actualStatusCode = FakeApiActivityRespone.getStatusCode();

        LOGGER.info("actual status code: {}, expected status code: {}", actualStatusCode, expectedStatusCode);

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(actualStatusCode, expectedStatusCode, "Response Body: " + FakeApiActivityRespone.asString() + ", HttpStatusCode not matched: ");
        sa.assertAll();
    }

    public static String getActivityIdBasedOnEnv() {

        String env = TestBase.ENV;

        if (env.equalsIgnoreCase("qa")) {
            LOGGER.info("Using QA test data → Activity ID = 1");
            return "1";
        } else if (env.equalsIgnoreCase("uat")) {
            LOGGER.info("Using UAT test data → Activity ID = 5");
            return "5";
        } else {
            LOGGER.info("Using DEFAULT test data → Activity ID = 1");
            return "1";
        }
    }

}
