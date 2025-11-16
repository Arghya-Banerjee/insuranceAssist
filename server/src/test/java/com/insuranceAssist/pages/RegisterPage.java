package com.insuranceAssist.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators

    @FindBy(id = "fullName")
    WebElement clientFullNameInput;

    @FindBy(id = "email")
    WebElement clientEmailInput;

    @FindBy(id = "confirmEmail")
    WebElement clientConfirmEmailInput;

    @FindBy(id = "dob")
    WebElement clientDobInput;

    @FindBy(id = "gender")
    WebElement clientGenderInput;

    @FindBy(id = "address")
    WebElement clientAddressInput;

    @FindBy(id = "phone")
    WebElement clientPhoneNumber;

    @FindBy(id = "password")
    WebElement clientPasswordInput;

    @FindBy(id = "confirmPassword")
    WebElement clientConfirmPasswordInput;

    @FindBy(xpath = "/html/body/app-root/app-auth/div/div/app-signup/form/button")
    WebElement clientSignupButton;


    // Dependent 1
    @FindBy(xpath = "//*[@id=\"dep1name\"]")
    WebElement dep1FullNameInput;

    @FindBy(xpath = "//*[@id=\"dep1dob\"]")
    WebElement dep1DobInput;

    @FindBy(xpath = "//*[@id=\"dep1contact\"]")
    WebElement dep1PhoneNumberInput;

    @FindBy(xpath = "//*[@id=\"dep1email\"]")
    WebElement dep1EmailInput;

    @FindBy(xpath = "//*[@id=\"dep1address\"]")
    WebElement dep1AddressInput;

    @FindBy(xpath = "//*[@id=\"dep1gender\"]")
    WebElement dep1GenderInput;

    @FindBy(xpath = "//*[@id=\"dep1relationTypeId\"]")
    WebElement dep1RelationTypeInput;


    // Dependent 2
    @FindBy(xpath = "//*[@id=\"dep2name\"]")
    WebElement dep2FullNameInput;

    @FindBy(xpath = "//*[@id=\"dep2dob\"]")
    WebElement dep2DobInput;

    @FindBy(xpath = "//*[@id=\"dep2contact\"]")
    WebElement dep2PhoneNumberInput;

    @FindBy(xpath = "//*[@id=\"dep2email\"]")
    WebElement dep2EmailInput;

    @FindBy(xpath = "//*[@id=\"dep2address\"]")
    WebElement dep2AddressInput;

    @FindBy(xpath = "//*[@id=\"dep2gender\"]")
    WebElement dep2GenderInput;

    @FindBy(xpath = "//*[@id=\"dep2relationTypeId\"]")
    WebElement dep2RelationTypeInput;


    // Dependent 3
    @FindBy(xpath = "//*[@id=\"dep3name\"]")
    WebElement dep3FullNameInput;

    @FindBy(xpath = "//*[@id=\"dep3dob\"]")
    WebElement dep3DobInput;

    @FindBy(xpath = "//*[@id=\"dep3contact\"]")
    WebElement dep3PhoneNumberInput;

    @FindBy(xpath = "//*[@id=\"dep3email\"]")
    WebElement dep3EmailInput;

    @FindBy(xpath = "//*[@id=\"dep3address\"]")
    WebElement dep3AddressInput;

    @FindBy(xpath = "//*[@id=\"dep3gender\"]")
    WebElement dep3GenderInput;

    @FindBy(xpath = "//*[@id=\"dep3relationTypeId\"]")
    WebElement dep3RelationTypeInput;


    // Dependent 4
    @FindBy(xpath = "//*[@id=\"dep4name\"]")
    WebElement dep4FullNameInput;

    @FindBy(xpath = "//*[@id=\"dep4dob\"]")
    WebElement dep4DobInput;

    @FindBy(xpath = "//*[@id=\"dep4contact\"]")
    WebElement dep4PhoneNumberInput;

    @FindBy(xpath = "//*[@id=\"dep4email\"]")
    WebElement dep4EmailInput;

    @FindBy(xpath = "//*[@id=\"dep4address\"]")
    WebElement dep4AddressInput;

    @FindBy(xpath = "//*[@id=\"dep4gender\"]")
    WebElement dep4GenderInput;

    @FindBy(xpath = "//*[@id=\"dep4relationTypeId\"]")
    WebElement dep4RelationTypeInput;

    @FindBy(xpath = "//*[@id=\"add-dependent-btn\"]")
    WebElement addDependentButton;

    @FindBy(xpath = "//*[@id=\"remove-dependent-btn\"]")
    WebElement removeDependentButton;

    @FindBy(xpath = "//*[@id=\"skip-dependent-btn\"]")
    WebElement skipDependentButton;

    @FindBy(xpath = "//*[@id=\"continue-btn\"]")
    WebElement dependentsContinueButton;


    // Policy page
    @FindBy(xpath = "//*[@id=\"plan\"]")
    WebElement policyInput;

    @FindBy(xpath = "//*[@id=\"policy-continue-btn\"]")
    WebElement policyContinueButton;

    // Actions

    private void safeSendKeys(WebElement element, String value) {
        if (value != null && !value.isBlank()) {
            element.clear();
            element.sendKeys(value);
        }
    }

    public void setClientFullname(String fullname){
        clientFullNameInput.sendKeys(fullname);
    }

    public void setClientEmail(String email){
        clientEmailInput.sendKeys(email);
    }

    public void setClientConfirmEmail(String confirmEmail){
        clientConfirmEmailInput.sendKeys(confirmEmail);
    }

    public void setClientDob(String dob){
        clientDobInput.sendKeys(dob);
    }

    public void setClientGender(String gender){
        Select s = new Select(clientGenderInput);
        s.selectByVisibleText(gender);
    }

    public void setClientAddress(String address){
        clientAddressInput.sendKeys(address);
    }

    public void setClientPhoneNumber(String phoneNumber){
        clientPhoneNumber.sendKeys(phoneNumber);
    }

    public void setClientPassword(String password){
        clientPasswordInput.sendKeys(password);
    }

    public void setClientConfirmPassword(String confirmPassword){
        clientConfirmPasswordInput.sendKeys(confirmPassword);
    }

    public void clickSignupButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOf(clientSignupButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            // fallback
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

    }

    public void clickSkipDependents() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOf(skipDependentButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public void clickAddDependent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOf(addDependentButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        try {
            button.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public void fillDependentDetails(int depIndex,
                                     String fullName,
                                     String dob,
                                     String phone,
                                     String email,
                                     String address,
                                     String gender,
                                     String relation) {
        WebElement nameInput, dobInput, phoneInput, emailInput, addressInput, genderInput, relationInput;

        switch (depIndex) {
            case 1:
                nameInput = dep1FullNameInput;
                dobInput = dep1DobInput;
                phoneInput = dep1PhoneNumberInput;
                emailInput = dep1EmailInput;
                addressInput = dep1AddressInput;
                genderInput = dep1GenderInput;
                relationInput = dep1RelationTypeInput;
                break;
            case 2:
                nameInput = dep2FullNameInput;
                dobInput = dep2DobInput;
                phoneInput = dep2PhoneNumberInput;
                emailInput = dep2EmailInput;
                addressInput = dep2AddressInput;
                genderInput = dep2GenderInput;
                relationInput = dep2RelationTypeInput;
                break;
            case 3:
                nameInput = dep3FullNameInput;
                dobInput = dep3DobInput;
                phoneInput = dep3PhoneNumberInput;
                emailInput = dep3EmailInput;
                addressInput = dep3AddressInput;
                genderInput = dep3GenderInput;
                relationInput = dep3RelationTypeInput;
                break;
            case 4:
                nameInput = dep4FullNameInput;
                dobInput = dep4DobInput;
                phoneInput = dep4PhoneNumberInput;
                emailInput = dep4EmailInput;
                addressInput = dep4AddressInput;
                genderInput = dep4GenderInput;
                relationInput = dep4RelationTypeInput;
                break;
            default:
                throw new IllegalArgumentException("Invalid dependent index: " + depIndex);
        }

        safeSendKeys(nameInput, fullName);
        System.out.println(dob);
        safeSendKeys(dobInput, dob);
        safeSendKeys(phoneInput, phone);
        safeSendKeys(emailInput, email);
        safeSendKeys(addressInput, address);

        if (gender != null && !gender.isBlank()) {
            new Select(genderInput).selectByVisibleText(gender);
        }
        if (relation != null && !relation.isBlank()) {
            new Select(relationInput).selectByVisibleText(relation);
        }
    }

    public void clickDependentsContinueButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOf(dependentsContinueButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public void selectPlan(String plan){
        new Select(policyInput).selectByVisibleText(plan);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOf(policyContinueButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", button);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        try{
            button.click();
        }
        catch (ElementClickInterceptedException e){
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public String getRegistrationText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

}
