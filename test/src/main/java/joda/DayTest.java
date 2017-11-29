package joda;

import org.joda.time.DateTime;

/**
 * filename: DayTest
 * Description:
 * Author: ubuntu
 * Date: 11/29/17 5:04 PM
 */
public class DayTest {


    public static void main(String[] args) {
        DateTime dateTime = new DateTime(2017, 2, 1, 0, 0);
        System.out.println(dateTime.dayOfMonth().getMaximumValue());
    }
}
