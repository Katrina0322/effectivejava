package UtilTest;

/**
 * filename: JavassistUtilTest
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 4:40 PM
 */
public class JavassistUtilTest {
    public static void main(String[] args) {
        String[] a = new String[10];
        Integer b = 88;

        System.out.println(a.getClass().getTypeName());
        System.out.println(b.getClass().getTypeName());
    }
}
