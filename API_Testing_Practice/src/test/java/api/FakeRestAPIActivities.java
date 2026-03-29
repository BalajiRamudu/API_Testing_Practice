package api;

import com.jayway.jsonpath.JsonPath;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import utility.FakeRestApiActivitiesUtil;
import utility.ReqresUtil;

import java.util.List;
import java.util.Map;

public class FakeRestAPIActivities {
    private static final Logger LOGGER = LoggerFactory.getLogger(FakeRestAPIActivities.class);
    private static Response GetActivitiesDetailsResponse;
    private static String GetActivitiesDetailsResponseStr;

    private static Response PostActivityDetailsResponse;
    private static String PostActivityDetailsResponseStr;

    private static Response GetRetrieveActivitiesDetailsResponse;
    private static String GetRetrieveActivitiesDetailsResponseStr;

    public static void GetActivitiesDetails(){
        GetActivitiesDetailsResponse = FakeRestApiActivitiesUtil.getFakeApiActivities(" ");
        GetActivitiesDetailsResponseStr = GetActivitiesDetailsResponse.asString();
    }

    public static void FakeApiGetActivityDetailsStatusCode(String statuscode){
        FakeRestApiActivitiesUtil.verifyFakeApiActivityResponseCode(GetActivitiesDetailsResponse,statuscode);
    }

    public static void VerifyFakeApiRequestActivityDetails (String attributeName){

        SoftAssert sa = new SoftAssert();
        List<Map<String,?>> getFakeApiDetailslist = JsonPath.read(GetActivitiesDetailsResponseStr,"$");
        for(Map<String,?> getFakeApiDetails : getFakeApiDetailslist) {
            Object attribute;
            attribute = getFakeApiDetails.get(attributeName);
            sa.assertNotNull(attribute, "The " + attributeName + "is Null");
            if (attribute instanceof String) {
                sa.assertFalse(((String) attribute).isEmpty(), "The " + attributeName + " is empty");
            }
            if (attribute instanceof Boolean) {
                sa.assertTrue(attribute.equals(Boolean.TRUE) || attribute.equals(Boolean.FALSE),
                        "The " + attributeName + " is not a valid boolean");
            }
        }
        sa.assertAll();
    }

    public static void createFakeApiRequestActivity (String id, String title, String dueDate, String completed){
        String uri = "/Activities";
        JsonObject createActivityBody = createFakeApiActivityJsonObject(id, title, dueDate, completed);
        PostActivityDetailsResponse = FakeRestApiActivitiesUtil.PostFakeApiActivities(uri,createActivityBody);
        PostActivityDetailsResponseStr = PostActivityDetailsResponse.asString();
    }

    public static JsonObject createFakeApiActivityJsonObject (String id, String title, String dueDate, String completed){
        JsonObject createFakeApiRequestBody = new JsonObject();
        int activityId = Integer.parseInt(id);
        boolean isCompleted = Boolean.parseBoolean(completed);
        createFakeApiRequestBody.addProperty("id", activityId);
        createFakeApiRequestBody.addProperty("title", title);
        createFakeApiRequestBody.addProperty("dueDate", dueDate);
        createFakeApiRequestBody.addProperty("completed", isCompleted);

        return createFakeApiRequestBody;

    }

    public static void FakeApiCreateActivityDetailsStatusCode(String statuscode){
        FakeRestApiActivitiesUtil.verifyFakeApiActivityResponseCode(PostActivityDetailsResponse,statuscode);
    }

    public static void VerifyFakeApiCreateActiviyDetails (String attributeResponseField, String attributeField) {
        System.out.println("The response is: " + PostActivityDetailsResponseStr);
        Object apiAttributeValue = JsonPath.read(PostActivityDetailsResponseStr, "$." + attributeResponseField);
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(apiAttributeValue, "The " + attributeResponseField + " is Null");
        if (attributeResponseField.equals("id")) {
            int expectedId = Integer.parseInt(attributeField);
            sa.assertTrue(apiAttributeValue instanceof Number, "The id should be an integer but found: " + apiAttributeValue);
            sa.assertEquals(((Number) apiAttributeValue).intValue(), expectedId, "The id value is different");
        } else if (attributeResponseField.equals("completed")) {
            boolean expectedCompleted = Boolean.parseBoolean(attributeField);
            sa.assertTrue(apiAttributeValue instanceof Boolean, "The completed value should be boolean but found: " + apiAttributeValue);
            sa.assertEquals(apiAttributeValue, expectedCompleted, "The completed value is different");
        } else {
            sa.assertEquals(apiAttributeValue.toString(), attributeField, "The " + apiAttributeValue + " value is different");
        }
        sa.assertAll();
    }

    public static void getRetrieveFakeAPIActivityForId (String id){
        GetRetrieveActivitiesDetailsResponse = FakeRestApiActivitiesUtil.getFakeApiActivities(id);
        GetRetrieveActivitiesDetailsResponseStr = GetRetrieveActivitiesDetailsResponse.asString();
    }

    public static void getRetrieveFakeAPIActivityStatusCode (String statuscode){
        FakeRestApiActivitiesUtil.verifyFakeApiActivityResponseCode(GetRetrieveActivitiesDetailsResponse, statuscode);
    }

    public static void setGetRetrieveActivitiesDetails (String attributeName){
        Object attributeValue = JsonPath.read(GetRetrieveActivitiesDetailsResponseStr, "$." + attributeName );
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(attributeValue, "The " + attributeName + "is Null");
        if(attributeName.equals("completed")){
            sa.assertTrue(attributeValue.equals(Boolean.TRUE) || attributeValue.equals(Boolean.FALSE),
                    "The " + attributeName + " is not a valid boolean");
        }
        sa.assertFalse(attributeValue.toString().isEmpty(), "The " + attributeName + "is Empty");
        sa.assertAll();
    }

    public static void getVerifyRetrieveActivityId (String id, String ResponseId) {
        int activityId = JsonPath.read(GetRetrieveActivitiesDetailsResponseStr, "$." + id);
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(activityId, "The " + activityId + "is Null");
        sa.assertFalse(String.valueOf(activityId).isEmpty(), "The " + activityId + "is Empty");
        sa.assertEquals(activityId, Integer.parseInt(ResponseId), "The " + activityId + "is not matching...");
        sa.assertAll();
    }
}
