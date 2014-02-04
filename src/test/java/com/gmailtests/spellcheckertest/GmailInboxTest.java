package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.InboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GmailInboxTest extends GmailLoggedInSetup {
    @Test(description = "Get to the compose email dialog")
    public void getToTheComposeEmailDialog()
    {
        InboxPage inboxPage = PageFactory.initElements(getWebDriver(), InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.composeNewEmail();
        assertThat(composeEmailPages.size(), equalTo(1));
    }
    @Test(description = "Get multiple compose email dialog")
    public void getToMultipleComposeEmailDialog()
    {
        InboxPage inboxPage = PageFactory.initElements(getWebDriver(), InboxPage.class);
        inboxPage.composeNewEmail();
        inboxPage.composeNewEmail();
        List<ComposeEmailPage> composeEmailPages = inboxPage.getComposeEmailPages();

        assertThat(composeEmailPages.size(), equalTo(2));
        assertThat(composeEmailPages.get(0).getId(), not(equalTo(composeEmailPages.get(1).getId())));
    }
}
