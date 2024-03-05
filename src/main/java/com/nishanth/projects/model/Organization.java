package com.nishanth.projects.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Entity
public class Organization extends RepresentationModel<Organization> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String organizationName;
    private String licenseId;
}
