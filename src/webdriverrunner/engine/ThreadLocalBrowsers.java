package webdriverrunner.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
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
    
    private final Lock shutdownReadLock, shutdownWriteLock;
    
    private volatile boolean shutdown = false;

    public ThreadLocalBrowsers(WebDriverFactory webDriverFactory) {
        this.webDriverFactory = webDriverFactory;
        browsersEverCreated = new ArrayList<WebDriverBrowser>();
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        shutdownReadLock = rwLock.readLock();
        shutdownWriteLock = rwLock.writeLock();
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
        shutdownReadLock.lock();
        try {
            if (shutdown) {
                throw new RuntimeException("ThreadLocalBrowsers is closed already.");
            }
            return super.get();
        } finally {
            shutdownReadLock.unlock();
        }
    }

    public void shutdown() {
        shutdownWriteLock.lock();
        try {
            shutdown = true;
            for (WebDriverBrowser browser : browsersEverCreated) {
                browser.exitQuietly();
            }
            browsersEverCreated.clear();
        } finally {
            shutdownWriteLock.unlock();
        }
    }
}
