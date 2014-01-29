package com.gmailtests.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class InboxPage {
    private WebDriver _webDriver;

    @FindBy(using ="div.T-I.J-J5-Ji.T-I-KE.L3",how = How.CSS)
    private WebElement _compose;
    public InboxPage(WebDriver webDriver) {
        _webDriver = webDriver;
    }
    public ComposeEmailPage ComposeNewEmail()
    {
        _compose.click();
        return PageFactory.initElements(_webDriver,ComposeEmailPage.class);
    }
}
