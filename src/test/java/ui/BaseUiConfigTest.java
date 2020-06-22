package ui;

import config.DataSourceProperty;
import config.MainConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(classes = MainConfig.class, loader = AnnotationConfigContextLoader.class)
public abstract class BaseUiConfigTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected DataSourceProperty dataSourceProperty;

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

/*
    @BeforeClass
    public static void setupClass() {
        WebDriverManager.init();
    }*/

    @BeforeMethod
    public void setUp() {
        //driver = WebDriverManager.getWebDriver();
        //wait = new WebDriverWait(driver, 10, 50);
        System.out.println("Data Source Property URL = " + dataSourceProperty.getSiteUrl());
        System.out.println("Driver = " + driver);
        System.out.println("Wait = " + wait);
        System.out.println("Current Thread = " + Thread.currentThread().getId());
        driver.get(dataSourceProperty.getSiteUrl());
    }

    @AfterMethod
    public void setDown() {
        if (driver != null) { WebDriverManager.closeWebDriver();
        }
    }

}
