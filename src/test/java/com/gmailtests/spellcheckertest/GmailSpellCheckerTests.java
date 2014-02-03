package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.InboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GmailSpellCheckerTests extends GmailLoggedInTests {
    @Test(description = "Get to the spellchecker menu")
    public void GetToTheSpellCheckerMenu()
    {
        ComposeEmailPage composeEmailPage = SetupForOneEmail();
        ComposeEmailPage.ComposeEmailMenu menu = composeEmailPage.clickMoreOptionsMenu();

        assertTrue(menu.isSpellCheckInMenu());
    }
    @Test(description = "Find Spelling Errors On Email")
    public void FindSpellingErrorsOnEmail() throws InterruptedException {
        ComposeEmailPage composeEmailPage = SetupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss");
        List<String> spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrors();

        assertEquals(spellingErrors.size(), 1);
    }
    @Test(description = "Find Multiple Spelling Errors On Email")
    public void FindMultipleSpellingErrorsOnEmail() throws InterruptedException {
        ComposeEmailPage composeEmailPage = SetupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss for my friendz");
        List<String> spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrors();

        assertEquals(spellingErrors.size(), 2);
    }
    @Test(description = "Find Spelling Errors On Email And Recheck")
    public void FindSpellingErrorsOnEmailAndRecheck() throws InterruptedException {
        ComposeEmailPage composeEmailPage = SetupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss,");
        List<String> spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrors();

        assertEquals(spellingErrors.size(), 1);

        composeEmailPage.withBody(" for my friendz");
        spellingErrors = composeEmailPage.clickReCheckSpelling().getSpellingErrors();

        assertEquals(spellingErrors.size(), 2);
    }
    private ComposeEmailPage SetupForOneEmail()
    {
        InboxPage inboxPage = PageFactory.initElements(getWebDriver(),InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.composeNewEmail();

        assertEquals(composeEmailPages.size(), 1);

        return composeEmailPages.get(0);
    }
}
