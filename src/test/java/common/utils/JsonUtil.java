package common.utils;

import api.src.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import ui.src.model.ProductDetails;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class JsonUtil {

    private JsonUtil() {}

    private final static String BASE_PATH_TO_TEST_DATA = "testdata" + File.separatorChar;

    public static List<ProductDetails> readFromProductDetailsJson(String fileName) {
        File file = getFileFromResource(BASE_PATH_TO_TEST_DATA + fileName);
        return Arrays.asList(mapObject(file, ProductDetails[].class));
    }

    public static Employee readFromEmployeeJson(String fileName) {
        File file = getFileFromResource(BASE_PATH_TO_TEST_DATA + fileName);
        return mapObject(file, Employee.class);
    }

    public static File getFileFromResource(String pathToFile) {
        URL fileUrl = JsonUtil.class.getClassLoader().getResource(pathToFile);
        return new File((fileUrl).getPath());
    }

    public static <T> T mapObject(File file, Class<T> valueType) {
        ObjectMapper objectMapper = new ObjectMapper();
        T objectToMap = null;
        try {
            objectToMap = objectMapper.readValue(file, valueType);
        } catch (IOException e) {
            throw new RuntimeException("Mapping failed from file " + file.getPath());
        }
        return objectToMap;
    }

}
