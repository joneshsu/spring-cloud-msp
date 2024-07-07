package jones.practice.springcloudmsp.controller;

import java.util.Locale;
import jones.practice.springcloudmsp.model.License;
import jones.practice.springcloudmsp.service.LicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
@RequiredArgsConstructor
public class LicenseController {

  private final LicenseService licenseService;

  @GetMapping(value = "/{licenseId}")
  public ResponseEntity<License> getLicense(
      @PathVariable("organizationId") String organizationId,
      @PathVariable("licenseId") String licenseId) {
    License license = licenseService.getLicense(licenseId, organizationId);
    license.add(
        linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId()))
            .withSelfRel(),
        linkTo(methodOn(LicenseController.class).createLicense(organizationId, license, null))
            .withRel("createLicense"),
        linkTo(methodOn(LicenseController.class).updateLicense(organizationId, license))
            .withRel("updateLicense"),
        linkTo(methodOn(LicenseController.class).deleteLicense(organizationId, license.getLicenseId()))
            .withRel("deleteLicense"));
    return ResponseEntity.ok(license);
  }

  @PostMapping
  public ResponseEntity<String> createLicense(
      @PathVariable("organizationId") String organizationId,
      @RequestBody License license,
      @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
    return ResponseEntity.ok(licenseService.createLicense(license, organizationId, locale));
  }

  @PutMapping
  public ResponseEntity<String> updateLicense(
      @PathVariable("organizationId") String organizationId, @RequestBody License license) {
    return ResponseEntity.ok(licenseService.updateLicense(license, organizationId));
  }

  @DeleteMapping(value = "/{licenseId}")
  public ResponseEntity<String> deleteLicense(
      @PathVariable("organizationId") String organizationId,
      @PathVariable("licenseId") String licenseId) {
    return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
  }
}
