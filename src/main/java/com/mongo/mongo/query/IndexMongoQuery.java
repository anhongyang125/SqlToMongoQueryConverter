package com.mongo.mongo.query;

import java.util.List;
import java.util.StringJoiner;

public class IndexMongoQuery implements MongoQuery {

    String databseName;
    String indexName;
    List<OneColAndData> indexColumns;

    public IndexMongoQuery() {
    }

    public void setDatabseName(String databseName) {
        this.databseName = databseName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setIndexColumns(List<OneColAndData> indexColumns) {
        this.indexColumns = indexColumns;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (OneColAndData oneColAndData : indexColumns) {
            stringJoiner.add(oneColAndData.toString());
        }
        return "db."+this.databseName+".createIndex({"+stringJoiner+"})";
    }
}
