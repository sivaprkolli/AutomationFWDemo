package com.sip.Base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

import static com.sip.Base.WebDriverFactory.*;

public class SIPBase {

    @Parameters({"browserType","url"})
    @BeforeTest
    public void initialize(String browserType, String url) throws MalformedURLException {
        WebDriver driver = setBrowser(browserType);
        threadLocal.set(driver);
        getDriver().get(url);
    }

    @AfterTest
    public void killSession(){
        getDriver().quit();
        threadLocal.remove();
    }

}
