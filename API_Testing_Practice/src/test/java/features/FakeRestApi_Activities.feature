Feature: FakeRest API Activities

  Scenario Outline: Retrieve all activities details (retrieve all the activity details)
    When I send a GET request to retrieve all activity details
    Then the GET activities response status code should be "<HttpStatusCode>"
    Then the response should contain valid activity details for "id"
    Then the response should contain valid activity details for "title"
    Then the response should contain valid activity details for "dueDate"
    Then the response should contain valid activity details for "completed"

    Examples:
      | HttpStatusCode |
      | 200            |

  Scenario Outline: Create the activities details
    When I create a Activity details with "<Id>", "<Title>", "<DueDate>", "<Completed>"
    Then the POST activity response status code should be "<HttpStatusCode>"
    Then The response should contain the requested "id" for "<Id>"
    Then The response should contain the requested "title" for "<Title>"
    Then The response should contain the requested "dueDate" for "<DueDate>"
    Then The response should contain the requested "completed" for "<Completed>"

    Examples:
      | HttpStatusCode | Id | Title       | DueDate                  | Completed |
      | 200            | 5  | HorryPotter | 2025-03-26T11:23:28.039Z | true      |

  Scenario Outline: Retrieve the specific activity id
    When I retrieve the user activities for "<id>"
    Then the activity response status code should be "<HttpStatusCode>"
    And the "id" field should be present and have the value "<id>"
    Then Verify the response should contain valid activity details for "title"
    Then Verify the response should contain valid activity details for "dueDate"
    Then Verify the response should contain valid activity details for "completed"

    Examples:
      | id | HttpStatusCode |
      | 6  | 200            |



