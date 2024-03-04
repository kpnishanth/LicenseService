package com.nishanth.projects.services;

import com.nishanth.projects.model.License;
import com.nishanth.projects.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {

    @Autowired
    MessageSource messageSource;

    @Autowired
    RestTemplate restTemplate;
    public License getLicense(String licenseId)  {

        License license = new License();
        license.setLicenseType("full");
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setDescription("Software Product");
        license.setProductName("UFT");
        license.setOrganizationId(getOrganization(licenseId).getBody().getOrganizationId());
        license.setOrganizationName(getOrganization(licenseId).getBody().getOrganizationName());
        return license;
    }

    public ResponseEntity<Organization> getOrganization(String licenseId)  {
        String url = "http://localhost:5555/organization/"+licenseId;
        ResponseEntity<Organization> organizationResponseEntity = restTemplate.getForEntity(url, Organization.class);
        return organizationResponseEntity;
    }

    public String createLicense(License license, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(getOrganization(license.getLicenseId()).getBody().getOrganizationId());
            responseMessage = String.format(messageSource.getMessage("license.create.message",null,locale),license.toString());
        }
        return responseMessage;
    }

    public String updateLicense(License license, Locale locale)  {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(getOrganization(license.getLicenseId()).getBody().getOrganizationId());
            responseMessage = String.format(messageSource.getMessage("license.update.message",null,locale), license.toString());
        }
        return responseMessage;
    }

    public String deleteLicense(String  licenseId) {
        String responseMessage = null;
        if (licenseId != null) {

            responseMessage = "This is Delete....License has been deleted successfully";
        }
        return responseMessage;
    }

}
