package commonuse;

import java.util.Date;

/**
 * used to
 * Created by tianjin on 6/16/16.
 */
public class DateTest {

    public static void main(String[] args){
        double a = 1463999309.7442591;
        Date date = new Date((long)a*1000);
        System.out.println(date);
    }
}
