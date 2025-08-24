package api;

import com.jayway.jsonpath.JsonPath;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import utility.ReqresUtil;

import java.util.Map;

public class Reqres {
    private static final Logger LOGGER = LoggerFactory.getLogger(Reqres.class);
    private static Response reqresGetUserRespone;
    private static String reqresGetUserResponeStr;
    private static Response createUserResponse;
    private static String createUserResponseStr;

    public static void reqresGetSingleUser (String userId) {
        reqresGetUserRespone = ReqresUtil.getReqresSingleUser(userId);
        reqresGetUserResponeStr = reqresGetUserRespone.asString();
    }

    public static void verifyStatusCodeForSingleUser (String statuscode){
        ReqresUtil.verifyReqresforSingleUSerResponseCode(reqresGetUserRespone, statuscode);
    }

    public static void VerifyUserResponseDataId(String Id){
        int actualDataId = JsonPath.read(reqresGetUserResponeStr, "$.data.id");
        int expectedDataId = Integer.parseInt(Id);
        LOGGER.info("ActualUserDataId is {}", actualDataId);
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(actualDataId, expectedDataId, "The User Data Id Not matched");
    }

    public static void verifyUserEmail(String useremail){
        String userEmail = JsonPath.read(reqresGetUserResponeStr, "$.data.email");
        SoftAssert sa = new SoftAssert();

        if (userEmail != null && !userEmail.isEmpty()) {
            sa.assertTrue(userEmail.endsWith(useremail), "The User Email is not valid. Please enter a valid email.");
        }

        sa.assertAll();
    }

    public static void addUsers(String name, String job){
        String uri = "/api/users";
        JsonObject ReqresrequestBody = getReqresJsonObject(name, job);
        createUserResponse = ReqresUtil.invokePostApi(uri, ReqresrequestBody);
        createUserResponseStr = createUserResponse.asString();
    }

    private static JsonObject getReqresJsonObject(String name, String job) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", name);
        requestBody.addProperty("job", job);
        return requestBody;
    }

    public static void verifyStatusCodeForCreateUser(String statuscode){
        ReqresUtil.verifyReqresforSingleUSerResponseCode(createUserResponse, statuscode);
    }

    public static void verifyUserNameAndJob(String name, String job){
        String username = JsonPath.read(createUserResponseStr,"$.name");
        String userjob = JsonPath.read(createUserResponseStr,"$.job");
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(username, "The username field is null");
        sa.assertNotNull(userjob, "The userjob field is null");
        sa.assertEquals(username, name, "The username field is not matching...");
        sa.assertEquals(userjob, job, "The userjob field is not matching...");
    }

    public static void verifyuserId(){
        int userId = Integer.parseInt(JsonPath.read(createUserResponseStr, "$.id").toString());
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(userId, "The user id is null... ");
        sa.assertTrue(userId > 0, "The user id value is 0 or less than 0...");
        sa.assertAll();

    }
}
