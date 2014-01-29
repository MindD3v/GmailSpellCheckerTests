package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.InboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GmailInboxTests extends GmailLoggedInTests {
    @Test(description = "Get to the compose email dialog")
    public void GetToTheComposeEmailDialog()
    {
        InboxPage inboxPage = PageFactory.initElements(_webDriver, InboxPage.class);
        ComposeEmailPage composeEmailPage = inboxPage.ComposeNewEmail();
        Assert.assertNotNull(composeEmailPage);
    }
}
