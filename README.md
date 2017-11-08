# TinyOrm
TinyOrm is a small annotation based library that generates converters form cursor to pojos.

## What it does

TinyOrm does one simple thing: It reduces the boilerplate code you need to write when using cursors in Android by using annotations.
It generates for you a converter class which looks like this:

```java
public final class PojoConverter {

   public static Pojo toSingleRow(Cursor cursor) {
   
     Pojo row = new Pojo();
     if (!cursor.isNull(cursor.getColumnIndex("_id"))) {
         row.id = String.valueOf(cursor.getString(cursor.getColumnIndex("_id")));
     };
     row.anInt = cursor.getInt(cursor.getColumnIndex("timeStamp"));
     if (!cursor.isNull(cursor.getColumnIndex("value"))) {
         row.niceValue = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("value")));
     };
     row.aBoolean = cursor.getInt(cursor.getColumnIndex("boolValue")) == 1;
     if (!cursor.isNull(cursor.getColumnIndex("boolValue"))) {
         row.niceBoolean = Boolean.valueOf(cursor.getInt(cursor.getColumnIndex("boolValue")) == 1);
     };
     row.aDouble = cursor.getDouble(cursor.getColumnIndex("doubleValue"));
     if (!cursor.isNull(cursor.getColumnIndex("doubleValue"))) {
         row.niceDouble = Double.valueOf(cursor.getDouble(cursor.getColumnIndex("doubleValue")));
     };
     row.aLong = cursor.getLong(cursor.getColumnIndex("longValue"));
     if (!cursor.isNull(cursor.getColumnIndex("longValue"))) {
         row.niceLong = Long.valueOf(cursor.getLong(cursor.getColumnIndex("longValue")));
     };
     row.aFloat = cursor.getFloat(cursor.getColumnIndex("floatValue"));
     if (!cursor.isNull(cursor.getColumnIndex("floatValue"))) {
         row.niceFloat = Float.valueOf(cursor.getFloat(cursor.getColumnIndex("floatValue")));
     };
     row.aShort = cursor.getShort(cursor.getColumnIndex("shortValue"));
     if (!cursor.isNull(cursor.getColumnIndex("shortValue"))) {
         row.niceShort = Short.valueOf(cursor.getShort(cursor.getColumnIndex("shortValue")));
     };
     row.byteArray = cursor.getBlob(cursor.getColumnIndex("byteArrayValue"));
     return row;
   }
 
   public static List<Pojo> toList(Cursor cursor) {
     List<Pojo> list = new ArrayList<>();
     while (cursor.moveToNext()) {;
          list.add(toSingleRow(cursor));
     };
     return list;
   }
 }
 ```
 
 As you can see it generates the Boilerplate code to convert the cursor to the fields in the Pojo and thats pretty easy just add the @Field annotation and you are done:
 
 ```java
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
 ```
 
 ## Usage
 
 * Create your Pojo, the fields need to be protected or package private
 * Add the @Field annotation to the fields that should be filled and specify the column name
 * Simply call ```PojoConverter.toSingleRow(cursor)``` or ```PojoConverter.toList(cursor)```
 
 **A tiny note**: If you use boxed versions of primitives e.g. ```java.lang.Integr``` it also supports nullable columns, that means if a column is **null** in database also the field will be **null**

## Gradle Setup

In order to make TinyOrm work in your project you need to add the TinyOrm dependency and the TinyOrm Annotation processor

``` groovy

dependencies {
    implementation project('dominikschulz.io:tinyorm-annotations:0.1')
    annotationProcessor project('dominikschulz.io:tinyorm-processor:0.1')
}

```

**Note** im currently working on making TinyOrm available on public repositories :)
