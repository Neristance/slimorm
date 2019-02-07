package io.dominikschulz.annotationprocessing;


import io.dominikschulz.slimorm.Field;

public class Person {

    @Field(PersonDBContract.COLUMN_ID)
    long id;

    @Field(PersonDBContract.COLUMN_NAME)
    String name;

    @Field(PersonDBContract.COLUMN_EMAIL)
    String email;

    @Field(PersonDBContract.COLUMN_AGE)
    Integer age;

    @Field(PersonDBContract.COLUMN_AGE)
    boolean isFake;

    @Field(PersonDBContract.COLUMN_AGE)
    boolean fakeData;

}
