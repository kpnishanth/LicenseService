package com.nishanth.projects.services;

import com.nishanth.projects.model.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {

    @Autowired
    MessageSource messageSource;
    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setLicenseType("full");
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setDescription("Software Product");
        license.setProductName("UFT");
        license.setOrganizationId(organizationId);
        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageSource.getMessage("license.create.message",null,locale),license.toString());
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId,Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageSource.getMessage("license.update.message",null,locale), license.toString());
        }
        return responseMessage;
    }

    public String deleteLicense(License license, String organizationId) {
        String responseMessage = null;
        if (license != null) {

            responseMessage = "This is Delete....License has been deleted successfully";
        }
        return responseMessage;
    }

}
