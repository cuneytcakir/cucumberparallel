package Methods;

import StepDefinitions.BaseTest;
import com.as.helper.ElementHelper;
import com.as.helper.StoreHelper;
import com.as.model.ElementInfo;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class BaseMethods {

    private WebDriver driver = BaseTest.getDriver();

    private WebElement findElement(String key) {
        try {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            WebDriverWait webDriverWait = new WebDriverWait(driver, 3, 500);
            WebElement webElement = webDriverWait
                    .until(ExpectedConditions.presenceOfElementLocated(infoParam));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    webElement);
            return webElement;

        } catch (TimeoutException e) {
            Assert.fail("Verilen Sürede Aranan Eleman Oluşmamıştır...");
            return null;
        }
    }
    private List<WebElement> findElements(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    public String getConfigValue(String Key) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "config.properties";
        File configFile = new File(appConfigPath);
        String value = null;

        {
            try {
                FileReader reader = new FileReader(configFile);
                Properties props = new Properties();
                props.load(reader);

                value = props.getProperty(Key);

                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }



    private void clickjavaScriptClicker(WebElement element) {
        javaScriptClicker(driver, element);
    }

    public void goToPage(String url) {
        driver.get(url);
    }

    public void hoverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }


    protected void scrollTo(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d, %d);", x, y);
        executeJS(jsStmt, true);

    }

    protected Object executeJS(String jsStmt, boolean wait) {
        return wait ? getJSExecutor().executeScript(jsStmt, "")
                : getJSExecutor().executeAsyncScript(jsStmt, "");
    }

    protected JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    public Object getRandomContent(List<?> contentList) {
        Random rand = new Random();
        int n = rand.nextInt(contentList.size());
        return contentList.get(n);
    }

    public void scrollToElement(WebElement element) {

        if (element != null) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY() - 350);
        }
    }

    public void javaScriptClicker(WebDriver driver, WebElement element) {

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);", element);
    }

    public void waitBySeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void hover_Element(String key) {
        WebElement element = findElement(key);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void waitByMilliSeconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void elementinYuklenmesiniBekle(String key, int Int, String message) {
        try {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            WebDriverWait webDriverWait = new WebDriverWait(driver, Int, 300);
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(infoParam));
            System.out.println("Verilen Sürede Eleman Oluşmuştur...");

        } catch (TimeoutException e) {
            Assert.fail(message);
        }
    }

    public void clickIntKey(int INT, String css) {

        try {
            for (int i = 0; i < INT; i++) {
                WebElement element = findElement(css);
                clickjavaScriptClicker(element);
                System.out.println((i + 1) + " kere tıklandı");
                waitBySeconds(1);
            }
        } catch (Exception e) {
            System.out.println("Tıklanılacak eleman kalmadı");
        }
    }

    public void clickElement(String key) {
        WebElement element = findElement(key);
        scrollToElement(element);
        hoverElement(element);
        waitByMilliSeconds(500);
        clickjavaScriptClicker(element);
    }

    public void sendText(String key, String text) {
        WebElement element = findElement(key);
        scrollToElement(element);
        hoverElement(element);
        waitByMilliSeconds(500);
        element.sendKeys(text);
    }

    public void NormalClick(String key) {
        WebElement element = findElement(key);
        scrollToElement(element);
        hoverElement(element);
        waitByMilliSeconds(500);
        element.click();
    }

    public void hardClick(String key) {
        WebElement element = findElement(key);
        waitByMilliSeconds(500);
        clickjavaScriptClicker(element);
    }

    public void focusNewTab() {
        ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(tabList.size() - 1));

    }

    public void focusBackTab() {
        ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(0));

    }
}
