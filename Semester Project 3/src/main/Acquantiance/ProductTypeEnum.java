package Acquantiance;

import java.util.HashMap;
import java.util.Map;

public enum ProductTypeEnum {
    PILSNER("Pilsner"),
    ALE("Ale"),
    WHEAT("Wheat"),
    STOUT("Stout"),
    IPA("Ipa"),
    ALCOHOLFREE("Alcohol Free");

    private String type;

    ProductTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    //****** Reverse Lookup Implementation************//

    //Lookup table
    private static final Map<String, ProductTypeEnum> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static {
        for(ProductTypeEnum productType : ProductTypeEnum.values()) {
            lookup.put(productType.getType(), productType);
        }
    }

    //This method can be used for reverse lookup purpose
    public static ProductTypeEnum get(String type) {
        return lookup.get(type);
    }




}
