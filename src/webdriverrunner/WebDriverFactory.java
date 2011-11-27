package webdriverrunner;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 *
 * @author Witold Szczerba
 */
public class WebDriverFactory {

    public RemoteWebDriver create() {
        return new FirefoxDriver();
    }
    
}
