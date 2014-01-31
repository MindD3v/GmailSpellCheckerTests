package com.gmailtests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GmailLoginPage extends BasePageObject {

    @FindBy(using = "Email")
    private WebElement _email;
    @FindBy(using = "Passwd")
    private WebElement _password;
    @FindBy(using = "signIn")
    private WebElement _signIn;
    @FindBy(using = "errormsg_0_Passwd")
    private WebElement _errorMessagePassword;
    @FindBy(using = "errormsg_0_Email")
    private WebElement _errorMessageEmail;

    public GmailLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public GmailLoginPage loginAs(String email) {
        _email.sendKeys(email);
        return this;
    }

    public GmailLoginPage withPassword(String password) {
        _password.sendKeys(password);
        return this;
    }

    public InboxPage login() {
        _signIn.click();
        _webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(":3o")));
        return PageFactory.initElements(_webDriver, InboxPage.class);
    }
    public GmailLoginPage loginExpectingFailure()
    {
        _signIn.click();
        return this;
    }

    public void open() {
        _webDriver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/");
    }

    public String getErrorMessageForPassword() {
        return _errorMessagePassword.getText();
    }
    public String getErrorMessageForEmail() {
        return _errorMessageEmail.getText();
    }
}
