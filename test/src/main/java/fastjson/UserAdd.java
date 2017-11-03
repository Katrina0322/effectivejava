package fastjson;

import java.util.HashMap;

/**
 * filename: UserAdd
 * Description:
 * Author: ubuntu
 * Date: 11/3/17 6:21 PM
 */
public class UserAdd {
    public UserAdd() {
    }

    public void add(User user){
        user.setMoney(user.getMoney() + 1);
    }
}
