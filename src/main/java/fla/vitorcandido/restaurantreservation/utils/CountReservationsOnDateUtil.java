package fla.vitorcandido.restaurantreservation.utils;

import fla.vitorcandido.restaurantreservation.entity.Reservation;

import java.util.Date;
import java.util.List;

public class CountReservationsOnDateUtil {

    public static int countReservationsOnDateUtil(Date date, List<Reservation> reservations) {
        int reservationsCount = 0;

        for (Reservation reservation : reservations) {
            Date reservationStartDate = reservation.getStartDate();
            Date reservationEndDate = reservation.getEndDate();

            if (
                    date.equals(reservationStartDate) || (date.after(reservationStartDate) && date.before(reservationEndDate))
            )
                reservationsCount++;
        }

        return reservationsCount;
    }

}
