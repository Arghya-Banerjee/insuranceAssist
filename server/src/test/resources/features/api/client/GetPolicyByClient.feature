Feature: As a QA engineer, I want to automate the GET /policy/get/{clientId} API to verify retrieval of client-specific policies

  @api-testing
  Scenario Outline: Get policy details for a specific client by clientId
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    Then I get login req status code as 200
    And Store the jwt token and user id
    Then I call get policy by clientId API and verify the response

    Examples:
      | username   | password   |
      | client1example  | 123456   |
      | client2example  | 123456   |




