package shopping.model;

public class ShirtRefBuilder {
    private  Integer shirtRefId;
    private String shirtName;
    private  String shirtSize;
    private String shirtStyle;

    public ShirtRefBuilder() {
    }


    public ShirtRefBuilder id(Integer shirtRefId){
        this.shirtRefId = shirtRefId;
        return this;
    }


    public ShirtRefBuilder shirtName(String shirtName){
        this.shirtName = shirtName;
        return this;
    }

    public ShirtRefBuilder size(String shirtSize){
        this.shirtSize = shirtSize;
        return this;
    }

    public ShirtRefBuilder style(String shirtStyle){
        this.shirtStyle = shirtStyle;
        return this;
    }

    public ShirtRef build() {
        return new ShirtRef(shirtRefId, shirtName, shirtSize, shirtStyle);
    }
}
