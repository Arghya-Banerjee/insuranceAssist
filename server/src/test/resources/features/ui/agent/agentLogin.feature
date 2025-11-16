Feature: Agent: Automate the successful Login and secure access to the agent dashboard.

  @agent-login @positive
  Scenario: Positive Agent login process
    Given Navigate to insuranceAssist URL
    And Perform login operation and verify logged in user "src/test/resources/testData/agent/loginDataPositive.json"
    Then Close browser

  @agent-login @negative
  Scenario: Negative Agent login process
    Given Navigate to insuranceAssist URL
    And Perform login operation and verify logged in user "src/test/resources/testData/agent/loginDataNegative.json"
    Then Close browser
