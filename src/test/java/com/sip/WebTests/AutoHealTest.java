package com.sip.WebTests;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

//https://github.com/healenium/healenium-example-maven

// docker compose up
public class AutoHealTest {
    // public static WebDriver selfHealingDriver;
    // public static SelfHealingDriver driver;
    static protected SelfHealingDriver driver;

    @Test
    public void autoHealTest() throws MalformedURLException, InterruptedException {
        //driver = new ChromeDriver();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setBrowserName("chrome");
//        desiredCapabilities.setPlatform(Platform.MAC);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.merge(desiredCapabilities);
        WebDriver delegate = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        //WebDriver delegate = new ChromeDriver(chromeOptions);
        driver = SelfHealingDriver.create(delegate);

        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        driver.quit();
    }
}
