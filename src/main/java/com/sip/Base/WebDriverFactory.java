package com.sip.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {
    private WebDriver driver;

    public WebDriverFactory(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver intializeBrowser(String browser){
        switch (browser){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                System.out.println("Provide right browser name");
        }
        driver.manage().window().maximize();
        return driver;
    }


}
