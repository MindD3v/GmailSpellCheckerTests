package com.gmailtests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class GmailMainPage extends BasePageObject {
    private List<ComposeEmailPage> _composeEmailPages;

    public List<ComposeEmailPage> getComposeEmailPages()
    {
        return _composeEmailPages;
    }

    @FindBy(using =".nM .T-I.J-J5-Ji.T-I-KE.L3",how = How.CSS)
    private WebElement _compose;

    @FindBy(using = "q",how = How.NAME)
    private WebElement _filter;

    @FindBy(using = "gbqfb")
    private WebElement _search;

    public GmailMainPage(WebDriver webDriver) {
        super(webDriver);
        _composeEmailPages = new ArrayList<ComposeEmailPage>();
    }
    public List<ComposeEmailPage> composeNewEmail()
    {
        _compose.click();
        _webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".nH .Hd")));
        getComposeNewEmailWindows();
        return _composeEmailPages;
    }
    private void getComposeNewEmailWindows()
    {
        _composeEmailPages.clear();
        List<WebElement> windows = _webDriver.findElements(By.cssSelector(".nH .Hd"));
        for(WebElement w : windows)
        {
            ComposeEmailPage composeEmailPage = new ComposeEmailPage(_webDriver,w.getAttribute("aria-labelledby"));
            _composeEmailPages.add(composeEmailPage);
        }
    }

    public GmailMainPage applyFilter(String filter) throws InterruptedException {
        _filter.sendKeys(filter);
        Thread.sleep(3000);
        return this;
    }

    public List<Email> getEmails() throws InterruptedException {
        _search.click();
        Thread.sleep(2000);
        List<Email> emailList = new ArrayList<Email>();
        List<WebElement> emails = _webDriver.findElements(By.cssSelector(".zA"));

        for(WebElement emailWebElement : emails)
        {
            emailList.add(Email.BuildEmailFormWebElement(emailWebElement));
        }

        return emailList;
    }
}
