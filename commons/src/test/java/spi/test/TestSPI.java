package spi.test;

import com.oneapm.SPI.HelloService;

import java.util.ServiceLoader;

/**
 * @filename: TestSPI
 * @Description:
 * @Author: ubuntu
 * @Date: 12/14/16 3:17 PM
 */
public class TestSPI {
    public static void main(String[] args) {
        ServiceLoader<HelloService> services = ServiceLoader.load(HelloService.class);
        for(HelloService service:services){
            service.sayHello();
        }
    }
}
