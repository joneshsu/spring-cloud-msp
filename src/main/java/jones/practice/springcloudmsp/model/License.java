package jones.practice.springcloudmsp.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class License {

    private int id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}

