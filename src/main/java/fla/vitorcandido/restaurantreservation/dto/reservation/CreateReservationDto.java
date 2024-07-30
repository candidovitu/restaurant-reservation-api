package fla.vitorcandido.restaurantreservation.dto.reservation;

import jakarta.validation.constraints.*;

import java.util.Date;

public class CreateReservationDto {
    @NotBlank()
    public String restaurantId;

    @NotBlank()
    @Size(min = 2, max = 100)
    public String name;

    @NotBlank()
    @Size(min = 2, max = 100)
    // TODO: phone validation regex pattern
    public String phone;

    @NotNull()
    public Date date;
}
