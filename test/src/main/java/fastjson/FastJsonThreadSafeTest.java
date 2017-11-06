package fastjson;

/**
 * filename: FastJsonThreadSafeTest
 * Description:
 * Author: ubuntu
 * Date: 11/3/17 5:16 PM
 */
public class FastJsonThreadSafeTest {
    private static User json = new User(5, "aa");

    public static void test(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                json.add(5);
                ThreadTest.get(json);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                json.add(18);
                ThreadTest.get(json);
            }
        }).start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        for( int i = 0; i< 10000; i++) {
            test();
        }
    }
}
