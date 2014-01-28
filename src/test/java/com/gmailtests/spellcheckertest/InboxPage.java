package com.gmailtests.spellcheckertest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage {
    private WebDriver _webDriver;

    @FindBy(using = ":3o")
    private WebElement Inbox;
    public InboxPage(WebDriver webDriver) {
        _webDriver = webDriver;
    }
}
