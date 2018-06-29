package com.mongo.converter;

import com.mongo.mongo.query.UpdateMongoQuery;
import com.mongo.sql.query.UpdateSqlQuery;
import com.mongo.util.CustomStringUtil;

public class UpdateQueryConverter implements QueryConverter<UpdateSqlQuery> {

    @Override
    public UpdateMongoQuery covertToMongoQuery(UpdateSqlQuery updateSqlQuery) {

        UpdateMongoQuery updateMongoQuery = new UpdateMongoQuery();
        updateMongoQuery.setDatabase(updateSqlQuery.getTableName());
        if( !updateSqlQuery.getWhereClauseColumnNames().isEmpty()) {
            updateMongoQuery.setWhereClause(CustomStringUtil.getListOfPairValues(
                    updateSqlQuery.getWhereClauseColumnNames(), updateSqlQuery.getWhereClauseColumnValues()));
        }
        updateMongoQuery.setSetClause(CustomStringUtil.getListOfPairValues(
                updateSqlQuery.getSetClauseColumnNames(), updateSqlQuery.getSetClauseColumnValues()));

        return updateMongoQuery;
    }
}
