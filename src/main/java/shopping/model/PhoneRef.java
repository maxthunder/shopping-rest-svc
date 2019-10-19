package shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRef implements Serializable {
    private Integer phoneRefId;
    private String name;
    private Double price;
    private String description;
    private Integer CartOrderId;

    public PhoneRef(String name) {
        this.name = name;
    }
}
