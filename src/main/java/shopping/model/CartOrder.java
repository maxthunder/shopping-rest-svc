package shopping.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Entity
@Table(name = "cart_order")
@JsonIgnoreProperties
@CrossOrigin
public class CartOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name =  "cart_order_id")
    private Integer cartOrderId;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "Shipping address", required = true)
    @Column(name =  "address")
    @NotNull
    private String address;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "The id of the customer", required = true)
    @Column(name =  "customer_id")
    @NotNull
    private Integer customerId;

    public CartOrder() {}

    public CartOrder(String address, Integer customerId) {
        this.address = address;
        this.customerId = customerId;
    }

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
                "cartOrderId=" + cartOrderId +
                ", address='" + address + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
