package com.insuranceAssist.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientNewClaimPage {
    private By inputBy = By.id("claimType");
    private By procedureDetailsBy = By.id("procedureNotes");
    private By claimAmountBy = By.id("claimAmount");
    private By documentUploadBy = By.id("documentUpload");
    private By submitButtonBy = By.xpath("//button[contains(text(),'Submit Claim')]");
    private WebDriver driver;

    public ClientNewClaimPage(WebDriver driver){
        this.driver = driver;
    }

    public void setClaimType(String claimType){
        driver.findElement(inputBy).sendKeys(claimType);
    }

    public void setProcedureDetails(String procedureDetails){
        driver.findElement(procedureDetailsBy).sendKeys(procedureDetails);
    }

    public void setClaimAmount(String claimAmount){
        driver.findElement(claimAmountBy).sendKeys(claimAmount);
    }

    public void clickSubmit(){
        WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(
                ExpectedConditions.elementToBeClickable(driver.findElement(submitButtonBy))
        ).click();

    }
    public void uploadDocument(){
        driver.findElement(documentUploadBy)
                .sendKeys("C:\\Arghya\\SpringBoot\\insuranceAssist\\server\\src\\test\\resources\\testData\\client\\pdfFiles\\MedicalReport.pdf");
    }

    public String getAlert(String expectedText){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String actualText = alert.getText();
        alert.accept();
        return actualText;

    }


}

