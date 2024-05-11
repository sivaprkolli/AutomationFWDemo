package com.sip.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
    private static DesiredCapabilities desiredCapabilities;
    protected static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return threadLocal.get();
    }


    public static WebDriver setBrowser(String browser) throws MalformedURLException {
        WebDriver driver = null;
        desiredCapabilities = new DesiredCapabilities();

        switch (browser){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), chromeOptions);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), edgeOptions);
                break;

            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444/wd/hub"), safariOptions);
                break;
            default:
                System.out.println("Provide right browser name");
        }
        driver.manage().window().maximize();
        return driver;
    }


}
