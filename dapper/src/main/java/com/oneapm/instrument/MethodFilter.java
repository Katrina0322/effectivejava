package com.oneapm.instrument;

/**
 * filename: MethodFilter
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 2:48 PM
 */
public interface MethodFilter {
    boolean ACCEPT = true;
    boolean REJECT = false;

    boolean accept(InstrumentMethod method);
}
