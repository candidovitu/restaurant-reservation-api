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
    @Pattern(regexp = "^\\+\\d{1,3}\\s?\\(?\\d{1,4}\\)?\\s?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$")
    public String phone;

    @NotNull()
    public Date date;
}
