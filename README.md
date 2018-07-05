# SqlToMongoQueryConverter
This utility will help to convert SQL query to Mongo Query.

###### Requirment:
jdk8, Gradle

###### Prerequisite:
You can copy all sql queries into one file.
e.g. Please find input.txt from this repository.

###### Run as a jar:
java -jar SqlToMongoQueryConverter.jar aboveSqlFilePath.

###### Run from main Class:
You need to pass above sql file as a program arguments for "MainConverter.java" this class.

###### Examples:
1. **SELECT Clause**:
  ###### SqlQuery:  SELECT * FROM TABLE
  ###### MongoQuery: db.TABLE.find({})

  ###### SqlQuery: SELECT lastLoginTimestampUtc, uid, lastAccessedTimestampUtc FROM identityActions WHERE uid=123123
  ###### MongoQuery: db.identityActions.find({"uid":"123123"},{"lastLoginTimestampUtc":"1","uid":"1","lastAccessedTimestampUtc":"1"})

2. **DELETE Clause**:
  ###### SqlQuery:
  DELETE FROM TABLE
  ###### MongoQuery:
  db.TABLE.remove({})

  ###### SqlQuery:
  DELETE FROM identityActions WHERE uid=122323
  ###### MongoQuery:
  db.identityActions.remove({"uid":"122323"})

3. **UPDATE Clause**:
  ###### SqlQuery:
  UPDATE TABLE SET columnName=XYZ
  ###### MongoQuery:
  db.TABLE.updateMany({},{ $set: {"columnName":"XYZ"}})

  ###### SqlQuery:
  UPDATE identityActions SET userName=XYZ WHERE uid=123123
  ###### MongoQuery:
  db.identityActions.updateMany({"uid":"123123"},{ $set: {"userName":"XYZ"}})

4. **INSERT Clause**:
  ###### SqlQuery:
  INSERT INTO identityActions (uid,lastAccessedTimestampUtc) VALUES (122323, 2014-12-12)
  ###### MongoQuery:
  db.TABLE.updateMany({},{ $set: {"columnName":"XYZ"}})

  ###### SqlQuery:
  INSERT INTO identityActions (lastAccessedTimestampUtc,lastModifiedTimestampUtc,uid,email) VALUES
            (2017-05-10T06:14:08.678Z,2017-05-10T06:14:08.678Z, uid-7878, abc@abc),
            (2017-05-10T06:14:08.678Z,2017-05-10T06:14:08.678Z, uid-7979, anc)
  ###### MongoQuery:
  db.identityActions .insertMany([
             {"lastAccessedTimestampUtc":"2017-05-10T06:14:08.678Z", "lastModifiedTimestampUtc":"2017-05-10T06:14:08.678Z", "uid":"uid-7878", "email":"abc@abc"},
             {"lastAccessedTimestampUtc":"2017-05-10T06:14:08.678Z", "lastModifiedTimestampUtc":"2017-05-10T06:14:08.678Z", "uid":"uid-7979", "email":"anc"}])
 
 5. **INDEX Clause**:
  ###### SqlQuery:
  CREATE INDEX Asset_uid_index ON Asset(uid)
  ###### MongoQuery:
  db.Asset.createIndex({"uid":"1"})

  ###### SqlQuery:
  CREATE INDEX Asset_uid_index ON Asset(uid,id)
  ###### MongoQuery:
  db.Asset.createIndex({"uid":"1","id":"1"})
  
