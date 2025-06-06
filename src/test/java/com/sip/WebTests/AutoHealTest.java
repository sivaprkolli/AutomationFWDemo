package com.sip.WebTests;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

//https://github.com/healenium/healenium-example-maven

// docker compose up
public class AutoHealTest {
    // public static WebDriver selfHealingDriver;
    // public static SelfHealingDriver driver;
    static protected SelfHealingDriver driver;

    @AfterTest
    public void killSession() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

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
        driver.manage().window().maximize();
        Thread.sleep(2000);
        try {
            driver.findElement(By.xpath("//form/div/input[@placeholder='Username']")).sendKeys("standard_user");
        }catch (NoSuchElementException nse){
            driver.findElement(By.id("test1426531")).sendKeys("standard_user");
        }
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();
    }
}
