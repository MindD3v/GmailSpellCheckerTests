package com.gmailtests.spellcheckertest;

import com.gmailtests.pageobjects.ComposeEmailPage;
import com.gmailtests.pageobjects.GmailMainPage;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GmailComposeEmailTest extends GmailLoggedInSetup {
    @Test(description = "Compose basic email")
    public void composeBasicEmail()
    {
        List<ComposeEmailPage> composeEmailPages = this.getGmailMainPage().waitForPageToLoad().composeNewEmail();

        assertThat(composeEmailPages.size(),equalTo(1));

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a tests");

        assertThat(composeEmailPage.getSubject(),   equalTo("Testing"));
        assertThat(composeEmailPage.getEmailBody(), equalTo("This is a tests"));
        assertThat(composeEmailPage.getRecipients(),equalTo("jhinojosa@nearsoft.com"));

    }
    @Test(description = "Compose multiple email")
    public void composeMultipleEmail()
    {
        GmailMainPage gmailMainPage = this.getGmailMainPage().waitForPageToLoad();
        gmailMainPage.composeNewEmail();
        gmailMainPage.composeNewEmail();

        List<ComposeEmailPage> composeEmailPages = gmailMainPage.getComposeEmailPages();
        assertThat(composeEmailPages.size(),equalTo(2));

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.to("jhinojosa@nearsoft.com").withSubject("Testing").withBody("This is a tests");

        ComposeEmailPage secondComposeEmailPage = composeEmailPages.get(1);
        secondComposeEmailPage.to("papucho@nearsoft.com").withSubject("Papucho testing").withBody("This is a tests for Serch");


        assertThat(composeEmailPage.getRecipients(), equalTo("jhinojosa@nearsoft.com"));
        assertThat(composeEmailPage.getSubject(),    equalTo("Testing"));
        assertThat(composeEmailPage.getEmailBody(),  equalTo("This is a tests"));

        assertThat(secondComposeEmailPage.getRecipients(), equalTo("papucho@nearsoft.com"));
        assertThat(secondComposeEmailPage.getSubject(),    equalTo("Papucho testing"));
        assertThat(secondComposeEmailPage.getEmailBody(),  equalTo("This is a tests for Serch"));
    }
    @Test(description = "Compose Email With Many Recipients")
     public void composeEmailWithManyRecipients()
    {
        List<ComposeEmailPage> composeEmailPages = this.getGmailMainPage().waitForPageToLoad().composeNewEmail();

        assertThat(composeEmailPages.size(),equalTo(1));

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.to("jhinojosa@nearsoft.com").to("papucho@nearsoft.com").withSubject("Testing").withBody("This is a tests");

        assertThat(composeEmailPage.getRecipients(), equalTo("jhinojosa@nearsoft.com, papucho@nearsoft.com"));
        assertThat(composeEmailPage.getSubject(),    equalTo("Testing"));
        assertThat(composeEmailPage.getEmailBody(),  equalTo("This is a tests"));

    }
    @Test(description = "Compose Email With Many Recipients In a Single Line")
    public void composeEmailWithManyRecipientsInASingleLine()
    {
        List<ComposeEmailPage> composeEmailPages = this.getGmailMainPage().waitForPageToLoad().composeNewEmail();

        assertThat(composeEmailPages.size(),equalTo(1));

        ComposeEmailPage composeEmailPage = composeEmailPages.get(0);
        composeEmailPage.to("jhinojosa@nearsoft.com, papucho@nearsoft.com").withSubject("Testing").withBody("This is a tests");

        assertThat(composeEmailPage.getRecipients(), equalTo("jhinojosa@nearsoft.com, papucho@nearsoft.com"));
        assertThat(composeEmailPage.getSubject(),    equalTo("Testing"));
        assertThat(composeEmailPage.getEmailBody(),  equalTo("This is a tests"));

    }
}
