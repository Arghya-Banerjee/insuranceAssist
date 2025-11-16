package com.insuranceAssist.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClientOverviewPage {
    private By usernameBy = By.xpath("//p");
    private By remainingCoverageBy = By.xpath("//li/strong[text() ='Remaining Coverage:' ]/parent::li");
    private By policyTypeBy = By.xpath("//li/strong[text() ='Tier:' ]/parent::li");
    private WebDriver driver;

    public ClientOverviewPage(WebDriver driver){
        this.driver = driver;
    }


    public String getUsername(){
        return driver.findElement(usernameBy).getText();
    }

    public String getRemainingCoverage(){
        String coverageMoney =  driver.findElement(remainingCoverageBy).getText();
        String[] arr = coverageMoney.split(" ");
        return arr[2];
    }

    public String getPolicyType(){
        String policyType =  driver.findElement(policyTypeBy).getText();
        String[] arr = policyType.split(" ");
        return arr[1];
    }
}
