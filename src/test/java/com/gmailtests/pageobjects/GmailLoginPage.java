package com.gmailtests.pageobjects;

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
        PageFactory.initElements(webDriver,this);
    }

    @Override
    public GmailLoginPage waitForPageToLoad() {
        _webDriverWait.until(ExpectedConditions.titleIs("Gmail"));
        return this;
    }

    public GmailLoginPage loginAs(String email) {
        _email.sendKeys(email);
        return this;
    }

    public GmailLoginPage withPassword(String password) {
        _password.sendKeys(password);
        return this;
    }

    public GmailMainPage login() {
        _signIn.click();
        //waitForElement(By.cssSelector(".nM"));
        return new GmailMainPage(_webDriver);
    }
    public GmailLoginPage loginExpectingFailure()
    {
        _signIn.click();
        return this;
    }

    public GmailLoginPage open() {
        _webDriver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/");
        return this;
    }

    public String getErrorMessageForPassword() {
        return _errorMessagePassword.getText();
    }
    public String getErrorMessageForEmail() {
        return _errorMessageEmail.getText();
    }
}
