Feature: : As a QA engineer, I want to automate the POST /hospital/create API to ensure new hospital entries can be created.
  Scenario Outline: I want to add a new hospital
    Given I supply login data in json format
    When I give valid "<username>" and "<password>"
    And I get login req status code as 200
    And Store the jwt token and user id
    And I provide details "<filepath>" to add hospital
    Then I get the status code as 200


    Examples:
      |username| password|filepath|
      |agent1example| 123456|src/test/resources/testData/agent/postHospitalData.json|