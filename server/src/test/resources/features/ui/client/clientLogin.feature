Feature: Client: Automate the Login and successful session validation.

  Scenario Outline: Client login process

    Given Open browser
    Then Navigate to insuranceAssist URL
    And Select role "<role>"
    Then Input username "<username>"
    And Input password "<password>"
    Then Click on login button
    And Verify if client landed on dashboard page with username "<username>"
    Then Logout client
    And Close browser

    Examples:
    | role | username | password |
    | Client | client1example | 123456 |
    | Client | client2example | 123456 |
