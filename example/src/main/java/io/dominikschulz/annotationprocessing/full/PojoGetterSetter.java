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

    @Field("_id")
    public String getId() {
        return id;
    }

    @Field("_id")
    public void setId(String id) {
        this.id = id;
    }

    @Field("timeStamp")
    public int getAnInt() {
        return anInt;
    }

    @Field("timeStamp")
    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    @Field("value")
    public Integer getNiceValue() {
        return niceValue;
    }

    @Field("value")
    public void setNiceValue(Integer niceValue) {
        this.niceValue = niceValue;
    }

    @Field("boolValue")
    public boolean isAnBoolean() {
        return anBoolean;
    }

    @Field("boolValue")
    public void setAnBoolean(boolean anBoolean) {
        this.anBoolean = anBoolean;
    }

    @Field("boolValue")
    public Boolean getNiceBoolean() {
        return niceBoolean;
    }

    @Field("boolValue")
    public void setNiceBoolean(Boolean niceBoolean) {
        this.niceBoolean = niceBoolean;
    }

    @Field("doubleValue")
    public double getaDouble() {
        return aDouble;
    }

    @Field("doubleValue")
    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    @Field("doubleValue")
    public Double getNiceDouble() {
        return niceDouble;
    }

    @Field("doubleValue")
    public void setNiceDouble(Double niceDouble) {
        this.niceDouble = niceDouble;
    }

    @Field("longValue")
    public long getaLong() {
        return aLong;
    }

    @Field("longValue")
    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    @Field("longValue")
    public Long getNiceLong() {
        return niceLong;
    }

    @Field("longValue")
    public void setNiceLong(Long niceLong) {
        this.niceLong = niceLong;
    }

    @Field("floatValue")
    public float getaFloat() {
        return aFloat;
    }

    @Field("floatValue")
    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    @Field("floatValue")
    public Float getNiceFloat() {
        return niceFloat;
    }

    @Field("floatValue")
    public void setNiceFloat(Float niceFloat) {
        this.niceFloat = niceFloat;
    }

    @Field("shortValue")
    public short getaShort() {
        return aShort;
    }

    @Field("shortValue")
    public void setaShort(short aShort) {
        this.aShort = aShort;
    }

    @Field("shortValue")
    public Short getNiceShort() {
        return niceShort;
    }

    @Field("shortValue")
    public void setNiceShort(Short niceShort) {
        this.niceShort = niceShort;
    }

    @Field("byteArrayValue")
    public byte[] getByteArray() {
        return byteArray;
    }

    @Field("byteArrayValue")
    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }
}
