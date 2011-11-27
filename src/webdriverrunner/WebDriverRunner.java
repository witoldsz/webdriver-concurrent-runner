package webdriverrunner;

import com.google.inject.Guice;
import com.google.inject.Injector;
import webdriverrunner.api.Browser;
import webdriverrunner.api.FeatureExecutor;
import webdriverrunner.engine.FixedThreadPoolFeatureExecutor;
import webdriverrunner.engine.ThreadLocalBrowsers;
import webdriverrunner.engine.WebDriverBrowserProxyProvider;
import webdriverrunner.feature.DemoFeature;

/**
 *
 * @author Witold Szczerba
 */
public class WebDriverRunner {
    
    private final FeatureExecutor featureExecutor;

    public WebDriverRunner(FeatureExecutor featureExecutor) {
        this.featureExecutor = featureExecutor;
    }
    
    private void run() throws Exception {
        featureExecutor.initialize(3);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.execute(DemoFeature.class);
        featureExecutor.awaitTermination();
    }
    
    public static void main(String[] args) throws Exception {
        ThreadLocalBrowsers browsersSupplier = new ThreadLocalBrowsers(new WebDriverFactory());
        try {
            Browser browserProxy = new WebDriverBrowserProxyProvider(browsersSupplier).get();
            Injector injector = Guice.createInjector(new WebDriverModule(browserProxy));
            FeatureExecutor executor = new FixedThreadPoolFeatureExecutor(injector, browserProxy);
            WebDriverRunner runner = new WebDriverRunner(executor);
            runner.run();
        } finally {
            browsersSupplier.shutdown();
        }
    }
}
