package com.xj.util;

/**
 * Created by sheeran on 2017/6/16.
 * Talk is cheap show me code.
 * 不要重复写轮子，没啥子用
 */
@Deprecated
public class SqlHelper {
    private StringBuilder updateSql;
    private StringBuilder querySql;
    private String prefix;
    private String condition;


    private SqlHelper(String prefix) {
        this.prefix = prefix;
        updateSql.append(prefix);
    }

    public SqlHelper getUpdateSqlHelper(String table) {
        return new SqlHelper("UPDATE " + table);
    }

    public String getUpdateSql() {
        return updateSql.toString();
    }

    public void addSetParam(String setParam) {
        updateSql.append(" " + setParam + ", ");
    }

    public void addCondition(String condition) {

    }

    public void addFrom(String from) {

    }


}
