package com.gmailtests.spellcheckertest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jhinojosa on 1/27/14.
 */
public class GmailLoginPage {

    private WebDriver _webDriver;

    @FindBy(using = "Email")
    private WebElement _email;
    @FindBy(using = "Passwd")
    private WebElement _password;
    @FindBy(using = "signIn")
    private WebElement _signIn;


    public GmailLoginPage(WebDriver webDriver)
    {
        _webDriver = webDriver;
//        if(_webDriver.getTitle() != "Gmail")
//            throw new IllegalStateException("This is not the login page");
    }
    public GmailLoginPage LoginAs(String email) {
        _email.sendKeys(email);
        return this;
    }

    public GmailLoginPage WithPassword(String password) {
        _password.sendKeys(password);
        return this;
    }

    public InboxPage Login() {
        _signIn.click();
        return PageFactory.initElements(_webDriver, InboxPage.class);
    }

    public void Open() {
        _webDriver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/");
    }
}
