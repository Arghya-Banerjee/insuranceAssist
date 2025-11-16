Feature: As a QA engineer, I want to automate the GET /hospital/get API to validate retrieval of all hospital records.
  Scenario Outline: I want to see what all hospital come under my insurance
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    And I get login req status code as 200
    And Store the jwt token and user id
    And i make a request to get all hospitals
    Then the get hospital status code is 200
    And i receive data containing name, address, email, rating

  Examples:
    |username|password|
    |client1example|123456|
