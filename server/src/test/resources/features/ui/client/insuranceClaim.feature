@NewClaim
Feature: Create a New Claim

  Background:
    Given I am on the Create New Claim page

  Scenario: Submit a valid Pre-Authorization (Cashless) claim
    When I select "Pre-Authorization (Cashless)" as the coverage type
    And I enter procedure details with at least 10 characters
    And I upload a valid supporting document
    And I click the "Submit Claim" button
    Then I should see an alert with text "Claim created and document uploaded successfully!"
    And logout and close browser


  Scenario: Submit a valid Post-Authorization (Reimbursement) claim
    When I select "Post-Authorization (Reimbursement)" as the coverage type
    And I enter procedure details with at least 10 characters
    And I enter an amount greater than or equal to 500
    And I upload a valid supporting document
    And I click the "Submit Claim" button
    Then I should see an alert with text "Claim created and document uploaded successfully!"
    And logout and close browser


  Scenario: Disable submit button when procedure details are too short
    When I select "Pre-Authorization (Cashless)" as the coverage type
    And I enter procedure details with less than 10 characters
    Then the "Submit Claim" button should be disabled
    And logout and close browser

  Scenario: Disable submit button when reimbursement amount is below minimum
    When I select "Post-Authorization (Reimbursement)" as the coverage type
    And I enter procedure details with at least 10 characters
    And I enter an amount less than 500
    Then the "Submit Claim" button should be disabled
    And logout and close browser

  Scenario: Disable submit button when no document is uploaded
    When I select "Pre-Authorization (Cashless)" as the coverage type
    And I enter procedure details with at least 10 characters
    And I do not upload any document
    Then the "Submit Claim" button should be disabled
    And logout and close browser
