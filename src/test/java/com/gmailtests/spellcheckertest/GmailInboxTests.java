package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.InboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class GmailInboxTests extends GmailLoggedInTests {
    @Test(description = "Get to the compose email dialog")
    public void GetToTheComposeEmailDialog()
    {
        InboxPage inboxPage = PageFactory.initElements(getWebDriver(), InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.composeNewEmail();
        assertEquals(composeEmailPages.size(), 1);
    }
    @Test(description = "Get multiple compose email dialog")
    public void GetToMultipleComposeEmailDialog()
    {
        InboxPage inboxPage = PageFactory.initElements(getWebDriver(), InboxPage.class);
        inboxPage.composeNewEmail();
        inboxPage.composeNewEmail();
        List<ComposeEmailPage> composeEmailPages = inboxPage.getComposeEmailPages();

        assertEquals(composeEmailPages.size(), 2);
        assertNotEquals(composeEmailPages.get(0).getId(), composeEmailPages.get(1).getId());
    }
}
