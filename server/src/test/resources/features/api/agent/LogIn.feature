Feature: Login api endpoints
  @positive
  Scenario Outline: Login api testing with correct username and password
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    Then I get login req status code as 200
    And Store the jwt token and user id


    Examples:
      |username| password|
      |agent1example| 123456|
      |agent2example| 123456|
      |agent3example| 123456|
      |agent4example| 123456|

  @negative
  Scenario Outline: Login api testing with incorrect username or password
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    Then I do not get status code as 200

    Examples:
      |username| password|
      |agent6example| 123456|
      |agent7example| 123456|
      |agent8example| 123456|
      |agent9example| 123456|