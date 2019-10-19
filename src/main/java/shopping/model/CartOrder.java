package shopping.model;

import java.io.Serializable;

public class CartOrder implements Serializable {
    private Integer cartOrderId;
    private String address;
    private Integer customerId;

    public CartOrder() {}

    public Integer getCartOrderId() {
        return cartOrderId;
    }
    public void setCartOrderId(Integer cartOrderId) {
        this.cartOrderId = cartOrderId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CartOrder{" +
                "CartOrderId=" + cartOrderId +
                ", address='" + address + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
