package com.study.es.dto.agg;


import com.google.common.base.Preconditions;
import com.study.es.dto.AbstractQueryBeanBuilder;
import com.study.es.dto.AggQuery;
import com.study.es.dto.QueryBean;
import com.study.es.dto.QueryGenerater;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-16 下午4:44
 */
public class AggQueryBean implements QueryBean {
    private static final long serialVersionUID = -8372374115912564377L;
    private String query;
    private String index;

    private AggQueryBean(String index, String query) {
        this.index = index;
        this.query = query;
    }

    @Override
    public String getIndex() {
        return index;
    }

    @Override
    public String getQueryString() {
        return query;
    }

    public static class AggBeanBuilder extends AbstractQueryBeanBuilder {
        private static final long serialVersionUID = -2384595516192810910L;
        private StringBuilder sb = new StringBuilder();
        private String index;
        private QueryGenerater queryGenerater;

        public AggBeanBuilder(String index, AggQuery aggQuery) {
            Preconditions.checkNotNull(aggQuery);
            this.index = index;
            sb.append("{\"size\": 0").append(",").append(aggQuery.getQueryString());
            queryGenerater = new QueryGenerater();
        }

        @Override
        public AggQueryBean build(){
            String lastQuery = sb.append(",")
                    .append(queryGenerater.getQueryString()).toString() + "}";
            return new AggQueryBean(index, lastQuery);
        }

        @Override
        protected QueryGenerater getQueryGenerater() {
            return queryGenerater;
        }

        @Override
        protected StringBuilder getSb() {
            return sb;
        }
    }
}
