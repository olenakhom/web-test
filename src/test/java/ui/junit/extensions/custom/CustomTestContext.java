package ui.junit.extensions.custom;


import lombok.Synchronized;
import common.model.TestEnvironment;

import org.junit.jupiter.api.extension.ExtensionContext;
import ui.junit.extensions.WebTestContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static common.utils.PropertiesUtil.loadAllProperties;

public class CustomTestContext extends WebTestContext {

    private static final Logger LOGGER = LogManager.getLogger(CustomTestContext.class);

    @Override
    @Synchronized
    public void beforeAll(ExtensionContext context) {
        loadAllProperties("kuk", TestEnvironment.PRODUCTION);
        super.beforeAll(context);
        //TestEnvironmentUtils.load();
    }
}
