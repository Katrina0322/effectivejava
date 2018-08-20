package com.study.es.dubboApi;

import com.ronglian.core.Result;
import com.ronglian.dto.QueryBean;
import com.ronglian.dto.curd.DelBean;
import com.study.es.core.Result;
import com.study.es.dto.QueryBean;
import com.study.es.dto.curd.DelBean;

import java.io.IOException;
import java.util.List;

/**
 * @description: es agg
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-16 下午2:12
 */
public interface IEsQueryService {
    /**
     * 列表查詢
     * @param sourceQueryBean
     * @return
     */
    Result<String> sourceQuery(QueryBean sourceQueryBean);

    /**
     * 聚合查詢
     * @param aggQueryBean
     * @return
     */
    Result<String> aggQuery(QueryBean aggQueryBean);

    /**
     * 根據_id查詢所需数据
     * @param delBean
     * @return
     */
    Result<String> idQuery(DelBean delBean, List<String> source) throws IOException;
}
