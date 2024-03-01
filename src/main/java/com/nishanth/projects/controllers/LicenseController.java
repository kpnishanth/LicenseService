package com.nishanth.projects.controllers;

import com.nishanth.projects.model.License;
import com.nishanth.projects.services.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.Locale;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable(name = "licenseId") String licenseId, @PathVariable(name = "organizationId") String organizationId) {

        License license = licenseService.getLicense(licenseId, organizationId);
        license.add(
                linkTo(methodOn(LicenseController.class)
                        .getLicense(licenseId, organizationId)).withSelfRel());
        license.add(linkTo(methodOn(LicenseController.class).createLicense(license,organizationId,null)).withRel("CReate License"));
        license.add(linkTo(methodOn(LicenseController.class).updateLicense(license,organizationId,null)).withRel("Update License"));
        return ResponseEntity.ok(license);

    }

    @PostMapping()

    public ResponseEntity<String> createLicense(@RequestBody License license,
                                                @PathVariable String organizationId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {

        String license1 = licenseService.createLicense(license, organizationId, locale);

        return ResponseEntity.ok(license1);

    }

    @PutMapping()
    public ResponseEntity<String> updateLicense(@RequestBody License license,
                                                @PathVariable String organizationId,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) {

        return ResponseEntity.ok(licenseService.updateLicense(license, organizationId, locale));

    }
}
