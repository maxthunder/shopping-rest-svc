package shopping.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "product")
@JsonIgnoreProperties
@CrossOrigin
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name =  "product_id")
	private Integer productId;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "Name of the product", required = true)
	@Column(name =  "name")
	@NotNull
	private String name;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "Price of the product", required = true)
	@Column(name =  "price")
	@NotNull
	private Double price;

	@JsonProperty
	@ApiModelProperty(notes = "Description of the product", required = true)
	@Column(name =  "description")
	@NotNull
	private String description;

	public Product(String name, Double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public Product() {}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
