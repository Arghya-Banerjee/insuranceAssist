package com.insuranceAssist.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class AgentClaimHistoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AgentClaimHistoryPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(heading));
        wait.until(ExpectedConditions.visibilityOfElementLocated(TBODY_BY_ID));
    }

    // --- Locators ---
    @FindBy(xpath = "//h3[normalize-space()='Claim History']")
    public WebElement heading;

    @FindBy(id = "created-filter-btn")   public WebElement createdFilterButton;
    @FindBy(id = "reviewed-filter-btn")  public WebElement reviewedFilterButton; // IN REVIEW
    @FindBy(id = "approved-filter-btn")  public WebElement approvedFilterButton;
    @FindBy(id = "rejected-filter-btn")  public WebElement rejectedFilterButton;

    @FindBy(id = "pre-filter-btn")       public WebElement preFilterButton;
    @FindBy(id = "post-filter-btn")      public WebElement postFilterButton;

    @FindBy(id = "start-date")           public WebElement startDateFilter;
    @FindBy(id = "end-date")             public WebElement endDateFilter;

    @FindBy(id = "search-filter")        public WebElement searchFilter;
    @FindBy(id = "clear-filter-btn")     public WebElement clearFilterButton;

    @FindBy(id = "claim-history-table-body")
    public WebElement claimHistoryTableBody;

    private static final By TBODY_BY_ID = By.id("claim-history-table-body");
    private static final By ROWS = By.cssSelector("#claim-history-table-body > tr");

    // --- Public API used in steps ---
    public void waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TBODY_BY_ID));
    }

    public List<WebElement> getTableRows() {
        waitUntilLoaded();
        return driver.findElements(ROWS);
    }

    public String getNoClaimsMessage() {
        waitUntilLoaded();
        return claimHistoryTableBody.getText().trim();
    }

    public void clickStatusFilter(String status) {
        WebElement btn = switch (status.toUpperCase()) {
            case "CREATED" -> createdFilterButton;
            case "IN REVIEW" -> reviewedFilterButton;
            case "APPROVED" -> approvedFilterButton;
            case "REJECTED" -> rejectedFilterButton;
            default -> throw new IllegalArgumentException("Unknown status: " + status);
        };
        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        waitUntilLoaded();
    }

    public void clickTypeFilter(String type) {
        WebElement btn = type.equalsIgnoreCase("PRE") ? preFilterButton : postFilterButton;
        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        waitUntilLoaded();
    }

    /** date must already be ISO yyyy-MM-dd */
    public void setStartDate(String iso) {
        // Convert yyyy-MM-dd → dd-MM-yyyy
        String[] p = iso.split("-");
        String mmDDyyyy = p[2] + "-" + p[1] + "-" + p[0];

        wait.until(ExpectedConditions.elementToBeClickable(startDateFilter));
        startDateFilter.clear();
        startDateFilter.sendKeys(mmDDyyyy);
        startDateFilter.sendKeys(Keys.TAB);
        waitUntilLoaded();
    }

    /** date must already be ISO yyyy-MM-dd */
    public void setEndDate(String iso) {
        // Convert yyyy-MM-dd → dd-MM-yyyy
        String[] p = iso.split("-");
        String mmDDyyyy = p[2] + "-" + p[1] + "-" + p[0];

        wait.until(ExpectedConditions.elementToBeClickable(endDateFilter));
        endDateFilter.clear();
        endDateFilter.sendKeys(mmDDyyyy);
        endDateFilter.sendKeys(Keys.TAB);
        waitUntilLoaded();
    }

    public void search(String keyword) {
        wait.until(ExpectedConditions.elementToBeClickable(searchFilter));
        searchFilter.clear();
        searchFilter.sendKeys(keyword);
        waitUntilLoaded();
    }

    public void clearFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(clearFilterButton)).click();
        waitUntilLoaded();
    }
}
