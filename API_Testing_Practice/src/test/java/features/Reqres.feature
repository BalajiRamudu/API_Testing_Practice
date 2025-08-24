
Feature: Reqres API

  Scenario Outline: Get the Reqres Details for Single User
    When I send a GET request for a user with "<userId>"
    Then the response status code should be "<HttpStatusCode>"
    Then the returned data should have an ID of "<Id>"
    Then the user has an email ending with "@reqres.in"
    Examples:
      | HttpStatusCode | userId | Id |
      | 200            | 2      | 2  |

  Scenario Outline: Add the User Reqres Details
    When I Add a "<name>" and "<job>" in body section
    Then the response status code should be "<HttpStatusCode>" for adding a user
    Then the response should contain the user "<name>" and "<job>" that we have added
    Then the response contains valid user id
    Examples:
      | name     | job    | HttpStatusCode |
      | morpheus | leader | 201            |