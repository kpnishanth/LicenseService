package com.nishanth.projects.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class Organization extends RepresentationModel<Organization> {
    private String organizationId;
    private String organizationName;
    private String licenseId;
}
