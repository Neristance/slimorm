package io.dominikschulz.annotationprocessing.full;

import io.dominikschulz.slimorm.Field;

public class Pojo {

    @Field("_id")
    String id;

    @Field("timeStamp")
    int anInt;

    @Field("value")
    Integer niceValue;

    @Field("boolValue")
    boolean anBoolean;

    @Field("boolValue")
    Boolean niceBoolean;

    @Field("doubleValue")
    double aDouble;

    @Field("doubleValue")
    Double niceDouble;

    @Field("longValue")
    long aLong;

    @Field("longValue")
    Long niceLong;

    @Field("floatValue")
    float aFloat;

    @Field("floatValue")
    Float niceFloat;

    @Field("shortValue")
    short aShort;

    @Field("shortValue")
    Short niceShort;

    @Field("byteArrayValue")
    byte[] byteArray;

}
