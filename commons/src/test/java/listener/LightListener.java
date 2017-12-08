package listener;

import com.study.listener.Listener;
import com.study.listener.Subject;

/**
 * filename: LightListener
 * Description:
 * Author: ubuntu
 * Date: 12/8/17 4:34 PM
 */
public class LightListener<T extends Subject> implements Listener<T> {
    @Override
    public void update(T t) {
        t.doSomeThing();
    }
}
