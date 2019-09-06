package com.alphamplyer.website.administration.utils.validation.date;

import com.alphamplyer.website.administration.utils.objects.Tuple;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {

    public static Tuple<Boolean, Timestamp> isValid(String str_timestamp) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(str_timestamp);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

            return new Tuple<>(true, timestamp);
        } catch(Exception e) {
            return new Tuple<>(false, null);
        }
    }
}
