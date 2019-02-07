package io.dominikschulz.annotationprocessing.full;

import io.dominikschulz.slimorm.Field;

public class PojoGetterSetter {

    private String id;

    private int anInt;

    private Integer niceValue;

    private boolean anBoolean;

    private Boolean niceBoolean;

    private double aDouble;

    private Double niceDouble;

    private long aLong;

    private Long niceLong;

    private float aFloat;

    private Float niceFloat;

    private short aShort;

    private Short niceShort;

    private byte[] byteArray;

    @Field(columnName = "_id")
    public String getId() {
        return id;
    }

    @Field(columnName = "_id")
    public void setId(String id) {
        this.id = id;
    }

    @Field(columnName = "timeStamp")
    public int getAnInt() {
        return anInt;
    }

    @Field(columnName = "timeStamp")
    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    @Field(columnName = "value")
    public Integer getNiceValue() {
        return niceValue;
    }

    @Field(columnName = "value")
    public void setNiceValue(Integer niceValue) {
        this.niceValue = niceValue;
    }

    @Field(columnName = "boolValue")
    public boolean isAnBoolean() {
        return anBoolean;
    }

    @Field(columnName = "boolValue")
    public void setAnBoolean(boolean anBoolean) {
        this.anBoolean = anBoolean;
    }

    @Field(columnName = "boolValue")
    public Boolean getNiceBoolean() {
        return niceBoolean;
    }

    @Field(columnName = "boolValue")
    public void setNiceBoolean(Boolean niceBoolean) {
        this.niceBoolean = niceBoolean;
    }

    @Field(columnName = "doubleValue")
    public double getaDouble() {
        return aDouble;
    }

    @Field(columnName = "doubleValue")
    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    @Field(columnName = "doubleValue")
    public Double getNiceDouble() {
        return niceDouble;
    }

    @Field(columnName = "doubleValue")
    public void setNiceDouble(Double niceDouble) {
        this.niceDouble = niceDouble;
    }

    @Field(columnName = "longValue")
    public long getaLong() {
        return aLong;
    }

    @Field(columnName = "longValue")
    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    @Field(columnName = "longValue")
    public Long getNiceLong() {
        return niceLong;
    }

    @Field(columnName = "longValue")
    public void setNiceLong(Long niceLong) {
        this.niceLong = niceLong;
    }

    @Field(columnName = "floatValue")
    public float getaFloat() {
        return aFloat;
    }

    @Field(columnName = "floatValue")
    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    @Field(columnName = "floatValue")
    public Float getNiceFloat() {
        return niceFloat;
    }

    @Field(columnName = "floatValue")
    public void setNiceFloat(Float niceFloat) {
        this.niceFloat = niceFloat;
    }

    @Field(columnName = "shortValue")
    public short getaShort() {
        return aShort;
    }

    @Field(columnName = "shortValue")
    public void setaShort(short aShort) {
        this.aShort = aShort;
    }

    @Field(columnName = "shortValue")
    public Short getNiceShort() {
        return niceShort;
    }

    @Field(columnName = "shortValue")
    public void setNiceShort(Short niceShort) {
        this.niceShort = niceShort;
    }

    @Field(columnName = "byteArrayValue")
    public byte[] getByteArray() {
        return byteArray;
    }

    @Field(columnName = "byteArrayValue")
    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }
}
