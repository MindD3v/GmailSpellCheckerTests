package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.GmailLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class GmailLoggedInTests {
    protected WebDriver _webDriver;
    @BeforeTest
    public void Setup()
    {
        _webDriver = new FirefoxDriver();
        GmailLoginPage loginPage = PageFactory.initElements(_webDriver, GmailLoginPage.class);
        loginPage.Open();
        loginPage.LoginAs("seleniumtest.hinojosa@gmail.com").WithPassword("95867bb.").Login();
    }
    @AfterTest
    public void CleanUp()
    {
        //_webDriver.close();
    }
}
