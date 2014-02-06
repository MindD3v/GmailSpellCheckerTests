package com.gmailtests.pageobjects;

import com.gmailtests.data.SpellingErrorResults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ComposeEmailPage extends BasePageObject {

    private By _toLocator;
    private By _subjectLocator;
    private By _frameLocator;
    private By _bodyLocator;
    private By _composeEmailMenuLocator;
    private By _checkSpellingButtonLocator;
    private By _spellingErrorsLocator;
    private By _recheckSpellingButtonLocator;

    private String _id;
    public String getId()
    {
        return _id;
    }

    public ComposeEmailPage(WebDriver webDriver, String id){
        super(webDriver);
        _id = "div[aria-labelledby=\""+id+"\"]";
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
        _recheckSpellingButtonLocator = By.cssSelector(_id+" .T-I.J-J5-Ji.aDs.T-I-Js-IF.T-I-ax7.L3");
        
    }
    public ComposeEmailPage to(String to) {
            getToInput().sendKeys(to + ",");
        return this;
    }

    public ComposeEmailPage withSubject(String subject) {
            getSubjectInput().sendKeys(subject);
        return this;
    }

    public ComposeEmailPage withBody(String bodyText) {
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
        return new ComposeEmailMenu(_webDriver,this);
    }
    public ComposeEmailPage clickReCheckSpelling(){
        _webDriver.findElement(_recheckSpellingButtonLocator).click();
        return this;
    }

    private WebElement getToInput(){
        return _webDriver.findElement(_toLocator);
    }

    private WebElement getSubjectInput(){
        return _webDriver.findElement(_subjectLocator);
    }

    public SpellingErrorResults getSpellingErrorsList() throws InterruptedException {
        SpellingErrorResults spellingErrorsList = new SpellingErrorResults();
        WebElement frame = _webDriver.findElement(_frameLocator);
        _webDriver.switchTo().frame(frame);
        WebElement body = _webDriver.findElement(_bodyLocator);

        Thread.sleep(2000); //don't like
        _webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(_spellingErrorsLocator));

        List<WebElement> spellingErrors = body.findElements(_spellingErrorsLocator);

        for(WebElement spellingError : spellingErrors)
        {
            spellingErrorsList.add(spellingError.getText());
        }
        _webDriver.switchTo().defaultContent();


        return spellingErrorsList;
    }

    public class ComposeEmailMenu {
        private WebDriver _webDriver;
        private ComposeEmailPage _composeEmailPage;
        public ComposeEmailMenu(WebDriver webDriver, ComposeEmailPage owner)
        {
            _webDriver = webDriver;
            _composeEmailPage = owner;
        }
        public ComposeEmailPage clickCheckSpelling(){
            _webDriver.findElement(_checkSpellingButtonLocator).click();
            return _composeEmailPage;
        }

        public boolean isSpellCheckInMenu(){
            WebElement checkSpellMenu = _webDriver.findElement(_checkSpellingButtonLocator);
            return checkSpellMenu != null && checkSpellMenu.getText().equals("Check spelling");
        }
    }
}
