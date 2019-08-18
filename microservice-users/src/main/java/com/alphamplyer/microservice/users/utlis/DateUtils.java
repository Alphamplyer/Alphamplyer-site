package com.alphamplyer.microservice.users.utlis;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateUtils {

    public static Timestamp calculateExpiryDateTime(int expiryTimeInMinute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTimeInMillis()));
        calendar.add(Calendar.MINUTE, expiryTimeInMinute);
        return new Timestamp(calendar.getTimeInMillis());
    }

}
