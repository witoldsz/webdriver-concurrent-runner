package webdriverrunner.feature;

import webdriverrunner.api.Feature;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import webdriverrunner.page.GooglePage;

/**
 *
 * @author Witold Szczerba
 */
@Singleton
public class DemoFeature implements Feature {
    
    @Inject 
    private GooglePage googlePage;
    
    @Override
    public void run() {
        googlePage.open();
        googlePage.search("Selenium WebDriver\n");
    }
}
