package common.model;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TestEnvironment {

    QA("qa"), LOCAL("local"), PRODUCTION("prod");

    private String env;

    private static final Logger LOGGER = LogManager.getLogger(TestEnvironment.class);

    public static TestEnvironment getBySystemProperty(TestEnvironment defaultTestEnvironment) {
        String env = System.getProperty("env");
        TestEnvironment testEnvironmentReturn = defaultTestEnvironment;

        for (TestEnvironment testEnvironment : TestEnvironment.values()) {
            if (testEnvironment.getEnv().equalsIgnoreCase(env)) {
                testEnvironmentReturn = testEnvironment;
            }
        }
        LOGGER.info("getBySystemProperty: Test environment is {}", testEnvironmentReturn);
        return testEnvironmentReturn;
    }

    public static TestEnvironment getBySystemProperty(String env) {
        return Arrays.stream(TestEnvironment.values())
            .filter(item -> item.getEnv().equalsIgnoreCase(env))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("getBySystemProperty: Provided environment " + env + " does not exist!"));
    }

}
