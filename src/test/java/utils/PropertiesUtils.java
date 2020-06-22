package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import model.TestEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Common property Loader Util class
 *
 * @author KuK QA
 */
public final class PropertiesUtils {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesUtils.class);

    private PropertiesUtils() {
    }

    public static void loadAllProperties(String productCode, TestEnvironment defaultTestEnvironment) {
        //loadCommonProperties();
        loadEnvProperties(productCode, defaultTestEnvironment);
    }

    /**
     * Load env properties
     */
    public static void loadEnvProperties(String productCode, TestEnvironment defaultTestEnvironment) {
        TestEnvironment env = TestEnvironment.getBySystemProperty(defaultTestEnvironment);
        String envValue = env.getEnv();
        setSystemProperty("env", envValue);

        InputStream envProperties =
            PropertiesUtils.class.getClassLoader().getResourceAsStream("properties/" + envValue + "." + productCode + ".properties");

        loadPropertiesFromFiles(Arrays.asList(envProperties));
    }

    /**
     * Load common properties
     */
    public static void loadCommonProperties() {
        InputStream commonProperties = PropertiesUtils.class.getClassLoader().getResourceAsStream("properties/config.properties");
        loadPropertiesFromFiles(Arrays.asList(commonProperties));
    }

    /**
     * Load webdriver properties
     */
    public static void loadWebdriverProperties() {
        InputStream commonProperties = PropertiesUtils.class.getClassLoader().getResourceAsStream("properties/selenium-config.properties");
        loadPropertiesFromFiles(Arrays.asList(commonProperties));
    }

    private static void setSystemProperty(String key, String value) {
        System.setProperty(key, value);
        LOGGER.info("loadProperties: system property with key \"{}\" was set with value \"{}\"", key, value);
    }

    private static void loadPropertiesFromFiles(List<InputStream> propertyFiles) {
        Properties properties = new Properties();
        try {
            for (InputStream propertyFile : propertyFiles) {
                properties.load(propertyFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("loadPropertiesFromFiles: Loading properties failed");
        }
        setSystemProperties(properties);
    }

    private static void setSystemProperties(Properties properties) {
        Enumeration e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            setSystemProperty(key, value);
        }
    }
}
