Feature: Login api endpoints
  @positive
  Scenario Outline: Login api testing with correct username and password
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    Then I get login req status code as 200
    And Store the jwt token and user id


    Examples:
    |username| password|
    |client1example| 123456|
    |client2example| 123456|
    |client3example| 123456|
    |client4example| 123456|

  @negative
  Scenario Outline: Login api testing with incorrect username or password
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    Then I do not get status code as 200

    Examples:
    |username| password|
    |client6example| 123456|
    |client7example| 123456|
    |client8example| 123456|
    |client9example| 123456|