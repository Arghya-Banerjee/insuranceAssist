Feature: As a QA engineer, I want to automate the POST /public/register API to validate user registration functionality.

  @positive
  @api-testing
  Scenario: Register a client with valid data
    Then Call register api and verify response details with data "positive"

  @negative
  @api-testing
  Scenario: Register a client with invalid data
    Then Call register api and verify response details with data "negative"
