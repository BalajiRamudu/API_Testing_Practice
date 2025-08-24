package utility;

import api.Reqres;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import libraries.TestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import com.google.api.client.http.HttpStatusCodes;

import static libraries.TestBase.reqresBaseUrl;

public class ReqresUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReqresUtil.class);
    private static final String REQRES_RESULT = "Reqres";

    public static Response getReqresSingleUser(String userId){
        String endpoint = reqresBaseUrl() +"/api/users/" + userId.trim();

        Response response = RestAssured.given()
                .urlEncodingEnabled(false).log().all()
                .when().get(endpoint)
                .then().log().all().extract().response();

//        Response response = RestAssured.given()
//                .header("Content-Type", "application/json").log().all()
//                .when().get(TestBase.getProperty("ReqresBaseUrl") + userId)
//                .then().log().all().extract().response();

        LOGGER.info("Reqres Response HttpStatusCode [{}]", response.statusCode());


        return response;
    }

    public static Response invokePostApi(String uri, JsonObject body) {
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all()
                .when()
                .body(body.toString())
                .post(reqresBaseUrl() + uri)
                .then().log().all().extract().response();

        return response;
    }

    public static void verifyReqresforSingleUSerResponseCode(Response reqresGetUserRespone, String statusCode) {

        int expectedStatusCode = Integer.parseInt(statusCode);
        int actualStatusCode = reqresGetUserRespone.getStatusCode();

        LOGGER.info("actual status code: {}, expected status code: {}", actualStatusCode, expectedStatusCode);

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(actualStatusCode, expectedStatusCode, "Response Body: " + reqresGetUserRespone.asString() + ", HttpStatusCode not matched: ");
        sa.assertAll();
    }
}
