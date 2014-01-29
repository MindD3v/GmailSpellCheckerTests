package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.GmailLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GmailLoginTests {

    protected WebDriver _webDriver;
    @BeforeTest
    public void Setup()
    {
        _webDriver = new FirefoxDriver();
    }
    @AfterTest
    public void CleanUp()
    {
        _webDriver.close();
    }
    @Test(description="Launches gmail login")
    public void LaunchGmailLoginTest()
    {
        GmailLoginPage loginPage = PageFactory.initElements(_webDriver,GmailLoginPage.class);
        loginPage.Open();
        Assert.assertEquals("Gmail",_webDriver.getTitle());
    }
    @Test(description = "Login into gmail")
    public void LoginAsValidUserGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(_webDriver,GmailLoginPage.class);
        loginPage.Open();
        loginPage.LoginAs("seleniumtest.hinojosa@gmail.com").WithPassword("95867bb.").Login();
        Assert.assertEquals("Inbox - seleniumtest.hinojosa@gmail.com - Gmail", _webDriver.getTitle());
    }
    @Test(description = "Try to login with invalid user")
    public void LoginAsInvalidUserGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(_webDriver,GmailLoginPage.class);
        loginPage.Open();
        loginPage.LoginAs("seleniumtest.hinojosa50@gmail.com").WithPassword("95867bb.").LoginExpectingFailure();
        Assert.assertEquals("The email or password you entered is incorrect. ?", loginPage.GetErrorMessageForPassword());
    }
    @Test(description = "Try to login with invalid password")
    public void LoginAsInvalidPasswordGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(_webDriver,GmailLoginPage.class);
        loginPage.Open();
        loginPage.LoginAs("seleniumtest.hinojosa@gmail.com").WithPassword("95867bb..").LoginExpectingFailure();
        Assert.assertEquals("The email or password you entered is incorrect. ?", loginPage.GetErrorMessageForPassword());
    }
    @Test(description = "Try to login with empty fields")
    public void LoginWithEmptyFieldsGmail()
    {
        GmailLoginPage loginPage = PageFactory.initElements(_webDriver,GmailLoginPage.class);
        loginPage.Open();
        loginPage.LoginAs("").WithPassword("").LoginExpectingFailure();
        Assert.assertEquals("Enter your email address.", loginPage.GetErrorMessageForEmail());
    }
}
