package com.mongo.converter;

import com.mongo.mongo.query.DeleteMongoQuery;
import com.mongo.sql.query.DeleteSqlQuery;
import com.mongo.util.CustomStringUtil;

public class DeleteQueryConverter implements QueryConverter<DeleteSqlQuery> {

    @Override
    public DeleteMongoQuery covertToMongoQuery(DeleteSqlQuery deleteSqlQuery) {

        DeleteMongoQuery deleteMongoQuery = new DeleteMongoQuery();
        deleteMongoQuery.setTableName(deleteSqlQuery.getTableName());
        if(! deleteSqlQuery.getColumnNames().isEmpty()) {
            deleteMongoQuery.setWhereClause(CustomStringUtil.getListOfPairValues(deleteSqlQuery.getColumnNames(), deleteSqlQuery.getColumnValues()));
        }
        return deleteMongoQuery;
    }


}
