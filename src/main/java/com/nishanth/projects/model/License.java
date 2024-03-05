package com.nishanth.projects.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
public class License extends RepresentationModel<License> {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "organizationInfo")
    private String organizationInfo;
    private String productName;
    private String licenseType;
    private String organizationName;

}
