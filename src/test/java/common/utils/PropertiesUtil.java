package common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import common.model.TestEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PropertiesUtil {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {
    }

    public static void loadAllProperties(String productCode, TestEnvironment defaultTestEnvironment) {
        loadEnvProperties(productCode, defaultTestEnvironment);
    }

    public static void loadEnvProperties(String productCode, TestEnvironment defaultTestEnvironment) {
        TestEnvironment env = TestEnvironment.getBySystemProperty(defaultTestEnvironment);
        String envValue = env.getEnv();
        setSystemProperty("env", envValue);

        InputStream envProperties =
            PropertiesUtil.class.getClassLoader().getResourceAsStream("properties/" + envValue + "." + productCode + ".properties");

        loadPropertiesFromFiles(Arrays.asList(envProperties));
    }

    public static void loadWebdriverProperties() {
        InputStream commonProperties = PropertiesUtil.class.getClassLoader().getResourceAsStream("properties/selenium-config.properties");
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
