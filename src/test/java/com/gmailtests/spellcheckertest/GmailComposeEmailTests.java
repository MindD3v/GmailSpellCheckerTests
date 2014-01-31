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
        List<ComposeEmailPage> composeEmailPages = inboxPage.composeNewEmail();

        Assert.assertEquals(composeEmailPages.size(),1);

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a tests");

        Assert.assertEquals(composeEmailPage.getRecipients(),"jhinojosa@nearsoft.com");
        Assert.assertEquals(composeEmailPage.getSubject(),"Testing");
        Assert.assertEquals(composeEmailPage.getEmailBody(),"This is a tests");

    }
    @Test(description = "Compose multiple email")
    public void ComposeMultipleEmail()
    {
        InboxPage inboxPage = PageFactory.initElements(_webDriver,InboxPage.class);
        inboxPage.composeNewEmail();
        inboxPage.composeNewEmail();

        List<ComposeEmailPage> composeEmailPages = inboxPage.getComposeEmailPages();
        Assert.assertEquals(composeEmailPages.size(),2);

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a tests");

        ComposeEmailPage secondComposeEmailPage = composeEmailPages.get(1);
        secondComposeEmailPage.to("papucho@nearsoft.com").withSubject("Papucho testing").withBody("This is a tests for Serch");


        Assert.assertEquals(composeEmailPage.getRecipients(),"jhinojosa@nearsoft.com");
        Assert.assertEquals(composeEmailPage.getSubject(),"Testing");
        Assert.assertEquals(composeEmailPage.getEmailBody(),"This is a tests");

        Assert.assertEquals(secondComposeEmailPage.getRecipients(),"papucho@nearsoft.com");
        Assert.assertEquals(secondComposeEmailPage.getSubject(),"Papucho testing");
        Assert.assertEquals(secondComposeEmailPage.getEmailBody(),"This is a tests for Serch");
    }
    @Test(description = "Compose Email With Many Recipients")
         public void ComposeEmailWithManyRecipients()
    {
        InboxPage inboxPage = PageFactory.initElements(_webDriver,InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.composeNewEmail();

        Assert.assertEquals(composeEmailPages.size(),1);

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.to("jhinojosa@nearsoft.com").to("papucho@nearsoft.com").withSubject("Testing").withBody("This is a tests");

        Assert.assertEquals(composeEmailPage.getRecipients(),"jhinojosa@nearsoft.com, papucho@nearsoft.com");
        Assert.assertEquals(composeEmailPage.getSubject(),"Testing");
        Assert.assertEquals(composeEmailPage.getEmailBody(),"This is a tests");

    }
    @Test(description = "Compose Email With Many Recipients In a Single Line")
    public void ComposeEmailWithManyRecipientsInASingleLine()
    {
        InboxPage inboxPage = PageFactory.initElements(_webDriver,InboxPage.class);
        List<ComposeEmailPage> composeEmailPages = inboxPage.composeNewEmail();

        Assert.assertEquals(composeEmailPages.size(),1);

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.to("jhinojosa@nearsoft.com, papucho@nearsoft.com").withSubject("Testing").withBody("This is a tests");

        Assert.assertEquals(composeEmailPage.getRecipients(),"jhinojosa@nearsoft.com, papucho@nearsoft.com");
        Assert.assertEquals(composeEmailPage.getSubject(),"Testing");
        Assert.assertEquals(composeEmailPage.getEmailBody(),"This is a tests");

    }
}
