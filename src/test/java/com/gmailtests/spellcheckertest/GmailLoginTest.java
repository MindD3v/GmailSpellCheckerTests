package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.GmailLoginPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class GmailLoginTest extends SauceLabsSetup {

    @Test(description="Launches gmail login")
    public void launchGmailLoginTest()
    {
        GmailLoginPage loginPage = new GmailLoginPage(getWebDriver());
        loginPage.open().waitForPageToLoad();

        assertThat("Is at login page",getWebDriver().getTitle(),equalTo("Gmail"));
    }
    @Test(description = "Login into gmail")
    public void loginAsValidUserGmail()
    {
        GmailLoginPage loginPage = new GmailLoginPage(getWebDriver());
        loginPage.open().waitForPageToLoad();
        loginPage.loginAs("seleniumtest.hinojosa@gmail.com").withPassword("95867bb.").login().waitForPageToLoad();

        assertThat("User logged in",getWebDriver().getTitle(),containsString("Inbox"));
    }
    @Test(description = "Try to login with invalid user")
    public void loginAsInvalidUserGmail()
    {
        GmailLoginPage loginPage = new GmailLoginPage(getWebDriver());
        loginPage.open().waitForPageToLoad();
        loginPage.loginAs("seleniumtest.hinojosa50@gmail.com").withPassword("95867bb.").loginExpectingFailure();

        assertThat("User login with invalid username",loginPage.getErrorMessageForPassword(),equalTo("The email or password you entered is incorrect. ?"));
    }
    @Test(description = "Try to login with invalid password")
    public void loginAsInvalidPasswordGmail()
    {
        GmailLoginPage loginPage = new GmailLoginPage(getWebDriver());
        loginPage.open().waitForPageToLoad();
        loginPage.loginAs("seleniumtest.hinojosa@gmail.com").withPassword("95867bb..").loginExpectingFailure();

        assertThat("User login with invalid password",loginPage.getErrorMessageForPassword(),equalTo("The email or password you entered is incorrect. ?"));
    }
    @Test(description = "Try to login with empty fields")
    public void loginWithEmptyFieldsGmail()
    {
        GmailLoginPage loginPage = new GmailLoginPage(getWebDriver());
        loginPage.open().waitForPageToLoad();
        loginPage.loginAs("").withPassword("").loginExpectingFailure();

        assertThat("User login with empty fields",loginPage.getErrorMessageForEmail(),equalTo("Enter your email address."));
    }
}
