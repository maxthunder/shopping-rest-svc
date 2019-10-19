package shopping.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("CartOrderInfo")
@Data
public class CartOrderInfo {
    private Integer customerId;
    private String address;
    private List<Integer> productIds;
}
