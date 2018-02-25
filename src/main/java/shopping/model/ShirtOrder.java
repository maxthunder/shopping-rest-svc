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
	@ApiModelProperty(notes = "The name of the shirt", required = true)
	@Column(name = "shirt_name")
	private String shirtName;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "The size of the shirt (S,M,L,...)", required = true)
	@Column(name = "size")
	private String size;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "The shirtOrderId of the customer", required = true)
	@Column(name = "customer_id")
	private Integer customerId;

	public Integer getShirtOrderId() {
		return this.shirtOrderId;
	}
	public void setShirtOrderId(Integer shirtOrderId) {
		this.shirtOrderId = shirtOrderId;
	}

	public String getShirtName() {
		return this.shirtName;
	}
	public void setShirtName(String shirtName) {
		this.shirtName = shirtName;
	}

	public String getSize() {
		return this.size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "ShirtOrder{" +
				"shirtOrderId=" + shirtOrderId +
				", shirtName='" + shirtName + '\'' +
				", size='" + size + '\'' +
				", customerId=" + customerId +
				'}';
	}
}
