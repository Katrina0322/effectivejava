package es;

import java.io.Serializable;

/**
 * @description: trans F to T
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-05-25 上午11:36
 */
public interface TransFunction<F, T> extends Serializable{
    T transfer(F f);
}
