package com.gmailtests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ComposeEmailBasePage extends BasePageObject {

    private By _toLocator;
    private By _subjectLocator;
    private By _frameLocator;
    private By _bodyLocator;
    private By _composeEmailMenuLocator;
    private By _checkSpellingButtonLocator;
    private By _spellingErrorsLocator;

    private String _id;
    public String getId()
    {
        return _id;
    }

    public ComposeEmailBasePage(WebDriver webDriver, String id){
        super(webDriver);
        _id = "div[aria-labelledby=\""+id+"\"]";
        _webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(_id + " .vO")));

        initializeLocators();
    }
    private void initializeLocators(){
        _toLocator = By.cssSelector(_id+" .vO");
        _subjectLocator = By.cssSelector(_id+" input[name=\"subjectbox\"]");
        _frameLocator = By.cssSelector(_id+" .Am.Al.editable iframe");
        _bodyLocator = By.cssSelector(".editable.LW-avf");
        _spellingErrorsLocator = By.cssSelector(".J-JK9eJ-PJVNOc");
        _composeEmailMenuLocator = By.cssSelector(_id+" .J-JN-M-I.J-J5-Ji.Xv.L3.T-I-ax7.T-I");
        _checkSpellingButtonLocator = By.cssSelector(_id+" .SK.AX .J-N:last-child");
    }
    public ComposeEmailBasePage to(String to) {
            getToInput().sendKeys(to + ",");
        return this;
    }

    public ComposeEmailBasePage withSubject(String subject) {
            getSubjectInput().sendKeys(subject);
        return this;
    }

    public ComposeEmailBasePage withBody(String bodyText) {
        WebElement frame = _webDriver.findElement(_frameLocator);
        _webDriver.switchTo().frame(frame);
        WebElement body = _webDriver.findElement(_bodyLocator);
        body.sendKeys(bodyText);
        _webDriver.switchTo().defaultContent();
        return this;
    }

    public String getRecipients() {
        List<WebElement> emailListWebElements = _webDriver.findElements(By.cssSelector(_id+" .oL.aDm.az9 span"));
        StringBuilder emailListBuilder = new StringBuilder();
        for(int i =0; i < emailListWebElements.size(); i++)
        {
            if(i != 0)
                emailListBuilder.append(", ");
            emailListBuilder.append(emailListWebElements.get(i).getText());
        }

        return emailListBuilder.toString();
    }

    public String getSubject() {
        return getSubjectInput().getAttribute("value");
    }

    public String getEmailBody() {
        WebElement frame = _webDriver.findElement(_frameLocator);
        _webDriver.switchTo().frame(frame);
        WebElement body = _webDriver.findElement(_bodyLocator);
        String bodyText = body.getText();
        _webDriver.switchTo().defaultContent();
        return bodyText;
    }

    public ComposeEmailMenu clickMoreOptionsMenu() {
        _webDriver.findElement(_composeEmailMenuLocator).click();
        return new ComposeEmailMenu(_webDriver);
    }

    private WebElement getToInput(){
        return _webDriver.findElement(_toLocator);
    }

    private WebElement getSubjectInput(){
        return _webDriver.findElement(_subjectLocator);
    }

    public class ComposeEmailMenu {
        private WebDriver _webDriver;
        public ComposeEmailMenu(WebDriver webDriver)
        {
            _webDriver = webDriver;
        }
        public ComposeEmailMenu clickCheckSpelling(){
            _webDriver.findElement(_checkSpellingButtonLocator).click();
            return this;
        }
        public List<String> getSpellingErrors() {
            List<String> spellingErrorsStrings = new ArrayList<String>();
            WebElement frame = _webDriver.findElement(_frameLocator);
            _webDriver.switchTo().frame(frame);
            WebElement body = _webDriver.findElement(_bodyLocator);

            _webDriverWait.until(ExpectedConditions.elementToBeClickable(_spellingErrorsLocator));

            List<WebElement> spellingErrors = body.findElements(_spellingErrorsLocator);

            for(WebElement spellingError : spellingErrors)
            {
                spellingErrorsStrings.add(spellingError.getText());
            }
            _webDriver.switchTo().defaultContent();


            return spellingErrorsStrings;
        }
        public boolean isSpellCheckInMenu(){
            WebElement checkSpellMenu = _webDriver.findElement(_checkSpellingButtonLocator);
            return checkSpellMenu != null && checkSpellMenu.getText().equals("Check spelling");
        }
    }
}
