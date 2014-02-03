package com.gmailtests.spellcheckertest;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.testng.SauceOnDemandAuthenticationProvider;
import com.saucelabs.testng.SauceOnDemandTestListener;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.URL;

@Listeners({SauceOnDemandTestListener.class})
public abstract class SauceLabsSetup implements SauceOnDemandSessionIdProvider, SauceOnDemandAuthenticationProvider {
    protected SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("jhinojosa-nearsoft", "5d37527e-328a-4330-b8c1-fa0e6905a769");

    protected ThreadLocal<WebDriver> _webDriver = new ThreadLocal<WebDriver>();
    protected ThreadLocal<String> sessionId = new ThreadLocal<String>();

    @Parameters({"username", "key", "os", "browser", "browserVersion"})
    @BeforeMethod
    protected void setUp(@Optional("jhinojosa-nearsoft") String username,
                      @Optional("5d37527e-328a-4330-b8c1-fa0e6905a769") String key,
                      @Optional("XP") String os,
                      @Optional("firefox") String browser,
                      @Optional("26") String browserVersion,
                      Method method) throws Exception {

        // Choose the browser, version, and platform to test
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", Platform.valueOf(os));
        capabilities.setCapability("name", method.getName());
        // Create the connection to Sauce Labs to run the tests
        _webDriver.set(new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities));
        sessionId.set(((RemoteWebDriver) getWebDriver()).getSessionId().toString());
    }
    public WebDriver getWebDriver() {
        return _webDriver.get();
    }
    public String getSessionId() {
        return sessionId.get();
    }
    @AfterMethod
    protected void CleanUp() {
        getWebDriver().quit();
    }
    @Override
    public SauceOnDemandAuthentication getAuthentication() {
        return authentication;
    }
    //    @BeforeMethod
//    public void setUp() {
//        _webDriver = new FirefoxDriver();
//        GmailLoginPage loginPage = PageFactory.initElements(getWebDriver(), GmailLoginPage.class);
//        loginPage.open();
//        loginPage.loginAs("seleniumtest.hinojosa@gmail.com").withPassword("95867bb.").login();
//    }
}
