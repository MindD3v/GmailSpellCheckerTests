package com.gmailtests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ComposeEmailPage {
    private WebDriver _webDriver;

    private By _toLocator;
    private By _subjectLocator;

    private String _id;
    public String GetId()
    {
        return _id;
    }

    public ComposeEmailPage(WebDriver webDriver,String id)
    {
        _webDriver = webDriver;
        _id = id;
        WebDriverWait wait = new WebDriverWait(_webDriver,50);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#\\"+_id+" .vO")));

        _toLocator = By.cssSelector("#\\"+_id+" .vO");
        _subjectLocator = By.cssSelector("#\\"+_id+" input[name=\"subjectbox\"]");
    }
    public ComposeEmailPage To(String to) {
        GetToInput().sendKeys(to);
        return this;
    }

    public ComposeEmailPage WithSubject(String subject) {
        GetSubjectInput().sendKeys(subject);
        return this;
    }

    public ComposeEmailPage WithBody(String bodyText) {
        WebElement frame = _webDriver.findElement(By.cssSelector(".Am.Al.editable iframe"));
        _webDriver.switchTo().frame(frame);
        WebElement body = _webDriver.findElement(By.cssSelector(".editable.LW-avf"));
        body.sendKeys(bodyText);
        _webDriver.switchTo().defaultContent();
        return this;
    }

    public String GetRecipients() {
        List<WebElement> emailListWebElements = _webDriver.findElements(By.cssSelector("#\\"+_id+" .oL.aDm.az9 span"));
        StringBuilder emailListBuilder = new StringBuilder();
        for(int i =0; i < emailListWebElements.size(); i++)
        {
            if(i != 0)
                emailListBuilder.append(", ");
            emailListBuilder.append(emailListWebElements.get(0).getText());
        }

        return emailListBuilder.toString();
    }

    public String GetSubject() {
        return GetSubjectInput().getAttribute("value");
    }

    public String GetEmailBody() {
        WebElement frame = _webDriver.findElement(By.cssSelector(".Am.Al.editable iframe"));
        _webDriver.switchTo().frame(frame);
        WebElement body = _webDriver.findElement(By.cssSelector(".editable.LW-avf"));
        String bodyText = body.getText();
        _webDriver.switchTo().defaultContent();
        return bodyText;
    }

    private WebElement GetToInput()
    {
        return _webDriver.findElement(_toLocator);
    }
    private WebElement GetSubjectInput()
    {
        return _webDriver.findElement(_subjectLocator);
    }
}
