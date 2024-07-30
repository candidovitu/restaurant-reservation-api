package fla.vitorcandido.restaurantreservation.dto.reservation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class CheckReservationAvailabilityDto {
    @NotBlank()
    public String restaurantId;

    @NotNull()
    public Date date;
}
