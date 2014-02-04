package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.InboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GmailSpellCheckerTest extends GmailLoggedInSetup {
    @Test(description = "Get to the spellchecker menu")
    public void getToTheSpellCheckerMenu()
    {
        ComposeEmailPage composeEmailPage = setupForOneEmail();
        ComposeEmailPage.ComposeEmailMenu menu = composeEmailPage.clickMoreOptionsMenu();

        assertThat(menu.isSpellCheckInMenu(),equalTo(true));
    }
    @Test(description = "Find Spelling Errors On Email")
    public void findSpellingErrorsOnEmail() throws InterruptedException {
        ComposeEmailPage composeEmailPage = setupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss");
        List<String> spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrors();

        assertThat(spellingErrors.size(), equalTo(1));
    }
    @Test(description = "Find Multiple Spelling Errors On Email")
    public void findMultipleSpellingErrorsOnEmail() throws InterruptedException {
        ComposeEmailPage composeEmailPage = setupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss for my friendz");
        List<String> spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrors();

        assertThat(spellingErrors.size(), equalTo(2));
    }
    @Test(description = "Find Spelling Errors On Email And Recheck")
    public void findSpellingErrorsOnEmailAndRecheck() throws InterruptedException {
        ComposeEmailPage composeEmailPage = setupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss,");
        List<String> spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrors();

        assertThat(spellingErrors.size(), equalTo(1));

        composeEmailPage.withBody(" for my friendz");
        spellingErrors = composeEmailPage.clickReCheckSpelling().getSpellingErrors();

        assertThat(spellingErrors.size(), equalTo(2));
    }
    private ComposeEmailPage setupForOneEmail()
    {
        InboxPage inboxPage = PageFactory.initElements(getWebDriver(),InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.composeNewEmail();

        assertThat(composeEmailPages.size(), equalTo(1));

        return composeEmailPages.get(0);
    }
}
