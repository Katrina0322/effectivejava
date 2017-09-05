package concurrent;

/**
 * filename: Test
 * Description:
 * Author: ubuntu
 * Date: 9/5/17 3:11 PM
 */
public class Test {
    private int age;
    private String username;
    private String password;
    private boolean valid;

    public Test(int age, String username, String password, boolean valid) {
        this.age = age;
        this.username = username;
        this.password = password;
        this.valid = valid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
