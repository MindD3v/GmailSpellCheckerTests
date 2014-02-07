package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.GmailLoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GmailLoginTest extends SauceLabsSetup {

    @Test(description="Launches gmail login")
    public void launchGmailLoginTest()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open().waitForPageToLoad();

        assertThat("Is at login page",getWebDriver().getTitle(),equalTo("Gmail"));
    }
    @Test(description = "Login into gmail")
    public void loginAsValidUserGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open().waitForPageToLoad();
        loginPage.loginAs("seleniumtest.hinojosa@gmail.com").withPassword("95867bb.").login();

        assertThat("User logged in",getWebDriver().getTitle(),equalTo("Inbox - seleniumtest.hinojosa@gmail.com - Gmail"));
    }
    @Test(description = "Try to login with invalid user")
    public void loginAsInvalidUserGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open().waitForPageToLoad();
        loginPage.loginAs("seleniumtest.hinojosa50@gmail.com").withPassword("95867bb.").loginExpectingFailure();

        assertThat("User login with invalid username",loginPage.getErrorMessageForPassword(),equalTo("The email or password you entered is incorrect. ?"));
    }
    @Test(description = "Try to login with invalid password")
    public void loginAsInvalidPasswordGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open().waitForPageToLoad();
        loginPage.loginAs("seleniumtest.hinojosa@gmail.com").withPassword("95867bb..").loginExpectingFailure();

        assertThat("User login with invalid password",loginPage.getErrorMessageForPassword(),equalTo("The email or password you entered is incorrect. ?"));
    }
    @Test(description = "Try to login with empty fields")
    public void loginWithEmptyFieldsGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(),GmailLoginPage.class);
        loginPage.open().waitForPageToLoad();
        loginPage.loginAs("").withPassword("").loginExpectingFailure();

        assertThat("User login with empty fields",loginPage.getErrorMessageForEmail(),equalTo("Enter your email address."));
    }
}
