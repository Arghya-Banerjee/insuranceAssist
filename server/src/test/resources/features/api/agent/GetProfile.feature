Feature: As a QA engineer, I want to automate the GET /profile/get/{userId} API to ensure user profile data is retrieved accurately.
  Scenario Outline: Successfully retrieve a valid user's profile data
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    And Store the jwt token and user id
    And I send request to GET my profile
    Then I get login req status code as 200
    And "<username>" matches the login username

    Examples:
      |username| password|
      |agent1example| 123456|
      |agent2example| 123456|
      |agent3example| 123456|
      |agent4example| 123456|
