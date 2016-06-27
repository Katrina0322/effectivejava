package commonuse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * used to
 * Created by tianjin on 6/16/16.
 */
public class DateTest {

    public static void main(String[] args) throws ParseException {
        double a = 1463999309.7442591;
        Date date = new Date((long)a*1000);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat esFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String ss = "1464775612.368301000";
        String s = "2016-05-22 15:00";
        String s1 = "2016-05-22 15:03";

        System.out.println(esFormat.format((long) Double.parseDouble("1464775560000")));

        System.out.println(format.format((long) Double.parseDouble(ss) * 1000));

        System.out.println(esFormat.parse("2016-05-22 15:00:05"));
        System.out.println(date);
    }
}
