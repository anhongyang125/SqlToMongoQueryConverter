package com.mongo.sql.query;

import com.mongo.util.Constant;
import com.mongo.util.CustomStringUtil;

import java.util.ArrayList;
import java.util.List;

public class InsertSqlQuery extends SqlQuery {

    private List<String> columnNames;
    private List<List<String>> columnValues = new ArrayList<>();
    private String insertQuery;

    public InsertSqlQuery(String insertQuery) {
        this.insertQuery = insertQuery;

        String insertSql = insertQuery.replaceFirst(Constant.INSERT, "").replace(Constant.INTO, "").trim();

        this.setTableName(insertSql.substring(0, insertSql.indexOf("(")));
        this.columnNames = getColumnNames(insertSql);

        String str = insertSql;
        int sIndex = str.indexOf("(");
        int eIndex = str.indexOf(")");
        str = str.substring(eIndex+1);
        while(str.length()>0) {
            sIndex = str.indexOf("(")+1;
            eIndex = str.indexOf(")");
            this.columnValues.add(getColumnValues(str.substring(sIndex, eIndex)));
            str = str.substring(eIndex+1);
        }
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<List<String>> getColumnValues() {
        return columnValues;
    }

    private List<String> getColumnNames(String insertSql) {
        int indexOfFirstValueStart = insertSql.indexOf("(") + 1;
        int indexOfFirstValueEnd = insertSql.indexOf(")");
        return CustomStringUtil.getListFromString(insertSql.substring(indexOfFirstValueStart, indexOfFirstValueEnd));
    }

    private List<String> getColumnValues(String insertSql) {
        return CustomStringUtil.getListFromString(insertSql);
    }

    @Override
    public String toString() {
        return insertQuery;
    }
}



