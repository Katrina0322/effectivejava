package fastjson;

/**
 * filename: User
 * Description:
 * Author: ubuntu
 * Date: 11/3/17 6:19 PM
 */
public class User {
    private int money;
    private String name;

    public User(int money, String name) {
        this.money = money;
        this.name = name;
    }

    public void add(int a){
        money += a;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (money != user.money) return false;
        return name != null ? name.equals(user.name) : user.name == null;

    }

    @Override
    public int hashCode() {
        int result = money;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "money=" + money +
                ", name='" + name + '\'' +
                '}';
    }
}
