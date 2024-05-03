package com.sip.ObjectRepository;

import com.sip.Base.WebDriverFactory;
import com.sip.Uitlities.SIPActions;
import com.sip.Uitlities.SubSIPActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver driver;
    private SIPActions sipActions;
    private SubSIPActions subSIPActions;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        sipActions = new SIPActions(driver);
        subSIPActions = new SubSIPActions(driver);
    }

    @FindBy(id = "user-name")
    private WebElement userNameInputBox;

    @FindBy(name = "password")
    private WebElement passwordInputBox;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    private By submitButon = By.cssSelector("#login-button");

    public void login(String un, String pwd){
        subSIPActions.typeValue(userNameInputBox, un);
        subSIPActions.typeValue(passwordInputBox, pwd);
        sipActions.clickOnElement(submitButon);
        //sipActions.clickOnElement(loginButton);
        //driver.findElement(By.cssSelector("#login-button")).click();
    }


}
