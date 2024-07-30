package fla.vitorcandido.restaurantreservation.utils;

import java.util.Calendar;
import java.util.Date;

public class MergeDateWithTimeUtil {

    public static Date mergeDateWithTime(Date date, String time) {
        String[] splitTime = time.split(":");

        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        return calendar.getTime();
    }

}
