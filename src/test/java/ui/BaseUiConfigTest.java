package ui;

import config.DataSourceProperty;
import config.MainConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(classes = MainConfig.class, loader = AnnotationConfigContextLoader.class)
public abstract class BaseUiConfigTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected DataSourceProperty dataSourceProperty;

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10, 50);
        driver.get(dataSourceProperty.getSiteUrl());
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }

}
