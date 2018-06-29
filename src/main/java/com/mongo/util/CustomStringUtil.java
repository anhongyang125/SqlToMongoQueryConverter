package com.mongo.util;

import com.mongo.mongo.query.OneColAndData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CustomStringUtil {

    public static List<String> getListFromString(String values) {
        return Arrays.stream(values.split(",")).map(value -> value.trim()).collect(Collectors.toList());
    }

    public static List<OneColAndData> getListOfPairValues(List<String> columnValues, List<String> columnDataValues) {
        List<OneColAndData> oneColAndDatas = new ArrayList<>();

        if (columnValues.size() == columnDataValues.size() ) {
            for(int i=0;i<columnValues.size();i++) {
                oneColAndDatas.add(new OneColAndData(columnValues.get(i), columnDataValues.get(i)));
            }
        } else {
            throw new RuntimeException("Data Column & Values are mismatched.");
        }
        return oneColAndDatas;
    }

    public static String getStringFromList(List<OneColAndData> list) {
        StringJoiner stringJoiner = new StringJoiner(",");
        for (OneColAndData oneColAndData : list) {
            stringJoiner.add(oneColAndData.toString());
        }
        return stringJoiner.toString();
    }

}

