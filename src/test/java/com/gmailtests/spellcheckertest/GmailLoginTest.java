package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.GmailLoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GmailLoginTest extends SauceLabsSetup {

    @Test(description="Launches gmail login")
    public void LaunchGmailLoginTest()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open();

        assertEquals("Gmail", getWebDriver().getTitle());
    }
    @Test(description = "Login into gmail")
    public void LoginAsValidUserGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open();
        loginPage.loginAs("seleniumtest.hinojosa@gmail.com").withPassword("95867bb.").login();

        assertEquals("Inbox - seleniumtest.hinojosa@gmail.com - Gmail", getWebDriver().getTitle());
    }
    @Test(description = "Try to login with invalid user")
    public void LoginAsInvalidUserGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open();
        loginPage.loginAs("seleniumtest.hinojosa50@gmail.com").withPassword("95867bb.").loginExpectingFailure();

        assertEquals("The email or password you entered is incorrect. ?", loginPage.getErrorMessageForPassword());
    }
    @Test(description = "Try to login with invalid password")
    public void LoginAsInvalidPasswordGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open();
        loginPage.loginAs("seleniumtest.hinojosa@gmail.com").withPassword("95867bb..").loginExpectingFailure();

        assertEquals("The email or password you entered is incorrect. ?", loginPage.getErrorMessageForPassword());
    }
    @Test(description = "Try to login with empty fields")
    public void LoginWithEmptyFieldsGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open();
        loginPage.loginAs("").withPassword("").loginExpectingFailure();

        assertEquals("Enter your email address.", loginPage.getErrorMessageForEmail());
    }
}