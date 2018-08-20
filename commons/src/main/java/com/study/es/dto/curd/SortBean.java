package com.study.es.dto.curd;

import com.study.es.core.IQueryString;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-17 上午10:16
 */
public class SortBean implements IQueryString {
    private static final long serialVersionUID = 7607321147272665989L;
    private String fieldName;
    private boolean desc;

    public SortBean(String fieldName, boolean desc) {
        this.fieldName = fieldName;
        this.desc = desc;
    }


    @Override
    public String getQueryString() {
        return "\"sort\": [{ \"" + fieldName + "\":{ \"order\":\"" +  (desc? "desc": "asc") + "\"}}]";
    }
}
