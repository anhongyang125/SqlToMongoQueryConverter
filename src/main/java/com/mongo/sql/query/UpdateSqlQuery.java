package com.mongo.sql.query;

import com.mongo.util.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateSqlQuery extends SqlQuery {

    private List<String> whereClauseColumnNames = new ArrayList<>();
    private List<String> whereClauseColumnValues = new ArrayList<>();

    private List<String> setClauseColumnNames = new ArrayList<>();
    private List<String> setClauseColumnValues = new ArrayList<>();

    public UpdateSqlQuery(String sqlQ) {
        String[] sqlArray = sqlQ.split(" ");
        this.setTableName(sqlArray[1]);
        int setIndex = sqlQ.indexOf(Constant.SET);
        int whereIndexOrLastIndex = sqlQ.contains(Constant.WHERE) ? sqlQ.indexOf(Constant.WHERE): sqlQ.length();
        setClauseList(sqlQ.substring(setIndex, whereIndexOrLastIndex));

        if (sqlQ.contains(Constant.WHERE)) {
            setWhereClauseList(sqlQ.substring(whereIndexOrLastIndex));
        }
    }

    public void setClauseList(String deleteQuery) {
        String[] whereClauseData = deleteQuery.replace(Constant.SET,"").trim().split(",");
        for (String pairOfkeyData : whereClauseData) {
            List<String> keyData = Arrays.stream(pairOfkeyData.split("=")).map(value -> value.trim()).collect(Collectors.toList());
            if (keyData.size() == 2) {
                setClauseColumnNames.add(keyData.get(0));
                setClauseColumnValues.add(keyData.get(1));
            }
        }
    }

    public void setWhereClauseList(String deleteQuery) {
        String[] whereClauseData = deleteQuery.replace(Constant.WHERE,"").trim().split(Constant.AND);
        for (String pairOfkeyData : whereClauseData) {
            List<String> keyData = Arrays.stream(pairOfkeyData.split("=")).map(value -> value.trim()).collect(Collectors.toList());
            if (keyData.size() == 2) {
                whereClauseColumnNames.add(keyData.get(0));
                whereClauseColumnValues.add(keyData.get(1));
            }
        }
    }

    public List<String> getWhereClauseColumnNames() {
        return whereClauseColumnNames;
    }

    public List<String> getWhereClauseColumnValues() {
        return whereClauseColumnValues;
    }

    public List<String> getSetClauseColumnNames() {
        return setClauseColumnNames;
    }

    public List<String> getSetClauseColumnValues() {
        return setClauseColumnValues;
    }
}
