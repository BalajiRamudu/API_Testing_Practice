package stepdefinition;

import api.FakeRestAPIActivities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FakeRestApiActivitiesStepdef {
    @When("I send a GET request to retrieve all activity details")
    public void iSendAGETRequestToRetrieveAllActivityDetails() {
        FakeRestAPIActivities.GetActivitiesDetails();
    }

    @Then("the GET activities response status code should be {string}")
    public void theGETActivitiesResponseStatusCodeShouldBe(String statuscode) {
        FakeRestAPIActivities.FakeApiGetActivityDetailsStatusCode(statuscode);
    }

    @Then("the response should contain valid activity details for {string}")
    public void theResponseShouldContainValidActivityDetailsFor(String attribute) {
        FakeRestAPIActivities.VerifyFakeApiRequestActivityDetails(attribute);
    }


    @When("I create a Activity details with {string}, {string}, {string}, {string}")
    public void iCreateAActivityDetailsWith(String id, String title, String dueDate, String completed) {
        FakeRestAPIActivities.createFakeApiRequestActivity(id, title, dueDate, completed);
    }

    @Then("the POST activity response status code should be {string}")
    public void thePOSTActivityResponseStatusCodeShouldBe(String statuscode) {
        FakeRestAPIActivities.FakeApiCreateActivityDetailsStatusCode(statuscode);
    }


    @Then("The response should contain the requested {string} for {string}")
    public void theResponseShouldContainTheRequestedFor(String responseAttributeField, String attributeField) {
        FakeRestAPIActivities.VerifyFakeApiCreateActiviyDetails(responseAttributeField, attributeField);
    }

    @When("I retrieve the user activities for {string}")
    public void iRetrieveTheUserActivitiesFor(String id) {
        FakeRestAPIActivities.getRetrieveFakeAPIActivityForId(id);
    }

    @Then("the activity response status code should be {string}")
    public void theActivityResponseStatusCodeShouldBe(String statuscode) {
        FakeRestAPIActivities.getRetrieveFakeAPIActivityStatusCode(statuscode);
    }

    @And("the {string} field should be present and have the value {string}")
    public void theFieldShouldBePresentAndHaveTheValue(String id, String ResponseId) {
        FakeRestAPIActivities.getVerifyRetrieveActivityId(id, ResponseId);
    }


    @Then("Verify the response should contain valid activity details for {string}")
    public void verifyTheResponseShouldContainValidActivityDetailsFor(String attributeName) {
        FakeRestAPIActivities.setGetRetrieveActivitiesDetails(attributeName);
    }
}
