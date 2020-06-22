package ui;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import static ui.TestPlatform.LINUX;
import static ui.TestPlatform.LOCAL;
import static ui.TestPlatform.MAC;
import static ui.TestPlatform.SELENOID;
import static ui.TestPlatform.WINDOWS;
import static ui.TestPlatform.getBySystemProperty;

public final class WebDriverManager {

    private static final Logger LOGGER = LogManager.getLogger(WebDriverManager.class);

    private WebDriverManager() {
    }

    public static void init() {
        LOGGER.info("init: init web driver");
        Configuration.timeout = 8000;
        Configuration.pollingInterval = 1000;
        Configuration.screenshots = false;
        Configuration.startMaximized = true;
        Configuration.pageLoadStrategy = "normal";
        setBrowser();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        setCommonCapabilities(capabilities);
        setRemoteConfigs(capabilities);

        Configuration.browserCapabilities = capabilities;
    }

    public static WebDriver getWebDriver() {
        WebDriver driver = WebDriverRunner.getAndCheckWebDriver();
        LOGGER.info("getWebDriver: get and check web driver {}", driver);
        return driver;
    }

    public static void deleteAllCookies() {
        LOGGER.info("deleteAllCookies: Delete all browser cookies");
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.clearBrowserCache();
        }
    }

    public static void closeWebDriver() {
        LOGGER.info("closeWebDriver: Close web driver");
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }

    private static void setBrowser() {
        String currentBrowser = System.getProperty("browser");
        Configuration.browser = Browsers.CHROME;
    }

    private static void setCommonCapabilities(DesiredCapabilities capabilities) {
        String currentBrowser = System.getProperty("browser");
        if (currentBrowser != null && currentBrowser.equalsIgnoreCase("safari")) {
            capabilities.setAcceptInsecureCerts(false);
        }
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
    }

    private static void setRemoteConfigs(DesiredCapabilities capabilities) {
        LOGGER.info("setRemoteConfigs: set web driver remote configurations");
        String platform = System.getProperty("platform");
        String env = System.getProperty("env");
        String remoteSeleniumHub = System.getProperty("nodeURL");

        if (platform == null || platform.isEmpty()) {
            Configuration.remote = remoteSeleniumHub;
            return;
        }

       // boolean isEnvironmentLocal = TestEnvironment.LOCAL.equals(TestEnvironment.getBySystemProperty(env));
        TestPlatform currentPlatform = getBySystemProperty(platform);

        if (currentPlatform.equals(SELENOID)) {
            capabilities.setPlatform(Platform.LINUX);
            Configuration.remote = System.getProperty("nodeURL_selenoid");
            return;
        }
        if (currentPlatform.equals(LOCAL)) {
            //Configuration.remote = System.getProperty("nodeURL_LOCAL");
            Configuration.remote = "http://localhost:4444/wd/hub";
            return;
        }
        if (currentPlatform.equals(MAC)) {
            capabilities.setPlatform(Platform.MAC);
            Configuration.remote = remoteSeleniumHub;
            return;
        }

        if (currentPlatform.equals(WINDOWS)) {
            capabilities.setPlatform(Platform.WIN10);
            Configuration.remote = remoteSeleniumHub;
            return;
        }
        if (currentPlatform.equals(LINUX)) {
            capabilities.setPlatform(Platform.LINUX);
            Configuration.remote = remoteSeleniumHub;
            return;
        }
    }

}

