package webdriverrunner.engine;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.remote.RemoteWebDriver;
import webdriverrunner.WebDriverFactory;
import webdriverrunner.api.Browser;

/**
 *
 * @author Witold Szczerba
 */
public class ThreadLocalBrowsers extends ThreadLocal<Browser> {

    private final WebDriverFactory webDriverFactory;
    
    private final List<WebDriverBrowser> browsersEverCreated;
    
    public ThreadLocalBrowsers(WebDriverFactory webDriverFactory) {
        this.webDriverFactory = webDriverFactory;
        browsersEverCreated = new ArrayList<WebDriverBrowser>();
    }

    @Override
    protected Browser initialValue() {
        RemoteWebDriver webDriver = webDriverFactory.create();
        WebDriverBrowser browser = new WebDriverBrowser(webDriver);
        browsersEverCreated.add(browser);
        return browser;
    }

    @Override
    public Browser get() {
        return super.get();
    }

    public void shutdown() {
        for (WebDriverBrowser browser : browsersEverCreated) {
            browser.exitQuietly();
        }
        browsersEverCreated.clear();
    }
}
