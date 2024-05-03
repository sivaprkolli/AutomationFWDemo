package com.sip.Uitlities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SIPActions {
    private WebDriver driver;

    public SIPActions(WebDriver driver){
        this.driver = driver;
    }


    public void waitForElementTobeClickable(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickOnElement(WebElement element){
        element.click();
    }

    public void clickOnElement(By by){
        driver.findElement(by).click();
    }

    public void typeValue(WebElement element, String data){
        element.sendKeys(data);
    }

    public int getNumberOfElements(List<WebElement> elementList){
        return elementList.size();
    }

    public List<String> getListOfTitles(List<WebElement> elementList){
        List<String> titles = new ArrayList<>();
        for (int i=0; i<elementList.size(); i++){
            titles.add(elementList.get(i).getText());
        }
        return titles;
    }
}
