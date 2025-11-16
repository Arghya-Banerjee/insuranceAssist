package com.insuranceAssist.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverSetup {

    public static WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();

        // General switches to suppress Chrome UI popups
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-password-generation");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars"); // hides "Chrome is being controlled…" and some warnings
        options.addArguments("--start-maximized");

        // Preferences to disable password manager and weak password warnings
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        // Extra prefs to suppress password protection / weak password prompts
        prefs.put("profile.password_protection_enabled", false);
        prefs.put("profile.password_protection_warning_trigger", 0);
        prefs.put("profile.password_entry_uses_account_store", false);

        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

}
