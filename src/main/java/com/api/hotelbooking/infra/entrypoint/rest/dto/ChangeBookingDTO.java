package com.api.hotelbooking.infra.entrypoint.rest.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// TODO: Verificar se é possível mudar para record
@Getter
@Setter
public class ChangeBookingDTO {

  @NotNull(message = "CurrentDay should be informed!")
  private LocalDate currentDay;

  private LocalDate checkin;

  private LocalDate checkout;
}
