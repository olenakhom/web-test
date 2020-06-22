package ui.model;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ProductDetails {

    private String title;
    private BigDecimal price;
    private String availability;
    private List<String> colors;

}
