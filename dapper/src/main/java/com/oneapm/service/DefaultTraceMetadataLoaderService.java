package com.oneapm.service;

import com.oneapm.trace.AnnotationKey;
import com.oneapm.trace.ServiceTypeInfo;
import com.oneapm.trace.TraceMetadataLoader;

import java.util.List;

/**
 * filename: DefaultTraceMetadataLoaderService
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 10:21 AM
 */
public class DefaultTraceMetadataLoaderService implements TraceMetadataLoaderService {
    private TraceMetadataLoader loader;
    @Override
    public List<ServiceTypeInfo> getServiceTypeInfos() {
        return null;
    }

    @Override
    public List<AnnotationKey> getAnnotationKeys() {
        return null;
    }
}
