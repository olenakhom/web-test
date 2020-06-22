package model;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Test environment enum
 *
 * @author KuK QA
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TestEnvironment {

    INT("int"), QA("qa"), LOCAL("local"), STAGING("staging"), PRODUCTION("prod"), INT_DELTA("int_delta"),
    INT_EPSILON("int_epsilon"), INT_ALPHA("int_alpha");

    private String env;

    private static final Logger LOGGER = LogManager.getLogger(TestEnvironment.class);

    /**
     * <p>returns the Environment by given system property "Env"</p>
     *
     * @return environment by system property, if not found Environment.LOCAL, never null
     */
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
