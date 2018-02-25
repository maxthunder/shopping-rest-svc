package shopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class ShirtOrderInfo {

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "The id of the shirt order", required = true)
	private Integer shirtOrderId;

	@JsonProperty(value = "customer", required = true)
	@ApiModelProperty(notes = "Customer of shirt order", required = true)
	private CustomerInfo customerInfo;

	@JsonProperty(value = "shirtRef", required = true)
	@ApiModelProperty(notes = "Shirt ref of shirt order", required = true)
	private ShirtRefInfo shirtRefInfo;

	public ShirtOrderInfo() {}

	public ShirtOrderInfo(Integer shirtOrderId, CustomerInfo customerInfo, ShirtRefInfo shirtRefInfo) {
		this.shirtOrderId = shirtOrderId;
		this.customerInfo = customerInfo;
		this.shirtRefInfo = shirtRefInfo;
	}

	public Integer getShirtOrderId() {
		return shirtOrderId;
	}
	public void setShirtOrderId(Integer shirtOrderId) {
		this.shirtOrderId = shirtOrderId;
	}

	public CustomerInfo getCustomer() {
		return customerInfo;
	}
	public void setCustomer(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public ShirtRefInfo getShirtRefInfo() {
		return shirtRefInfo;
	}
	public void setShirtRefInfo(ShirtRefInfo shirtRefInfo) {
		this.shirtRefInfo = shirtRefInfo;
	}

	@Override
	public String toString() {
		return "ShirtOrderInfo{" +
				"shirtOrderId=" + shirtOrderId +
				", customerInfo=" + customerInfo +
				", shirtRefInfo=" + shirtRefInfo +
				'}';
	}
}
