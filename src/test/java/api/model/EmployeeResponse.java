package api.model;

import java.util.List;
import lombok.Data;

@Data
public class EmployeeResponse {

    private String status;
    private Employee data;

}
