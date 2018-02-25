package shopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("customer")
public class CustomerInfo {

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "ID of customer", required = true)
	private Integer customerId;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "Name of customer", required = true)
	private String customerName;

	public CustomerInfo(Integer customerId, String customerName) {
		this.customerId = customerId;
		this.customerName = customerName;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
