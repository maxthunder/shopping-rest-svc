package shopping.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("CartOrderInfo")
@Data
public class CartOrderInfo {
    private String address;
    private Integer customerId;
    private List<Integer> productIds;
}
