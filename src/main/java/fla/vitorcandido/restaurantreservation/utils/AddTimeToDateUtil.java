package fla.vitorcandido.restaurantreservation.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class AddTimeToDateUtil {

    public static Date addTimeToDate(Date date, String time) {
        String[] splitTime = time.split(":");

        int timeHours = Integer.parseInt(splitTime[0]);
        int timeMinutes = Integer.parseInt(splitTime[1]);

        ZoneId systemZoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = date.toInstant()
                .atZone(systemZoneId)
                .toLocalDateTime();

        return Date.from(
                localDateTime
                        .plusHours(timeHours)
                        .plusMinutes(timeMinutes)
                        .atZone(systemZoneId)
                        .toInstant()
        );
    }

}
