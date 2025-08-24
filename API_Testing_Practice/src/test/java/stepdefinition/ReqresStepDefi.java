package stepdefinition;

import api.Reqres;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReqresStepDefi {
    @When("I send a GET request for a user with {string}")
    public void iSendAGETRequestForAUserWith(String userId) {
        Reqres.reqresGetSingleUser(userId);
    }

    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String statuscode) {
        Reqres.verifyStatusCodeForSingleUser(statuscode);
    }

    @Then("the returned data should have an ID of {string}")
    public void heReturnedDataShouldHaveAnIDOf(String Id) {
        Reqres.VerifyUserResponseDataId(Id);
    }


    @Then("the user has an email ending with {string}")
    public void theUserHasAnEmailEndingWith(String email) {
        Reqres.verifyUserEmail(email);
    }

    @When("I Add a {string} and {string} in body section")
    public void iAddAAndInBodySection(String name, String job) {
        Reqres.addUsers(name, job);
    }

    @Then("the response status code should be {string} for adding a user")
    public void theResponseStatusCodeShouldBeForAddingAUser(String statuscode) {
        Reqres.verifyStatusCodeForCreateUser(statuscode);
    }


    @Then("the response should contain the user {string} and {string} that we have added")
    public void theResponseShouldContainTheUserAndThatWeHaveAdded(String username, String userjob) {
        Reqres.verifyUserNameAndJob(username, userjob);
    }

    @Then("the response contains valid user id")
    public void theResponseContainsValidUserId() {
        Reqres.verifyuserId();
    }
}
