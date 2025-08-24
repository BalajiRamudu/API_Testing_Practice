Feature: FakeRest API Books

  Scenario Outline: Retrieve the all books details
    When I send a request to retrieve all book details
    Then the book response status code should be "<HttpStatusCode>"
    Then the book response should contain valid activity details for "id"
    Then the book response should contain valid activity details for "title"
    Then the book response should contain valid activity details for "description"
    Then the book response should contain valid activity details for "pageCount"
    Then the book response should contain valid activity details for "excerpt"
    Then the book response should contain valid activity details for "publishDate"

    Examples:
      | HttpStatusCode |
      | 200            |

  Scenario Outline: Create the books details
    When I send a request to create book details with "<Id>", "<Title>", "<Description>", "<PageCount>", "<Excerpt>", "<PublishDate>"
    Then Verify the response status code should be "<HttpStatusCode>"
    Then Verify the response should contain the requested "id" for "<Id>"
    Then Verify the response should contain the requested "title" for "<Title>"
    Then Verify the response should contain the requested "description" for "<Description>"
    Then Verify the response should contain the requested "pageCount" for "<PageCount>"
    Then Verify the response should contain the requested "excerpt" for "<Excerpt>"
    Then Verify the response should contain the requested "publishDate" for "<PublishDate>"

    Examples:
      | HttpStatusCode | Id | Title               | Description                                                                        | PageCount | Excerpt                       | PublishDate              |
      | 200            | 7  | Pride and Prejudice | The novel is set in the Ramsays' summer home in the Hebrides, on the Isle of Skye. | 224       | The meaning of life and death | 2025-12-31T15:29:59.535Z |