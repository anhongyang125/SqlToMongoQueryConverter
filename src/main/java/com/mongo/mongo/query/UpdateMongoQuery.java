package com.mongo.mongo.query;

import com.mongo.util.CustomStringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class UpdateMongoQuery implements MongoQuery {

    private String database;

    private List<OneColAndData> setClause = new ArrayList<>();
    private List<OneColAndData> whereClause = new ArrayList<>();

    public UpdateMongoQuery() {
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setSetClause(List<OneColAndData> setClause) {
        this.setClause = setClause;
    }

    public void setWhereClause(List<OneColAndData> whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String toString() {

        StringJoiner outerStringJoiner = new StringJoiner(",");
        String setClauseData = "";

        String whereClauseData = "{" + CustomStringUtil.getStringFromList(whereClause) + "}";
        outerStringJoiner.add(whereClauseData);

        if (this.setClause.size() != 0) {
            setClauseData = "{ $set: {" + CustomStringUtil.getStringFromList(setClause) + "}}";
            outerStringJoiner.add(setClauseData);
        }

        return "db." + database + ".updateMany(" + outerStringJoiner.toString() +")";
    }
}
