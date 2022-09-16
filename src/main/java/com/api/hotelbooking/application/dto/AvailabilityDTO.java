package com.api.hotelbooking.application.dto;

import java.time.LocalDate;
import java.util.List;

public record AvailabilityDTO(List<LocalDate> available, List<LocalDate> unavailable) {

}
