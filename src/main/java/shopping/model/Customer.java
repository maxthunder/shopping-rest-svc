package shopping.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "customer")
@JsonIgnoreProperties
@CrossOrigin
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name =  "customer_id")
	private Integer customerId;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "The name of the customer", required = true)
	@Column(name =  "customer_name")
	@NotNull
	private String customerName;

	// REVIEW: Add customer status to prevent customer deletion unless in 'deleable' status 

	public Customer() {}

	public Customer(String customerName) {
		this(null, customerName);
	}

	public Customer(Integer customerId, String customerName) {
		this.customerId = customerId;
		this.customerName = customerName;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerId=" + customerId +
				", customerName='" + customerName + '\'' +
				'}';
	}
}
