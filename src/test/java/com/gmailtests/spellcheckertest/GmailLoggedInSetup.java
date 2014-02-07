package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.GmailLoginPage;
import com.gmailtests.pageobjects.GmailMainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public abstract class GmailLoggedInSetup extends SauceLabsSetup {

    private GmailMainPage _gmailMainPage;

    @Parameters({"username", "key", "os", "browser", "browserVersion"})
    @BeforeMethod
    public void setUp(@Optional("jhinojosa-nearsoft") String username,
                      @Optional("5d37527e-328a-4330-b8c1-fa0e6905a769") String key,
                      @Optional("XP") String os,
                      @Optional("firefox") String browser,
                      @Optional("26") String browserVersion,
                      Method method) throws Exception {

        super.setUp(username,key,os,browser,browserVersion,method);

        GmailLoginPage gmailLoginPage = new GmailLoginPage(_webDriver.get());
        gmailLoginPage.open();
        _gmailMainPage = gmailLoginPage.loginAs("seleniumtest.hinojosa@gmail.com").withPassword("95867bb.").login().waitForPageToLoad();
    }

    protected GmailMainPage getGmailMainPage()
    {
        if(_gmailMainPage != null)
        {
            return _gmailMainPage;
        }

        GmailLoginPage gmailLoginPage = new GmailLoginPage(_webDriver.get());
        gmailLoginPage.open();
        _gmailMainPage = gmailLoginPage.loginAs("seleniumtest.hinojosa@gmail.com").withPassword("95867bb.").login().waitForPageToLoad();

        return _gmailMainPage;
    }
}
