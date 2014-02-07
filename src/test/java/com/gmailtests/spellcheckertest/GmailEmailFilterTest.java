package com.gmailtests.spellcheckertest;

import com.gmailtests.data.Email;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GmailEmailFilterTest extends GmailLoggedInSetup {
    @Test(description="Get all email")
    public void getEmails() throws InterruptedException {
        List<Email> emailList = getGmailMainPage().waitForPageToLoad().getEmails();
        assertThat(emailList.size(), equalTo(4));
    }
    @Test(description="Get Filter emails")
    public void filterEmails() throws InterruptedException {
        List<Email> emailList = getGmailMainPage().applyFilter("Test 2").getEmails();
        assertThat(emailList.size(), equalTo(1));
    }

    @Test(description="Get Unread emails")
    public void getUnreadEmails() throws InterruptedException {
        List emailList = getGmailMainPage().waitForPageToLoad().applyFilter("is:unread").getEmails();

        assertThat((List<Object>)emailList, hasItem(hasProperty("_isRead", is(false))));
    }
    @Test(description="Get Unread emails")
    public void getReadEmails() throws InterruptedException {
        List emailList = getGmailMainPage().waitForPageToLoad().applyFilter("is:inbox is:read").getEmails();

        assertThat((List<Object>)emailList, hasItem(hasProperty("_isRead", is(true))));
    }
}
