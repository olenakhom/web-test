package api.src.junit;

import static common.utils.PropertiesUtil.loadAllProperties;

import common.model.TestEnvironment;
import lombok.Synchronized;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ui.src.junit.extensions.WebTestContext;

public class CustomApiTestContext implements BeforeEachCallback, AfterEachCallback, BeforeAllCallback {

    private static final Logger LOGGER = LogManager.getLogger(WebTestContext.class);

    @Synchronized
    private static void beforeTestSuite() {
        LOGGER.info("beforeTestSuite: Test suite started");
        loadAllProperties("kuk", TestEnvironment.PRODUCTION);
    }

    @Synchronized
    private static void afterTestSuite() {
        LOGGER.info("afterTestSuite: Test suite finished");
    }

    @Synchronized
    private static void beforeEachTest(final ExtensionContext context) {
        LOGGER.info("beforeEachTest: test started \"{}\" from class \"{}\"", context.getDisplayName(), context.getTestClass().get().getName());
    }

    @Synchronized
    private static void afterEachTest(final ExtensionContext context) {
        LOGGER.info("afterEachTest: test finished \"{}\" from class \"{}\"", context.getDisplayName(),
            context.getTestClass().get().getName());
    }

    // <editor-fold desc="JUnit5 Hooks">

    private static boolean started = false;

    @Override
    @Synchronized
    public void beforeAll(ExtensionContext context) {
        LOGGER.info("beforeAll: before all started in CustomApiTestContext and invoked by test \"{}\" from class \"{}\"",
            context.getDisplayName(), context.getTestClass().get().getName());
        if (!started) {
            started = true;

            // Trick to be able to run code after test suite
            // more info: https://github.com/junit-team/junit5/pull/19
            context.getRoot().getStore(ExtensionContext.Namespace.GLOBAL)
                .getOrComputeIfAbsent(AfterTestSuiteSupport.class);

            beforeTestSuite();
        }
    }

    @Override
    @Synchronized
    public void beforeEach(ExtensionContext context) {
        beforeEachTest(context);
    }

    @Override
    @Synchronized
    public void afterEach(ExtensionContext context) {
        afterEachTest(context);
    }

    static class AfterTestSuiteSupport implements ExtensionContext.Store.CloseableResource {

        @Override
        @Synchronized
        public void close() {
            afterTestSuite();
        }
    }

    // </editor-fold>
}

