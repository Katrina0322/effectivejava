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
            int a = user.getMoney();

            UserAdd userAdd = new UserAdd();


            int b = user.getMoney();
//        System.out.println(a + "--------" + b);


            if(a != b){
                System.out.println(a + "+++++++++" + b);
            }
        }
    }
}
