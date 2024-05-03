package com.sip.Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class SIPBase {
    public WebDriver driver;
    WebDriverFactory webDriverFactory;

    @Parameters("browserType")
    @BeforeSuite
    public void initialize(String browserType){
        webDriverFactory = new WebDriverFactory(driver);
        driver = webDriverFactory.intializeBrowser(browserType);
    }

    @Parameters("url")
    @BeforeTest
    public void launchApplication(String url){
        driver.get(url);
    }

    @AfterSuite
    public void killSession(){
        driver.quit();
    }

}
