package dominikschulz.io.annotationprocessing;

import dominikschulz.io.tinyorm.Field;

public class Pojo {

    @Field(columnName = "_id")
    String id;

    @Field(columnName = "timeStamp")
    int anInt;

    @Field(columnName = "value")
    Integer niceValue;

    @Field(columnName = "boolValue")
    boolean aBoolean;

    @Field(columnName = "boolValue")
    Boolean niceBoolean;

    @Field(columnName = "doubleValue")
    double aDouble;

    @Field(columnName = "doubleValue")
    Double niceDouble;

    @Field(columnName = "longValue")
    long aLong;

    @Field(columnName = "longValue")
    Long niceLong;

    @Field(columnName = "floatValue")
    float aFloat;

    @Field(columnName = "floatValue")
    Float niceFloat;

    @Field(columnName = "shortValue")
    short aShort;

    @Field(columnName = "shortValue")
    Short niceShort;

    @Field(columnName = "byteArrayValue")
    byte[] byteArray;

}
