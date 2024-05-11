package com.sip.WebTests;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

public class ParallelTest {

    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return threadLocal.get();
    }

    public static WebDriver setBrowser(String browserName) throws MalformedURLException {
        WebDriver driver = null;
        switch (browserName) {
            case "chrome":
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setBrowserName(browserName);
                desiredCapabilities.setPlatform(Platform.MAC);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.merge(desiredCapabilities);
                driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), chromeOptions);
                break;

            case "firefox":
                DesiredCapabilities firefoxCaps = new DesiredCapabilities();
                firefoxCaps.setBrowserName(browserName);
                firefoxCaps.setPlatform(Platform.MAC);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.merge(firefoxCaps);
                driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), firefoxOptions);
                break;

            case "safari":
                DesiredCapabilities safariCaps = new DesiredCapabilities();
//                safariCaps.setBrowserName(browserName);
//                safariCaps.setPlatform(Platform.MAC);
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.merge(safariCaps);
                driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), safariOptions);
                break;

            case "edge":
                DesiredCapabilities edgeCaps = new DesiredCapabilities();
                edgeCaps.setBrowserName("MicrosoftEdge");
                edgeCaps.setPlatform(Platform.MAC);
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.merge(edgeCaps);
                driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), edgeOptions);
                break;
        }
        return driver;
    }

    @Parameters("browserName")
    @BeforeTest
    public void launchApp(String browserName) throws MalformedURLException {
        WebDriver driver = setBrowser(browserName);
        threadLocal.set(driver);
        getDriver().get("https://www.saucedemo.com/");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println(Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void verifyLoginSuccessful() {

        getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        getDriver().findElement(By.name("password")).sendKeys("secret_sauce");
        getDriver().findElement(By.cssSelector("#login-button")).click();
        Assert.assertTrue(getDriver().findElement(By.cssSelector(".shopping_cart_link")).isDisplayed());
        getDriver().findElement(By.cssSelector("#react-burger-menu-btn")).click();
        getDriver().findElement(By.cssSelector("#logout_sidebar_link")).click();
    }

    @AfterTest
    public void killSession(){
        System.out.println(Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getName());
        getDriver().quit();
        threadLocal.remove();
    }
}
