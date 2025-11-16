Feature: As a QA engineer, I want to automate the PUT /profile/update/{userId} API so I can verify profile updates work correctly.
  Scenario Outline: I want to update my profile
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    And I get login req status code as 200
    And Store the jwt token and user id
    And I send request to update "<address>" and "<phoneno>"
    Then I send request to GET my profile
    And I check the profile is updated correctly with "<address>" and "<phoneno>"

    Examples:
    |username|password|address|phoneno|
    |client1example|123456|flat-44 gh DB| 1234567890|