package com.insuranceAssist.stepDefinitions.ui.agent;

import com.insuranceAssist.pages.AgentClaimHistoryPage;
import com.insuranceAssist.pages.AgentDashboardPage;
import com.insuranceAssist.pages.LoginPage;
import com.insuranceAssist.utils.DriverSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class AgentClaimHistory {

    WebDriver driver;
    LoginPage loginPage;
    AgentDashboardPage agentDashboardPage;
    AgentClaimHistoryPage agentClaimHistoryPage;

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE;

    @Given("I am logged in as an agent with claims of all types and statuses")
    public void loginAsAgent() {
        driver = DriverSetup.getDriver();
        loginPage = new LoginPage(driver);
        driver.get("http://localhost:4200");
        loginPage.selectRole("Agent");
        loginPage.inputUsername("agent1example");
        loginPage.inputPassword("123456");
        agentDashboardPage = loginPage.agentClickLoginButton();
        agentClaimHistoryPage = agentDashboardPage.clickClaimHistoryButton();
    }

    @Given("I am on the Claim History page")
    public void openClaimHistoryPage() {
        agentClaimHistoryPage.waitUntilLoaded();
        Assert.assertTrue(agentClaimHistoryPage.claimHistoryTableBody.isDisplayed(), "Claim History table body not visible");
    }

    @Given("I have applied status, type, date, and search filters")
    public void applyAllFilters() {
        agentClaimHistoryPage.clickStatusFilter("Created");
        agentClaimHistoryPage.clickTypeFilter("PRE");
        String fromIso = LocalDate.now().minusDays(5).toString();
        String toIso   = LocalDate.now().minusDays(2).toString();
        agentClaimHistoryPage.setStartDate(fromIso);
        agentClaimHistoryPage.setEndDate(toIso);
        agentClaimHistoryPage.search("Test Claim");
    }

    @When("I click on the {string} status filter")
    public void clickStatusFilter(String status) {
        agentClaimHistoryPage.clickStatusFilter(status);
    }

    @When("I click on the {string} type filter")
    public void clickTypeFilter(String type) {
        agentClaimHistoryPage.clickTypeFilter(type);
    }

    @Then("claims {string} should be displayed")
    public void verifySpecificClaims(String commaSeparatedNotes) {
        List<String> expectedNotes = Arrays.stream(commaSeparatedNotes.split(","))
                .map(String::trim)
                .toList();

        List<WebElement> rows = agentClaimHistoryPage.getTableRows();

        for (WebElement row : rows) {
            String notes = row.findElement(By.xpath("./td[2]")).getText().trim();
            Assert.assertTrue(expectedNotes.contains(notes),
                    "Unexpected claim in results: " + notes);
        }

        Assert.assertEquals(rows.size(), expectedNotes.size(),
                "Number of displayed claims does not match expected");
    }

    @Then("the {string} date field should be disabled")
    public void verifyDateDisabled(String field) {
        WebElement dateField = field.equalsIgnoreCase("To")
                ? agentClaimHistoryPage.endDateFilter
                : agentClaimHistoryPage.startDateFilter;

        Assert.assertFalse(dateField.isEnabled(), field + " date field should be disabled");
    }

    @Then("the {string} date field should be enabled")
    public void verifyDateEnabled(String field) {
        WebElement dateField = field.equalsIgnoreCase("To")
                ? agentClaimHistoryPage.endDateFilter
                : agentClaimHistoryPage.startDateFilter;

        Assert.assertTrue(dateField.isEnabled(), field + " date field should be enabled");
    }

    @When("I select a {string} date")
    public void selectDate(String field) {
        String iso = LocalDate.now().minusDays(2).toString();
        if (field.equalsIgnoreCase("From")) agentClaimHistoryPage.setStartDate(iso);
        else agentClaimHistoryPage.setEndDate(iso);
    }

    @When("I select {string} date as {string}")
    public void selectSpecificDate(String field, String isoDate) {
        if (field.equalsIgnoreCase("From")) agentClaimHistoryPage.setStartDate(isoDate);
        else agentClaimHistoryPage.setEndDate(isoDate);
    }

    @When("I try to select a \"To\" date beyond today")
    public void trySelectAToDateBeyondToday() {
        String futureIso = LocalDate.now().plusDays(5).toString();
        agentClaimHistoryPage.setEndDate(futureIso);
    }

    @When("I try to select {string} date beyond today")
    public void selectDateBeyondToday(String field) {
        String futureIso = LocalDate.now().plusDays(5).toString();
        if (field.equalsIgnoreCase("To")) agentClaimHistoryPage.setEndDate(futureIso);
        else agentClaimHistoryPage.setStartDate(futureIso);
    }

    @When("I try to select {string} date as {string}")
    public void trySelectSpecificDate(String field, String dateIso) {
        selectSpecificDate(field, dateIso);
    }

    @Then("the {string} date should not allow selection beyond today")
    public void verifyMaxDate(String field) {
        WebElement dateField = field.equalsIgnoreCase("To")
                ? agentClaimHistoryPage.endDateFilter
                : agentClaimHistoryPage.startDateFilter;

        String max = dateField.getAttribute("max");
        String todayIso = LocalDate.now().toString();
        Assert.assertEquals(max, todayIso, "Max attribute should equal today (ISO)");
    }

    @Then("the {string} date should not allow selection before {string}")
    public void verifyMinDate(String field, String minDateIso) {
        WebElement dateField = field.equalsIgnoreCase("To")
                ? agentClaimHistoryPage.endDateFilter
                : agentClaimHistoryPage.startDateFilter;

        String actualMin = dateField.getAttribute("min");
        Assert.assertEquals(actualMin, minDateIso, field + " date min should equal From date (ISO)");
    }

    @Then("only claims opened between {string} and {string} should be displayed")
    public void verifyClaimsWithinDateRange(String fromIso, String toIso) {

        List<WebElement> rows = agentClaimHistoryPage.getTableRows();

        LocalDate from = LocalDate.parse(fromIso, ISO);
        LocalDate to   = LocalDate.parse(toIso, ISO);

        for (WebElement row : rows) {

            String openDateText = row.findElement(By.xpath("./td[1]")).getText().trim();
            LocalDate claimDate = LocalDate.parse(openDateText,
                    DateTimeFormatter.ofPattern("dd MMMM yyyy"));

            Assert.assertTrue(
                    (!claimDate.isBefore(from)) && (!claimDate.isAfter(to)),
                    "Claim date " + claimDate + " not within range " + from + " - " + to
            );
        }
    }

    @When("I enter {string} in the search bar")
    public void enterSearchKeyword(String keyword) {
        agentClaimHistoryPage.search(keyword);
    }

    @Then("only claims with {string} in procedure notes should be displayed")
    public void verifySearchResults(String keyword) {
        for (WebElement row : agentClaimHistoryPage.getTableRows()) {
            String notes = row.findElement(By.xpath("./td[2]")).getText().toLowerCase();
            Assert.assertTrue(notes.contains(keyword.toLowerCase()),
                    "Procedure notes do not contain search keyword: " + notes);
        }
    }

    @When("I click on the clear filters button")
    public void clickClearFilters() {
        agentClaimHistoryPage.clearFilters();
    }

    @Then("all filters should be reset")
    public void verifyFiltersReset() {
        List<WebElement> dataRows = agentClaimHistoryPage.getTableRows();
        Assert.assertEquals(dataRows.size(), 8, "Unexpected row count after reset");
    }

    @Then("all claims should be displayed")
    public void verifyAllClaimsVisible() {
        Assert.assertFalse(agentClaimHistoryPage.getTableRows().isEmpty());
    }

    @When("I apply filters that do not match any claim")
    public void applyInvalidFilters() {
        agentClaimHistoryPage.search("nonexistentkeyword123");
    }

    @Then("the table should display no results")
    public void verifyNoResults() {
        Assert.assertTrue(agentClaimHistoryPage.getNoClaimsMessage().contains("No claims found"));
    }

    @Then("a message {string} should be shown")
    public void verifyNoClaimsMessage(String expectedMessage) {
        Assert.assertTrue(agentClaimHistoryPage.getNoClaimsMessage().contains(expectedMessage));
    }

    @Then("I logout and close the browser")
    public void logoutAndCloseBrowser() {
        agentDashboardPage.clickLogoutButton();
        driver.quit();
    }
}
