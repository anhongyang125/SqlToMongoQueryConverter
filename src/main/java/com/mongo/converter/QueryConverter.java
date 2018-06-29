package com.mongo.converter;

import com.mongo.mongo.query.MongoQuery;
import com.mongo.sql.query.SqlQuery;

public interface QueryConverter<T extends SqlQuery> {

    MongoQuery covertToMongoQuery(T t);
}
