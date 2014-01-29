package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.InboxPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GmailComposeEmailTests extends GmailLoggedInTests {
    @Test(description = "Compose basic email")
    public void ComposeBasicEmail()
    {
        InboxPage inboxPage = PageFactory.initElements(_webDriver,InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.ComposeNewEmail();

        Assert.assertEquals(composeEmailPages.size(),1);

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.To("jhinojosa@nearsoft.com").WithSubject("Testing").WithBody("This is a tests");

        Assert.assertEquals(composeEmailPage.GetRecipients(),"jhinojosa@nearsoft.com");
        Assert.assertEquals(composeEmailPage.GetSubject(),"Testing");
        Assert.assertEquals(composeEmailPage.GetEmailBody(),"This is a tests");

    }
}
