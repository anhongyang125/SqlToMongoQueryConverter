package com.mongo.mongo.query;

import java.util.List;
import java.util.StringJoiner;

public class InsertMongoQuery implements MongoQuery {

    private String tableName;
    private List<ListOfColumnData> listOfColumnDatas;

    public InsertMongoQuery() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ListOfColumnData> getListOfColumnDatas() {
        return listOfColumnDatas;
    }

    public void setListOfColumnDatas(List<ListOfColumnData> listOfColumnDatas) {
        this.listOfColumnDatas = listOfColumnDatas;
    }

    @Override
    public String toString() {
        String data = "";
        if (this.listOfColumnDatas.size() == 1) {
            data = "insertOne({" + listOfColumnDatas.get(0) + "})";
        } else {
            StringJoiner stringJoiner = new StringJoiner(",");
            for (ListOfColumnData listOfColumnData : listOfColumnDatas) {
                stringJoiner.add("{" + listOfColumnData + "}");
                data = "insertMany([" + stringJoiner + "])";
            }
        }
        return "db." + this.tableName + "." + data;
    }


}
