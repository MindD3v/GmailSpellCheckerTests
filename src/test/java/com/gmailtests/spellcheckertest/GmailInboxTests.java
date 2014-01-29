package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.InboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GmailInboxTests extends GmailLoggedInTests {
    @Test(description = "Get to the compose email dialog")
    public void GetToTheComposeEmailDialog()
    {
        InboxPage inboxPage = PageFactory.initElements(_webDriver, InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.ComposeNewEmail();
        Assert.assertEquals(composeEmailPages.size(),1);
    }
    @Test(description = "Get to the compose email dialog")
    public void GetToMultipleComposeEmailDialog()
    {
        InboxPage inboxPage = PageFactory.initElements(_webDriver, InboxPage.class);
        inboxPage.ComposeNewEmail();
        inboxPage.ComposeNewEmail();
        List<ComposeEmailPage> composeEmailPages = inboxPage.GetComposeEmailPages();
        Assert.assertEquals(composeEmailPages.size(),2);
        Assert.assertNotEquals(composeEmailPages.get(0).GetId(),composeEmailPages.get(1).GetId());
    }
}
