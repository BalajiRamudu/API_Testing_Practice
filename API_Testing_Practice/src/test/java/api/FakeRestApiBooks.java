package api;

import com.jayway.jsonpath.JsonPath;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;
import utility.FakeRestApiActivitiesUtil;
import utility.FakeRestApiBooksUtil;

import java.util.List;
import java.util.Map;

public class FakeRestApiBooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeRestApiBooks.class);

    private static Response GetRetrieveAllBooksResponse;
    private static String GetRetrieveAllBooksResponseStr;

    private static Response CreateFakeApiBookDetailsResponse;
    private static String CreateFakeApiBookDetailsResponseStr;

    public static void GetRetrieveAllBookDetails() {
        GetRetrieveAllBooksResponse = FakeRestApiBooksUtil.getFakeApiBooks(" ");
        GetRetrieveAllBooksResponseStr = GetRetrieveAllBooksResponse.asString();
    }

    public static void getRetrieveBookDetailsStatusCode(String statuscode) {
        FakeRestApiActivitiesUtil.verifyFakeApiActivityResponseCode(GetRetrieveAllBooksResponse, statuscode);
    }

    public static void verifyGetRetrieveBookDetails(String attributeName) {
        List<Map<String, ?>> attributeValue = JsonPath.read(GetRetrieveAllBooksResponseStr, "$");
        SoftAssert sa = new SoftAssert();
        for (Map<String, ?> getAttribute : attributeValue) {
            Object value;
            value = getAttribute.get(attributeName);
            if (value != null) {
                if (attributeName.equals("id") || attributeName.equals("pageCount")) {
                    sa.assertTrue(!value.toString().isEmpty(), "The " + attributeName + "is Empty");
                    sa.assertTrue(((Number) value).intValue() >= 0, "The " + attributeName + "should not be negative value");
                } else {
                    sa.assertTrue(!value.toString().isEmpty(), "The " + attributeName + "is Empty");
                }
            }
        }
        sa.assertAll();
    }

    public static void CreateFakeApiBookDetails(String id, String title, String description, String pageCount, String excerpt, String publishDate) {
        String uri = "/Books";
        JsonObject createBookDetails = createFakeApiBooksJsonObject(id, title, description, pageCount, excerpt, publishDate);
        CreateFakeApiBookDetailsResponse = FakeRestApiBooksUtil.PostFakeApiBooks(uri, createBookDetails);
        CreateFakeApiBookDetailsResponseStr = CreateFakeApiBookDetailsResponse.asString();
    }

    public static JsonObject createFakeApiBooksJsonObject(String id, String title, String description, String pageCount, String excerpt, String publishDate) {
        JsonObject createBookDetails = new JsonObject();
        int bookId = Integer.parseInt(id);
        int bookPageCount = Integer.parseInt(pageCount);
        createBookDetails.addProperty("id", bookId);
        createBookDetails.addProperty("title", title);
        createBookDetails.addProperty("description", description);
        createBookDetails.addProperty("pageCount", bookPageCount);
        createBookDetails.addProperty("excerpt", excerpt);
        createBookDetails.addProperty("publishDate", publishDate);

        return createBookDetails;
    }

    public static void verifyStatusCodeForCreateBookDetails(String statuscode) {
        FakeRestApiActivitiesUtil.verifyFakeApiActivityResponseCode(CreateFakeApiBookDetailsResponse, statuscode);
    }

    public static void verifyCreateBookDetailsResponse(String responseAttributeField, String attributeField) {
        Object attribute = JsonPath.read(CreateFakeApiBookDetailsResponseStr, "$." + responseAttributeField);
        SoftAssert sa = new SoftAssert();
        sa.assertNotNull(attribute, "The " + responseAttributeField + "is Null");
        sa.assertFalse(attribute.toString().isEmpty(), "The " + responseAttributeField + "is Empty");
        if(responseAttributeField.equals("id") || responseAttributeField.equals("pageCount")){
            int expectedValue = Integer.parseInt(attributeField);
            sa.assertEquals(((Number) attribute).intValue(), expectedValue, "The " + responseAttributeField + "is not matching..." );
        }else{
            sa.assertEquals(attribute.toString(), attributeField, "The " +  responseAttributeField + "is not matching...");
        }
        sa.assertAll();

    }
}
