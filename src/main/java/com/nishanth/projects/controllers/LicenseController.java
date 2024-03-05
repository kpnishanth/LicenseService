package com.nishanth.projects.controllers;

import com.nishanth.projects.model.License;
import com.nishanth.projects.services.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.net.MalformedURLException;
import java.util.Locale;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/license")
public class LicenseController {

    @Autowired
    LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable(name = "licenseId") String licenseId) throws MalformedURLException {
        License license = licenseService.getLicense(Long.valueOf(licenseId));

        license.add(
                linkTo(methodOn(LicenseController.class)
                        .getLicense(licenseId)).withSelfRel());
        license.add(linkTo(methodOn(LicenseController.class).createLicense(license,null)).withRel("CReate License"));
        license.add(linkTo(methodOn(LicenseController.class).updateLicense(license, null)).withRel("Update License"));
        return ResponseEntity.ok(license);

    }

    @PostMapping()

    public ResponseEntity<String> createLicense(@RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) throws MalformedURLException {

        String license1 = licenseService.createLicense(license, locale);

        return ResponseEntity.ok(license1);

    }

    @PutMapping()
    public ResponseEntity<String> updateLicense(@RequestBody License license,
                                                @RequestHeader(value = "Accept-Language", required = false) Locale locale) throws MalformedURLException {

        return ResponseEntity.ok(licenseService.updateLicense(license, locale));

    }
}
