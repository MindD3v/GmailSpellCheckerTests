package com.gmailtests.spellcheckertest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailLoginPage {

    private WebDriver _webDriver;
    private WebDriverWait _wait;

    @FindBy(using = "Email")
    private WebElement _email;
    @FindBy(using = "Passwd")
    private WebElement _password;
    @FindBy(using = "signIn")
    private WebElement _signIn;
    @FindBy(using = "errormsg_0_Passwd")
    private WebElement _errorMessage;


    public GmailLoginPage(WebDriver webDriver) {
        _webDriver = webDriver;
        _wait = new WebDriverWait(_webDriver, 10);
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
        _wait.until(ExpectedConditions.elementToBeClickable(By.id(":3o")));
        return PageFactory.initElements(_webDriver, InboxPage.class);
    }
    public GmailLoginPage LoginExpectingFailure()
    {
        _signIn.click();
        return this;
    }

    public void Open() {
        _webDriver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/");
    }

    public String GetErrorMessage() {
        return _errorMessage.getText();
    }
}
