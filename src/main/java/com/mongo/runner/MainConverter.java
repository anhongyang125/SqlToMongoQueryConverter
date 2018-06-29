package com.mongo.runner;

import com.mongo.converter.*;
import com.mongo.mongo.query.MongoQuery;
import com.mongo.sql.query.DeleteSqlQuery;
import com.mongo.sql.query.InsertSqlQuery;
import com.mongo.sql.query.SelectSqlQuery;
import com.mongo.sql.query.UpdateSqlQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainConverter {

    static public MongoQuery getMongoQuery(String sql) {
        QueryConverter queryConverter;
        MongoQuery mongoQuery = null;
        if (sql.startsWith("SELECT")) {
            queryConverter = new SelectQueryConverter();
            mongoQuery = queryConverter.covertToMongoQuery(new SelectSqlQuery(sql));
        } else if (sql.startsWith("INSERT")) {
            queryConverter = new InsertQueryConverter();
            mongoQuery = queryConverter.covertToMongoQuery(new InsertSqlQuery(sql));
        } else if (sql.startsWith("DELETE")) {
            queryConverter = new DeleteQueryConverter();
            mongoQuery = queryConverter.covertToMongoQuery(new DeleteSqlQuery(sql));
        } else if (sql.startsWith("UPDATE")) {
            queryConverter = new UpdateQueryConverter();
            mongoQuery = queryConverter.covertToMongoQuery(new UpdateSqlQuery(sql));
        }
        return mongoQuery;
    }

    public static void main(String[] args) throws Exception {
        String inputFile = args.length > 0 ? args[0] : System.getProperty("user.dir") + "/input.txt";
	if(inputFile.contains("/build/libs/")) {
	inputFile = inputFile.replace("/build/libs", "");
	}
        File file = new File(inputFile);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        bufferedReader.lines().forEach(line -> {
            System.out.println(getMongoQuery(line));
        });
    }
}
