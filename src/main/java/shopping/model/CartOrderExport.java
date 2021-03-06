package shopping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class CartOrderExport implements Serializable {
    private Integer cartOrderId;
    private String address;
    private Integer customerId;
    private List<PurchasedItem> purchasedItems;

    public CartOrderExport(CartOrder cartOrder, List<PurchasedItem> purchasedItems) {
        this.cartOrderId = cartOrder.getCartOrderId();
        this.address = cartOrder.getAddress();
        this.customerId = cartOrder.getCustomerId();
        this.purchasedItems = purchasedItems;
    }
}
