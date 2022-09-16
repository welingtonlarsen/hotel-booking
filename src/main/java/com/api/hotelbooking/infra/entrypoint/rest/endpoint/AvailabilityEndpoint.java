package com.api.hotelbooking.infra.entrypoint.rest.endpoint;

import com.api.hotelbooking.application.dto.AvailabilityDTO;
import com.api.hotelbooking.application.usecase.CheckAvailability;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/availability")
public class AvailabilityEndpoint {

  private CheckAvailability checkAvailability;

  public AvailabilityEndpoint(CheckAvailability checkAvailability) {
    this.checkAvailability = checkAvailability;
  }

  @GetMapping
  public ResponseEntity<AvailabilityDTO> checkAvailability(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate until) {
    log.info("Checking availability for desired dates");
    var availability = checkAvailability.execute(from, until);
    return ResponseEntity.ok(availability);
  }
}
