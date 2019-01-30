package shopping.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;



//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "shirt_ref")
public class ShirtRef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name =  "shirt_ref_id")
    private Integer shirtRefId;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "The name of shirt", required = true)
    @Column(name =  "shirt_name")
    private String shirtName;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "The size of shirt", required = true)
    @Column(name =  "size")
    private String shirtSize;

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "The style of shirt", required = true)
    @Column(name =  "style")
    private String shirtStyle;

    public ShirtRef() {}

    public ShirtRef(String shirtName, String shirtSize, String shirtStyle) {
        this(null, shirtName, shirtSize, shirtStyle);
    }

    public ShirtRef(Integer shirtRefId, String shirtName, String shirtSize, String shirtStyle) {
        this.shirtRefId = shirtRefId;
        this.shirtName = shirtName;
        this.shirtSize = shirtSize;
        this.shirtStyle = shirtStyle;
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

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    public String getShirtStyle() {
        return shirtStyle;
    }

    public void setShirtStyle(String shirtStyle) {
        this.shirtStyle = shirtStyle;
    }
}
