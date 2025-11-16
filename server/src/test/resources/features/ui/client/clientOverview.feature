@Overview
Feature: Find client details

  Scenario Outline: Display client overview information
    Given I am on the Client Overview Page with username "<LoginUsername>" and password "<LoginPassword>"
    When I search for client "<Username>"
    Then I should see the username "<Username>"
    And I should see the remaining coverage "<RemainingCoverage>"
    And I should see the policy tier "<PolicyTier>"
    And close the browser after verification

    Examples:
      | LoginUsername    | LoginPassword | Username        | RemainingCoverage | PolicyTier |
      | client1example   | 123456        | @client1example | ₹250,000          | Silver     |
      | client2example   | 123456        | @client2example | ₹82,600           | Bronze     |
      | client3example   | 123456        | @client3example | ₹1,000,000        | Platinum   |
