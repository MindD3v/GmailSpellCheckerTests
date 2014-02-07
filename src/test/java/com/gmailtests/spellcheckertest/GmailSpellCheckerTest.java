package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.data.SpellingErrorResults;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
        SpellingErrorResults spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrorsList();

        assertThat(spellingErrors.getTotalSpellingErrorCount(), equalTo(1));
    }
    @Test(description = "Find Multiple Spelling Errors On Email")
    public void findMultipleSpellingErrorsOnEmail() throws InterruptedException {
        ComposeEmailPage composeEmailPage = setupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss for my friendz");
        SpellingErrorResults spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrorsList();

        assertThat(spellingErrors.getTotalSpellingErrorCount(), equalTo(2));
    }
    @Test(description = "Find Spelling Errors On Email And Recheck")
    public void findSpellingErrorsOnEmailAndRecheck() throws InterruptedException {
        ComposeEmailPage composeEmailPage = setupForOneEmail();

        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a testsss,");
        SpellingErrorResults spellingErrors = composeEmailPage.clickMoreOptionsMenu().clickCheckSpelling().getSpellingErrorsList();

        assertThat(spellingErrors.getTotalSpellingErrorCount(), equalTo(1));

        composeEmailPage.withBody(" for my friendz");
        spellingErrors = composeEmailPage.clickReCheckSpelling().getSpellingErrorsList();

        assertThat(spellingErrors.getTotalSpellingErrorCount(), equalTo(2));
    }
    private ComposeEmailPage setupForOneEmail()
    {
        List<ComposeEmailPage> composeEmailPages = this.getGmailMainPage().waitForPageToLoad().composeNewEmail();

        assertThat(composeEmailPages.size(), equalTo(1));

        return composeEmailPages.get(0);
    }
}
