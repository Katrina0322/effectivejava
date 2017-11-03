package fastjson;



/**
 * filename: ThreadTest
 * Description:
 * Author: ubuntu
 * Date: 11/3/17 6:17 PM
 */
public class ThreadTest {
    public static void get(User user){
        synchronized(user) {
            UserAdd userAdd = new UserAdd();
            int a = user.getMoney();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            int b = user.getMoney();
//        System.out.println(a + "--------" + b);


            if(a != b){
                System.out.println(a + "+++++++++" + b);
            }
        }
    }
}
