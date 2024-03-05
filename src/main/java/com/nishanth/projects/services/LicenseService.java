package com.nishanth.projects.services;

import com.nishanth.projects.model.License;
import com.nishanth.projects.model.Organization;
import com.nishanth.projects.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Service
public class LicenseService {

    @Autowired
    MessageSource messageSource;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LicenseRepository repository;


    public License getLicense(Long id){
        return repository.findById(id).get();
    }
    public ResponseEntity<Organization> getOrganization(String licenseId)  {
        String url = "http://localhost:5555/organization/"+licenseId;
        ResponseEntity<Organization> organizationResponseEntity = restTemplate.getForEntity(url, Organization.class);
        return organizationResponseEntity;
    }

    public String createLicense(License license, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationInfo(getOrganization(license.getOrganizationInfo()).getBody().getId());
            responseMessage = String.format(messageSource.getMessage("license.create.message",null,locale),license.toString());
        }
        return responseMessage;
    }

    public String updateLicense(License license, Locale locale)  {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationInfo(getOrganization(license.getOrganizationInfo()).getBody().getId());
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
