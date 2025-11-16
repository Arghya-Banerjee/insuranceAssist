Feature: retrieval of client-specific policies. As a QA engineer, I want to automate the GET /policy/get/policyType API to ensure policy types are listed correctly.
  Scenario Outline: check if the available policies are provided for the client to select from
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    And Store the jwt token and user id
    And I send request to GET policy Type
    Then I get login req status code as 200
    And I get policy type api request status code as 200
    And I get the available policy details

    Examples:
    |username|password|
    |client1example| 123456|
    |client2example| 123456|
    |client3example| 123456|
    |client4example| 123456|