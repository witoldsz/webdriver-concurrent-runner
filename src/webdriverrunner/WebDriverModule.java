package webdriverrunner;

import com.google.inject.AbstractModule;
import webdriverrunner.api.Browser;

/**
 *
 * @author Witold Szczerba
 */
public class WebDriverModule extends AbstractModule {

    private final Browser browserProxy;

    public WebDriverModule(Browser browserProxy) {
        this.browserProxy = browserProxy;
    }

    @Override
    protected void configure() {
        bind(Browser.class).toInstance(browserProxy);
    }
}
