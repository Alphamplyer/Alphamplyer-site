package utils.validation.date;

import utils.objects.Tuple;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Tuple<Boolean, Date> isValid(String str_date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(str_date);
            return new Tuple<>(true, date);
        } catch(Exception e) {
            return new Tuple<>(false, null);
        }
    }
}
