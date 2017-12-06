package com.oneapm.instrument;

/**
 * filename: MethodFilters
 * Description:
 * Author: ubuntu
 * Date: 12/6/17 2:38 PM
 */
public class MethodFilters {

    private MethodFilters() {
    }

    public static final MethodFilter ACCEPT_ALL = new MethodFilter() {
        @Override
        public boolean accept(InstrumentMethod method) {
            return ACCEPT;
        }
    };
}
