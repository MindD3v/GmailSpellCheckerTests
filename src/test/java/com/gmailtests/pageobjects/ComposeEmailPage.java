package com.gmailtests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComposeEmailPage {
    private WebDriver _webDriver;

    @FindBy(using =".vO",how= How.CSS)
    private WebElement _to;
    @FindBy(using ="subjectbox", how = How.NAME)
    private WebElement _subject;

    public ComposeEmailPage(WebDriver webDriver)
    {
        _webDriver = webDriver;
        WebDriverWait wait = new WebDriverWait(_webDriver,20);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".vO")));
    }

    public ComposeEmailPage To(String to) {
        _to.sendKeys(to);
        return this;
    }

    public ComposeEmailPage WithSubject(String subject) {
        _subject.sendKeys(subject);
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
}
