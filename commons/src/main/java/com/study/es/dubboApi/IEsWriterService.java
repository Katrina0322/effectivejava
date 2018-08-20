package com.study.es.dubboApi;

import com.ronglian.dto.curd.DelBean;
import com.ronglian.dto.curd.PutData;
import com.ronglian.dto.curd.UpdateQueryBean;
import com.study.es.dto.curd.DelBean;
import com.study.es.dto.curd.PutData;
import com.study.es.dto.curd.UpdateQueryBean;

import java.io.IOException;

/**
 * @description: write to es
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-16 上午11:24
 */
public interface IEsWriterService {

    /**
     * insert
     * @param etlData
     */
    void insert(PutData etlData) throws IOException;


    /**
     * update if exist
     * @param putData
     */
    void update(PutData putData) throws IOException;

    /**
     * delete by _id
     * @param delBean
     */
    void delete(DelBean delBean) throws IOException;

    /**
     * update by query
     * @param updateQueryBean
     */
    void updateByQuery(UpdateQueryBean updateQueryBean) throws IOException;

    void deleteByQuery(UpdateQueryBean updateQueryBean) throws IOException;

}
