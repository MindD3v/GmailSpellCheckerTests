package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.GmailMainPage;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class GmailInboxTest extends GmailLoggedInSetup {
    @Test(description = "Get to the compose email dialog")
    public void getToTheComposeEmailDialog()
    {
        List<ComposeEmailPage> composeEmailPages = this.getGmailMainPage().waitForPageToLoad().composeNewEmail();
        assertThat(composeEmailPages.size(), equalTo(1));
    }
    @Test(description = "Get multiple compose email dialog")
    public void getToMultipleComposeEmailDialog()
    {
        GmailMainPage gmailMainPage = this.getGmailMainPage().waitForPageToLoad();
        gmailMainPage.composeNewEmail();
        gmailMainPage.composeNewEmail();

        List<ComposeEmailPage> composeEmailPages = gmailMainPage.getComposeEmailPages();

        assertThat(composeEmailPages.size(), equalTo(2));
        assertThat(composeEmailPages.get(0).getId(), not(equalTo(composeEmailPages.get(1).getId())));
    }
}
