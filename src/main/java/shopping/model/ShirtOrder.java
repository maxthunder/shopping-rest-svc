package shopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shirt_order")
public class ShirtOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shirt_order_id")
	private Integer shirtOrderId;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "The id of the customer", required = true)
	@Column(name = "customer_id")
	private Integer customerId;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "The id of the shirt ref", required = true)
	@Column(name = "shirt_ref_id")
	private Integer shirtRefId;

	public ShirtOrder() {}

	public ShirtOrder(Integer customerId, Integer shirtRefId) {
		this(null, customerId, shirtRefId);
	}

	public ShirtOrder(Integer shirtOrderId, Integer customerId, Integer shirtRefId) {
		this.shirtOrderId = shirtOrderId;
		this.customerId = customerId;
		this.shirtRefId = shirtRefId;
	}

	public Integer getShirtOrderId() {
		return this.shirtOrderId;
	}
	public void setShirtOrderId(Integer shirtOrderId) {
		this.shirtOrderId = shirtOrderId;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getShirtRefId() {
		return shirtRefId;
	}
	public void setShirtRefId(Integer shirtRefId) {
		this.shirtRefId = shirtRefId;
	}

	@Override
	public String toString() {
		return "ShirtOrder{" +
				"shirtOrderId=" + shirtOrderId +
				", customerId=" + customerId +
				", shirtRefId=" + shirtRefId +
				'}';
	}
}
