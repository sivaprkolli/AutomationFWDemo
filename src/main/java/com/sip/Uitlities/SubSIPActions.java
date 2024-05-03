package com.sip.Uitlities;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.security.sasl.AuthenticationException;

public class SubSIPActions extends SIPActions{

    public SubSIPActions(WebDriver driver) {
        super(driver);
    }

    @Override
    public void typeValue(WebElement element, String data){
        try {
            element.sendKeys(data);
        }catch (Exception e){
            throw  new NoSuchElementException("Element not found");
        }
    }
}
