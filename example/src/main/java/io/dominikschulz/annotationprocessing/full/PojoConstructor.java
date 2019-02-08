package io.dominikschulz.annotationprocessing.full;

import io.dominikschulz.slimorm.ColumnName;
import io.dominikschulz.slimorm.Field;
import io.dominikschulz.slimorm.PojoCreator;

public class PojoConstructor {

    private String id;

    private int anInt;

    private Integer niceValue;

    private boolean anBoolean;


    @PojoCreator
    public PojoConstructor(@ColumnName("_id") String id,
                           @ColumnName("timeStamp") int anInt,
                           @ColumnName("value") Integer niceValue,
                           boolean anBoolean) {
        this.id = id;
        this.anInt = anInt;
        this.niceValue = niceValue;
        this.anBoolean = anBoolean;
    }

    @Field("_id")
    public String getId() {
        return id;
    }

    @Field("timeStamp")
    public int getAnInt() {
        return anInt;
    }

    @Field("value")
    public Integer getNiceValue() {
        return niceValue;
    }

    @Field("anBoolean")
    public boolean isAnBoolean() {
        return anBoolean;
    }
}
