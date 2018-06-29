package com.mongo.converter;

import com.mongo.mongo.query.FindMongoQuery;
import com.mongo.mongo.query.OneColAndData;
import com.mongo.sql.query.SelectSqlQuery;
import com.mongo.util.CustomStringUtil;

import java.util.ArrayList;
import java.util.List;

public class SelectQueryConverter implements QueryConverter<SelectSqlQuery> {

    @Override
    public FindMongoQuery covertToMongoQuery(SelectSqlQuery selectSqlQuery) {

        FindMongoQuery findMongoQuery = new FindMongoQuery();
        findMongoQuery.setTableName(selectSqlQuery.getTableName());
        findMongoQuery.setSelectClause(setOneColAndData(selectSqlQuery.getSelectClause()));
        findMongoQuery.setWhereClause(CustomStringUtil.getListOfPairValues(selectSqlQuery.getWhereClauseColumnNames(),
                selectSqlQuery.getWhereClauseColumnValues()));
        return findMongoQuery;
    }

    private List<OneColAndData> setOneColAndData(List<String> columnValues) {
        List<OneColAndData> oneColAndDatas = new ArrayList<>();

            for(int i=0;i<columnValues.size();i++) {
                oneColAndDatas.add(new OneColAndData(columnValues.get(i), "1"));
        }
        return oneColAndDatas;
    }

}
