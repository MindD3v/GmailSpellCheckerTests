package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.InboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GmailSpellCheckerTests extends GmailLoggedInTests {
    @Test(description = "Get to the spellchecker menu")
    public void GetToTheSpellCheckerMenu()
    {
        InboxPage inboxPage = PageFactory.initElements(_webDriver, InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.composeNewEmail();

        Assert.assertEquals(composeEmailPages.size(), 1);

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);

        composeEmailPage.clickComposeEmailMenu();
        Assert.assertTrue(composeEmailPage.isSpellCheckInMenu());
    }
    @Test(description = "Find Spelling Errors On Email")
    public void FindSpellingErrorsOnEmail() {
        InboxPage inboxPage = PageFactory.initElements(_webDriver,InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.composeNewEmail();

        Assert.assertEquals(composeEmailPages.size(),1);

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss");
        composeEmailPage.clickComposeEmailMenu();
        composeEmailPage.clickCheckSpelling();

        List<String> spellingErrors = composeEmailPage.getSpellingErrors();
        Assert.assertEquals(spellingErrors.size(),1);
    }
}
