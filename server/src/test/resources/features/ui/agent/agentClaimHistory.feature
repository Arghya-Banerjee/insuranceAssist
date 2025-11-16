Feature: Agent: Automate the ability to Check claim history using various search terms and filters.

  Background:
    Given I am logged in as an agent with claims of all types and statuses
    And I am on the Claim History page

  # --- Claim Status Filters ---
  @agent-claim-history
  Scenario: Filter claims by status CREATED
    When I click on the "Created" status filter
    Then claims "Test Claim 1, Test Claim 5" should be displayed
    And I logout and close the browser

  @agent-claim-history
  Scenario: Filter claims by status IN REVIEW
    When I click on the "In Review" status filter
    Then claims "Test Claim 2, Test Claim 6" should be displayed
    And I logout and close the browser

  @agent-claim-history
  Scenario: Filter claims by status APPROVED
    When I click on the "Approved" status filter
    Then claims "Test Claim 3, Test Claim 7" should be displayed
    And I logout and close the browser

  @agent-claim-history
  Scenario: Filter claims by status REJECTED
    When I click on the "Rejected" status filter
    Then claims "Test Claim 4, Test Claim 8" should be displayed
    And I logout and close the browser

  # --- Claim Type Filters ---
  @agent-claim-history
  Scenario: Filter claims by type PRE
    When I click on the "PRE" type filter
    Then claims "Test Claim 1, Test Claim 2, Test Claim 3, Test Claim 4" should be displayed
    And I logout and close the browser

  @agent-claim-history
  Scenario: Filter claims by type POST
    When I click on the "POST" type filter
    Then claims "Test Claim 5, Test Claim 6, Test Claim 7, Test Claim 8" should be displayed
    And I logout and close the browser

  # --- Combined Filters ---
  @agent-claim-history
  Scenario: Filter claims by type PRE and status APPROVED
    When I click on the "PRE" type filter
    And I click on the "Approved" status filter
    Then claims "Test Claim 3" should be displayed
    And I logout and close the browser

  @agent-claim-history
  Scenario: Filter claims by type POST and status REJECTED
    When I click on the "POST" type filter
    And I click on the "Rejected" status filter
    Then claims "Test Claim 8" should be displayed
    And I logout and close the browser

  # --- Date Filters ---
  @agent-claim-history
  Scenario: End date disabled until start date is selected
    Then the "To" date field should be disabled
    When I select a "From" date
    Then the "To" date field should be enabled
    And I logout and close the browser

  @agent-claim-history
  Scenario: End date cannot go beyond today
    When I select a "From" date
    And I try to select a "To" date beyond today
    Then the "To" date should not allow selection beyond today
    And I logout and close the browser

  @agent-claim-history
  Scenario: End date cannot be before start date
    When I select "From" date as "2025-11-01"
    And I try to select "To" date as "2025-10-31"
    Then the "To" date should not allow selection before "2025-11-01"
    And I logout and close the browser

  @agent-claim-history
  Scenario: Filter claims within date range
    When I select "From" date as "2025-11-01"
    And I select "To" date as "2025-11-07"
    Then only claims opened between "2025-11-01" and "2025-11-07" should be displayed
    And I logout and close the browser

  # --- Search ---
  @agent-claim-history
  Scenario: Search claims by keyword
    When I enter "Test Claim 7" in the search bar
    Then claims "Test Claim 7" should be displayed
    And I logout and close the browser

  @agent-claim-history
  Scenario: Case-insensitive search
    When I enter "test claim 6" in the search bar
    Then claims "Test Claim 6" should be displayed
    And I logout and close the browser

  # --- Clear Filters ---
  @agent-claim-history
  Scenario: Reset all filters
    Given I have applied status, type, date, and search filters
    When I click on the clear filters button
    Then all filters should be reset
    And all claims should be displayed
    And I logout and close the browser

  # --- Empty Results ---
  @agent-claim-history
  Scenario: No claims match filters
    When I apply filters that do not match any claim
    Then the table should display no results
    And a message "No claims found" should be shown
    And I logout and close the browser
