package stepdefinition;

import api.FakeRestApiBooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FakeRestApiBooks_Stepdef {
    @When("I send a request to retrieve all book details")
    public void iSendARequestToRetrieveAllBookDetails() {
        FakeRestApiBooks.GetRetrieveAllBookDetails();
    }

    @Then("the book response status code should be {string}")
    public void theBookResponseStatusCodeShouldBe(String statuscode) {
        FakeRestApiBooks.getRetrieveBookDetailsStatusCode(statuscode);
    }

    @Then("the book response should contain valid activity details for {string}")
    public void theBookResponseShouldContainValidActivityDetailsFor(String attributeName) {
        FakeRestApiBooks.verifyGetRetrieveBookDetails(attributeName);
    }

    @When("I send a request to create book details with {string}, {string}, {string}, {string}, {string}, {string}")
    public void iSendARequestToCreateBookDetailsWith(String id, String title, String description, String pageCount, String excerpt, String publishDate) {
        FakeRestApiBooks.CreateFakeApiBookDetails(id, title, description, pageCount, excerpt, publishDate);
    }

    @Then("Verify the response status code should be {string}")
    public void verifyTheResponseStatusCodeShouldBe(String statuscode) {
        FakeRestApiBooks.verifyStatusCodeForCreateBookDetails(statuscode);
    }

    @Then("Verify the response should contain the requested {string} for {string}")
    public void verifyTheResponseShouldContainTheRequestedFor(String responseAttributeField, String attributeField) {
        FakeRestApiBooks.verifyCreateBookDetailsResponse(responseAttributeField, attributeField);
    }
}
