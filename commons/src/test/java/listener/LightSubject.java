package listener;

import com.study.listener.AbstractSubject;

/**
 * filename: LightSubject
 * Description:
 * Author: ubuntu
 * Date: 12/8/17 5:37 PM
 */
public class LightSubject extends AbstractSubject {
    @Override
    public void doSomeThing() {
        System.out.println("aaa");
    }
}
