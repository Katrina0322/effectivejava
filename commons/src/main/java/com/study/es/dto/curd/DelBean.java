package com.study.es.dto.curd;

import java.io.Serializable;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-18 下午4:17
 */
public class DelBean implements Serializable {
    private static final long serialVersionUID = 1849759930922470284L;
    private String index;
    private String type;
    private String id;

    public DelBean(String index, String type, String id) {
        this.index = index;
        this.type = type;
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }


}
