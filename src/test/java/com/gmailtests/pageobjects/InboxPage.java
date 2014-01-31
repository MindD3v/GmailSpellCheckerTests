package com.gmailtests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class InboxPage {
    private WebDriver _webDriver;

    private List<ComposeEmailPage> _composeEmailPages;

    public List<ComposeEmailPage> getComposeEmailPages()
    {
        return _composeEmailPages;
    }

    @FindBy(using =".nM .T-I.J-J5-Ji.T-I-KE.L3",how = How.CSS)
    private WebElement _compose;
    public InboxPage(WebDriver webDriver) {
        _composeEmailPages = new ArrayList<ComposeEmailPage>();
        _webDriver = webDriver;
    }
    public List<ComposeEmailPage> composeNewEmail()
    {
        _compose.click();
        WebDriverWait wait = new WebDriverWait(_webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".nH .Hd")));
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
}
