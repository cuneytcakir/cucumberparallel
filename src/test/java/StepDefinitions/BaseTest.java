package StepDefinitions;

import Base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.net.URL;
import java.util.Map;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.config.DriverManagerType.FIREFOX;

public class BaseTest extends BaseClass {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static final String USERNAME = "cuneytcakir";
    public static final String ACCESS_KEY = "f9203b66e5954a0a0f891b55017bd688";
    public static final String KEY = USERNAME + ":" + ACCESS_KEY;
    public static final String Test_URL = "http://hub.testinium.io/wd/hub";
    public static final String Grid_URL = "http://192.168.255.126:4444";
    public static final String Selenoid_URL = "http://192.168.255.126:4444/wd/hub";

    public static String run_type = "selenoid";
    public static String browser_name = "chrome";

    @Before
    public void setUp() throws Exception {

        if (run_type == "local") {
            if (browser_name == "chrome") {
                WebDriverManager.getInstance(CHROME).setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
            }
            else if (browser_name == "firefox") {
                WebDriverManager.getInstance(FIREFOX).setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
        }
        else if (run_type == "testinium") {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("key", KEY);

            capabilities.setCapability(CapabilityType.PLATFORM, "WIN10");
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
            capabilities.setCapability(CapabilityType.VERSION, "LATEST");
            capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            capabilities.setCapability("recordsVideo", true);
            capabilities.setCapability("screenResolution", "SXGA");

            driver = new RemoteWebDriver(new URL(Test_URL), capabilities);
        }
        else if (run_type == "grid") {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setPlatform(Platform.ANY);
            capabilities.setBrowserName(browser_name);
            tlDriver.set(new RemoteWebDriver(new URL(Grid_URL), capabilities));
        }
        else if (run_type == "selenoid") {
            if (browser_name == "chrome") {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", browser_name);
                capabilities.setCapability("browserVersion", "83.0");
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableLog", true,
                        "enableVNC", true,
                        "enableVideo", false
                ));
                tlDriver.set(new RemoteWebDriver(new URL(Selenoid_URL), capabilities));
            }
            else if (browser_name == "firefox") {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", browser_name);
                capabilities.setCapability("browserVersion", "88.0");
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableLog", true,
                        "enableVNC", true,
                        "enableVideo", true
                ));
                tlDriver.set(new RemoteWebDriver(new URL(Selenoid_URL), capabilities));
            }
        }
    }

    @After(order = 0)
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) tlDriver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }

    @After(order = 1)
    public void quitBrowser() {
        if (tlDriver != null) {
            getDriver().quit();
        }
    }

        /*
    @After(order = 0)
    public void closeBrowser() {
        if (driver != null) {
            driver.close();
        }
    }*/

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        BaseTest.driver = driver;
    }
}
