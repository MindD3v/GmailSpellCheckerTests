package com.gmailtests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Email {

    private String _sender;
    private String _subject;
    private String _bodyPreview;
    private Boolean _isRead;

    public Email(String sender, String subject, String body, Boolean isRead)
    {
        _sender = sender;
        _subject = subject;
        _bodyPreview = body;
        _isRead = isRead;
    }

    public String get_sender() {
        return _sender;
    }

    public String get_subject() {
        return _subject;
    }

    public String get_bodyPreview() {
        return _bodyPreview;
    }

    public Boolean get_isRead() {
        return _isRead;
    }

    public static Email BuildEmailFormWebElement(WebElement startingElement)
    {
        String sender = startingElement.findElement(By.cssSelector(".yX.xY .yW span")).getText();
        String subject = startingElement.findElement(By.cssSelector(".xY .xS .xT .y6 span")).getText();
        String bodyPreview = startingElement.findElement(By.cssSelector(".xY .xS .xT .y6 .y2")).getText();
        boolean isRead = startingElement.getAttribute("class").contains("yO");

        return new Email(sender,subject,bodyPreview,isRead);
    }
}
