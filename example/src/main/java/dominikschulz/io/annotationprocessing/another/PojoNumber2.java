package dominikschulz.io.annotationprocessing.another;

import dominikschulz.io.tinyorm.Field;

public class PojoNumber2 {

    @Field(columnName = "_id2")
    String id2;

    @Field(columnName = "timeStamp2")
    int anInt2;

    @Field(columnName = "value2")
    Integer niceValue2;

    @Field(columnName = "boolValue2")
    boolean aBoolean2;

    @Field(columnName = "boolValue2")
    Boolean niceBoolean2;

    @Field(columnName = "doubleValue2")
    double aDouble2;

    @Field(columnName = "doubleValue2")
    Double niceDouble2;

    @Field(columnName = "longValue2")
    long aLong2;

    @Field(columnName = "longValue2")
    Long niceLong2;

    @Field(columnName = "floatValue2")
    float aFloat2;

    @Field(columnName = "floatValue2")
    Float niceFloat2;

    @Field(columnName = "shortValue2")
    short aShort2;

    @Field(columnName = "shortValue2")
    Short niceShort2;

    @Field(columnName = "byteArrayValue2")
    byte[] byteArray2;

}
