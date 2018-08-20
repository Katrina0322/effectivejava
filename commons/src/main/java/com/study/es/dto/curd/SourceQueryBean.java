package com.study.es.dto.curd;


import com.study.es.dto.AbstractQueryBeanBuilder;
import com.study.es.dto.QueryBean;
import com.study.es.dto.QueryGenerater;

import java.util.List;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-17 下午2:02
 */
public class SourceQueryBean implements QueryBean {
    private static final long serialVersionUID = -8778692449742541638L;
    private String query;
    private String index;

    private SourceQueryBean(String index, String query) {
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

    public static class SourceQueryBeanBuilder extends AbstractQueryBeanBuilder {
        private static final long serialVersionUID = -6642563244957734328L;
        private StringBuilder sb = new StringBuilder();
        private QueryGenerater queryGenerater;
        private String index;

//        private TimeRangeBean timeRangeBean;
//        private SortBean sortBean;
//        private String exists;
//        private List<String> source;
//        private Map<String, List<String>> must;
//        private Map<String, List<String>> mustNot;
//        private int pageNo;
//        private int pageSize;

        public SourceQueryBeanBuilder(String index, int pageSize, int pageNo, List<String> source) {
            super();
            this.index = index;
            sb.append("{\"from\":").append((pageNo - 1) * pageSize).append(",").append("\"size\":").append(pageSize);
            if(source != null && source.size() > 0) {
                sb.append(",\"_source\": [\"").append(String.join("\",\"", source)).append("\"]");
            }
            queryGenerater = new QueryGenerater();
        }

        public SourceQueryBean build(){
            String lastQuery = sb.append(",")
                    .append(queryGenerater.getQueryString()).toString() + "}";
            return new SourceQueryBean(index, lastQuery);
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
