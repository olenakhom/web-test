package common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import ui.model.ProductDetails;

public class JsonUtil {

    private JsonUtil() {}

    public static List<ProductDetails> readFromProductDetailsJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        URL fileUrl = JsonUtil.class.getClassLoader().getResource("testdata/productdetails.json");
        File file = new File((fileUrl).getPath());
        ProductDetails[] productDetails = null;
        try {
            productDetails = objectMapper.readValue(file, ProductDetails[].class);
        } catch (IOException e) {
            new RuntimeException("Mapping failed from json file " + file.getPath());
        }
        return Arrays.asList(productDetails);
    }

}
