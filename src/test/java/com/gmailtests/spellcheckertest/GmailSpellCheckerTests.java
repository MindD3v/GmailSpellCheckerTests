package com.gmailtests.spellcheckertest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by jhinojosa on 1/27/14.
 */
public class GmailSpellCheckerTests {

    public WebDriver _webDriver;
    @BeforeTest
    public void Setup()
    {
        _webDriver = new FirefoxDriver();
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
        loginPage.LoginAs("seleniumtest.hinojosa@gmail.com").WithPassword("95867bb.");
        InboxPage inboxPage = loginPage.Login();
        Assert.assertEquals("Inbox - seleniumtest.hinojosa@gmail.com - Gmail", _webDriver.getTitle());
    }
}
