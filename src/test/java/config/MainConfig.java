package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.SimpleThreadScope;

@Configuration
@PropertySource({"classpath:${propFile}"})

public class MainConfig {

    private static final Logger LOGGER = LogManager.getLogger(MainConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        final CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.addScope("thread", new SimpleThreadScope());
        LOGGER.info("Main Config Set ThreadScope = {}", configurer);
        return configurer;
    }

    @Bean(name = "dataSourceProperty")
    public DataSourceProperty getDataSourceProperty(){
        return new DataSourceProperty();
    }

/*    @Bean(name = "driver")
    @Scope("thread")
    public WebDriver getWebDriver(){
        WebDriverManager.init();
        LOGGER.info("Main Config Get Driver = {}", WebDriverManager.getWebDriver());
        return WebDriverManager.getWebDriver();
    }*/

   /* @Bean(name = "wait")
    @Scope("thread")
    public WebDriverWait getWebDriverWait(@Qualifier("driver") WebDriver driver){
        return new WebDriverWait(driver, 10, 50);
    }
*/
}
