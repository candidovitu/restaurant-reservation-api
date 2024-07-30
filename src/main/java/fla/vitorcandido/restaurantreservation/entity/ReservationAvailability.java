package fla.vitorcandido.restaurantreservation.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationAvailability {
    private Date date;
    private boolean available;
}
