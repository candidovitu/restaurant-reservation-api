package fla.vitorcandido.restaurantreservation.dto.restaurant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateRestaurantDto {
    @NotBlank()
    @Size(min = 2, max = 64)
    public String name;

    @Size(min = 2, max = 32)
    @Pattern(regexp = "^[A-Za-z0-9\\-_+]+$")
    public String slug;

    @NotNull()
    public int tablesCount;

    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$")
    public String reservationDuration;

    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$")
    public String openTime;

    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$")
    public String closeTime;
}
