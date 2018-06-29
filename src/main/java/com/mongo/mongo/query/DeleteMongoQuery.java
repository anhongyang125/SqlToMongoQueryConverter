package com.mongo.mongo.query;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class DeleteMongoQuery implements MongoQuery {

    private String tableName;
    private List<OneColAndData> whereClause = new ArrayList<>();

    public DeleteMongoQuery() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<OneColAndData> getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(List<OneColAndData> whereClause) {
        this.whereClause = whereClause;
    }

    @Override
    public String toString() {
        String data = "";
        if (this.whereClause.size() == 0) {
            data = "remove({})";
        } else if (this.whereClause.size() == 1) {
            data = "remove({" + whereClause.get(0) + "})";
        } else {
            StringJoiner stringJoiner = new StringJoiner(",");
            for (OneColAndData oneColAndData : whereClause) {
                stringJoiner.add(oneColAndData.toString());
            }
            data = "remove({ $and:[{" + stringJoiner + "}]})";
        }
        return "db." + this.tableName + "." + data;
    }
}
