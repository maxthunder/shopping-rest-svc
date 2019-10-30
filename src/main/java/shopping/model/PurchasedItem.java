package shopping.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "purchased_item")
@JsonIgnoreProperties
@CrossOrigin
public class PurchasedItem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name =  "purchased_item_id")
	private Integer purchasedItemId;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "ID of cart order", required = true)
	@Column(name =  "cart_order_id")
	@NotNull
	private Integer cartOrderId;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "ID of product ref", required = true)
	@Column(name =  "product_ref_id")
	@NotNull
		private Integer productRefId;

	public PurchasedItem(Integer cartOrderId, Integer productRefId) {
		this.cartOrderId = cartOrderId;
		this.productRefId = productRefId;
	}

	public PurchasedItem() {}

	public Integer getPurchasedItemId() {
		return purchasedItemId;
	}

	public void setPurchasedItemId(Integer purchasedItemId) {
		this.purchasedItemId = purchasedItemId;
	}

	public Integer getCartOrderId() {
		return cartOrderId;
	}

	public void setCartOrderId(Integer cartOrderId) {
		this.cartOrderId = cartOrderId;
	}

	public Integer getProductRefId() {
		return productRefId;
	}

	public void setProductRefId(Integer productRefId) {
		this.productRefId = productRefId;
	}
}
