package commonuse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * used to
 * Created by tianjin on 6/27/16.
 */
public class CalendarTest {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        Calendar now1 = Calendar.getInstance();

        Calendar now2 = new GregorianCalendar();

        now1.setTime(df.parse("2016-06-27 14:05:00"));


//        System.out.println(df.format(now1.getTime()));
//
//        System.out.println(df.format(now2.getTime()));
//
//        System.out.println(now1.get(Calendar.MINUTE));
//
//        System.out.println(now2.get(Calendar.MINUTE));

        int minute = now2.get(Calendar.MINUTE);

        now2.set(Calendar.MINUTE,minute / 5 * 5);

        System.out.println(df.format(now2.getTime()));

        System.out.println(df.format(now1.getTime()));

    }
}
