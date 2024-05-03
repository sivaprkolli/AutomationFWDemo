package com.sip.ObjectRepository;

import com.sip.Uitlities.SIPActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AllProductsPage {

    private SIPActions sipActions;

    public AllProductsPage(WebDriver driver) {
        sipActions = new SIPActions(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_item_name ")
    private List<WebElement> listOfProducts;

    public int getNumberOfProducts() {
        System.out.println(sipActions.getNumberOfElements(listOfProducts));
        return sipActions.getNumberOfElements(listOfProducts);
    }

    public List<String> getAllProductTitles() {
        System.out.println(sipActions.getListOfTitles(listOfProducts));
        return sipActions.getListOfTitles(listOfProducts);
    }
}
