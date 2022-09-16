package com.api.hotelbooking.infra.entrypoint.rest.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceBookingDTO {

  @NotBlank(message = "Guest name should not be blank!")
  private String guestName;

  @NotBlank(message = "Guest email should not be blank!")
  @Email(message = "Guest email is not valid!")
  private String guestEmail;

  @NotNull(message = "CurrentDay should be informed!")
  private LocalDate currentDay;

  @NotNull(message = "Checkin day should be informed!")
  private LocalDate checkin;

  @NotNull(message = "Checkout day should be informed!")
  private LocalDate checkout;
}
