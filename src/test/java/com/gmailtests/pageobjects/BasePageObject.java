package com.gmailtests.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageObject {
    WebDriver _webDriver;
    WebDriverWait _webDriverWait;

    public BasePageObject(WebDriver webDriver)
    {
        _webDriver = webDriver;
        _webDriverWait = new WebDriverWait(_webDriver,90);
    }

    public abstract <T extends BasePageObject> T waitForPageToLoad();
}
