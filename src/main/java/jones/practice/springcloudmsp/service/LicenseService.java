package jones.practice.springcloudmsp.service;

import jones.practice.springcloudmsp.model.License;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenseService {

  private final MessageSource messageSource;

  public License getLicense(String licenseId, String organizationId) {
    License license = new License();
    license.setId(new Random().nextInt(1000));
    license.setLicenseId(licenseId);
    license.setOrganizationId(organizationId);
    license.setDescription("Software product");
    license.setProductName("Ostock");
    license.setLicenseType("full");

    return license;
  }

  public String createLicense(License license, String organizationId, Locale locale) {
    String responseMessage = null;
    if (license != null) {
      license.setOrganizationId(organizationId);
      responseMessage =
          String.format(
              messageSource.getMessage("license.create.message", null, locale), license.toString());
    }

    return responseMessage;
  }

  public String updateLicense(License license, String organizationId) {
    String responseMessage = null;
    if (license != null) {
      license.setOrganizationId(organizationId);
      responseMessage =
          String.format(
              messageSource.getMessage("license.update.message", null, null), license.toString());
    }

    return responseMessage;
  }

  public String deleteLicense(String licenseId, String organizationId) {
    String responseMessage = null;
    responseMessage =
        String.format(
            "Deleting license with id %s for the organization %s", licenseId, organizationId);

    return responseMessage;
  }
}
