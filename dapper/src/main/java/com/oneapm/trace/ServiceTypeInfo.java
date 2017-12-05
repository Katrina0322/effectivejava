package com.oneapm.trace;

/**
 * filename: ServiceTypeInfo
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 10:18 AM
 */
public interface ServiceTypeInfo {
    ServiceType getServiceType();

    AnnotationKeyMatcher getPrimaryAnnotationKeyMatcher();
}
