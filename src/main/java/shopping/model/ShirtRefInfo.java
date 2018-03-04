package shopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class ShirtRefInfo {

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "ID of shirt ref", required = true)
	private Integer shirtRefId;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "Name of shirt ref", required = true)
	private String shirtName;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "Size of shirt ref", required = true)
	private String size;

	@JsonProperty(required = true)
	@ApiModelProperty(notes = "Style of shirt ref", required = true)
	private String style;

	public ShirtRefInfo(Integer shirtRefId, String shirtName, String size, String style) {
		this.shirtRefId = shirtRefId;
		this.shirtName = shirtName;
		this.size = size;
		this.style = style;
	}

	public Integer getShirtRefId() {
		return shirtRefId;
	}
	public void setShirtRefId(Integer shirtRefId) {
		this.shirtRefId = shirtRefId;
	}

	public String getShirtName() {
		return shirtName;
	}
	public void setShirtName(String shirtName) {
		this.shirtName = shirtName;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public String toString() {
		return "ShirtRefInfo{" +
				"shirtRefId=" + shirtRefId +
				", shirtName='" + shirtName + '\'' +
				", size='" + size + '\'' +
				", style='" + style + '\'' +
				'}';
	}
}
