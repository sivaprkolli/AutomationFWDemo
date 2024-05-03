package com.sip.WebTests;

import com.sip.Base.SIPBase;
import com.sip.ObjectRepository.AllProductsPage;
import com.sip.ObjectRepository.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginTests extends SIPBase {
    LoginPage loginPage;
    AllProductsPage allProductsPage;
    @BeforeTest
    public void intializePages(){
        loginPage = new LoginPage(driver);
        allProductsPage = new AllProductsPage(driver);
    }

    @Test
    public void verifySuccessfulLogin(){
        loginPage.login("performance_glitch_user","secret_sauce");
        Assert.assertEquals(allProductsPage.getNumberOfProducts(), 6);

        List<String> expectedTitles = new ArrayList<>(Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"));
        Assert.assertEquals(allProductsPage.getAllProductTitles(), expectedTitles);

    }
}
