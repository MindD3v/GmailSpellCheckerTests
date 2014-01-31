package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailBasePage;
import com.gmailtests.pageobjects.InboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GmailSpellCheckerTests extends GmailLoggedInTests {
    @Test(description = "Get to the spellchecker menu")
    public void GetToTheSpellCheckerMenu()
    {
        ComposeEmailBasePage composeEmailPage = SetupForOneEmail();
        ComposeEmailBasePage.ComposeEmailMenu menu = composeEmailPage.clickMoreOptionsMenu();
        Assert.assertTrue(menu.isSpellCheckInMenu());
    }
    @Test(description = "Find Spelling Errors On Email")
    public void FindSpellingErrorsOnEmail() {
        ComposeEmailBasePage composeEmailPage = SetupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss");
        List<String> spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrors();

        Assert.assertEquals(spellingErrors.size(),1);
    }
    @Test(description = "Find Multiple Spelling Errors On Email")
    public void FindMultipleSpellingErrorsOnEmail() {
        ComposeEmailBasePage composeEmailPage = SetupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss for my friendz");
        List<String> spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrors();

        Assert.assertEquals(spellingErrors.size(),2);
    }
    private ComposeEmailBasePage SetupForOneEmail()
    {
        InboxPage inboxPage = PageFactory.initElements(_webDriver,InboxPage.class);
        List<ComposeEmailBasePage> composeEmailPages = inboxPage.composeNewEmail();

        Assert.assertEquals(composeEmailPages.size(),1);

        return composeEmailPages.get(0);
    }
}
