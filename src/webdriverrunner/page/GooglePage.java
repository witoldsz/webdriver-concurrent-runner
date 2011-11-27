package webdriverrunner.page;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.openqa.selenium.By;
import webdriverrunner.api.Browser;

/**
 *
 * @author Witold Szczerba
 */
@Singleton
public class GooglePage {
    
    private final Browser browser;
    
    @Inject
    public GooglePage(Browser browser) {
        this.browser = browser;
    }

    public void open() {
        browser.goToUrl("http://www.google.com");
    }
    
    public void search(String text) {
        browser.find("search field", By.id("lst-ib")).sendKeys(text);
    }
}
