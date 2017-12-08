package SPI;

/**
 * @filename: JavaHello
 * @Description:
 * @Author: ubuntu
 * @Date: 12/14/16 3:14 PM
 */
public class JavaHello implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello：Java");
    }
}
