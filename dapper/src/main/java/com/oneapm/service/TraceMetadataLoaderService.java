package com.oneapm.service;

import com.oneapm.trace.AnnotationKey;
import com.oneapm.trace.ServiceTypeInfo;

import java.util.List;

/**
 * filename: TraceMetadataLoaderService
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 10:17 AM
 */
public interface TraceMetadataLoaderService {
    List<ServiceTypeInfo> getServiceTypeInfos();

    List<AnnotationKey> getAnnotationKeys();
}
