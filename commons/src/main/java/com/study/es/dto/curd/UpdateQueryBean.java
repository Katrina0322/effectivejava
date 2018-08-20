package com.study.es.dto.curd;

import java.io.Serializable;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-18 下午5:39
 */
public class UpdateQueryBean implements Serializable {
    private static final long serialVersionUID = -2295942456864400039L;
    private String index;
    private String query;

    public UpdateQueryBean(String index, String query) {
        this.index = index;
        this.query = query;
    }

    public String getIndex() {
        return index;
    }

    public String getQuery() {
        return query;
    }
}
