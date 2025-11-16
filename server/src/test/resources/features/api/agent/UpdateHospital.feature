Feature: As a QA engineer, I want to automate the PUT /hospital/update/{hospitalId} API to verify hospital record updates.

  @api-testing
  Scenario: Update hospital details for a specific hospital by hospitalId
    Given I supply login data in json format
    When I give valid "agent1example" and "123456"
    Then I get login req status code as 200
    And Store the jwt token and user id
    Then I call update hospital by hospitalId API and verify the response with data from "src/test/resources/testData/agent/putHospitalData.json"





