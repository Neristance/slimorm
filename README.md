# SlimOrm
SlimOrm is a small annotation based library that generates converters form ```Cursor``` to ```POJOs``` as well as ```POJOs``` to ```ContentValues```.
A lot of people still using the old fashioned SqliteDatabase which comes with the Android Framework but can't easily switch to libaries like 
[Room](https://developer.android.com/topic/libraries/architecture/room.html), [Realm](https://realm.io/), [GreenDao](http://greenrobot.org/greendao/), [ObjectBox](http://objectbox.io/) etc.
That is where this tiny library should help to at least reduce the boilerplate code you have to write when using ```Cursor``` and make your code more readable.

## What it does

SlimOrm does one simple thing: It reduces the boilerplate code you need to write when using cursors in Android by using annotations.
It generates for you a converter class which looks like this:

```java
public final class PojoConverter {

   public static Pojo toSingleRow(Cursor cursor) {
   
     Pojo row = new Pojo();
     if (!cursor.isNull(cursor.getColumnIndex("_id"))) {
         row.id = String.valueOf(cursor.getString(cursor.getColumnIndex("_id")));
     }
     row.anInt = cursor.getInt(cursor.getColumnIndex("timeStamp"));
     if (!cursor.isNull(cursor.getColumnIndex("value"))) {
         row.niceValue = Integer.valueOf(cursor.getInt(cursor.getColumnIndex("value")));
     }
     row.aBoolean = cursor.getInt(cursor.getColumnIndex("boolValue")) == 1;
     if (!cursor.isNull(cursor.getColumnIndex("boolValue"))) {
         row.niceBoolean = Boolean.valueOf(cursor.getInt(cursor.getColumnIndex("boolValue")) == 1);
     }
     row.aDouble = cursor.getDouble(cursor.getColumnIndex("doubleValue"));
     if (!cursor.isNull(cursor.getColumnIndex("doubleValue"))) {
         row.niceDouble = Double.valueOf(cursor.getDouble(cursor.getColumnIndex("doubleValue")));
     }
     row.aLong = cursor.getLong(cursor.getColumnIndex("longValue"));
     if (!cursor.isNull(cursor.getColumnIndex("longValue"))) {
         row.niceLong = Long.valueOf(cursor.getLong(cursor.getColumnIndex("longValue")));
     }
     row.aFloat = cursor.getFloat(cursor.getColumnIndex("floatValue"));
     if (!cursor.isNull(cursor.getColumnIndex("floatValue"))) {
         row.niceFloat = Float.valueOf(cursor.getFloat(cursor.getColumnIndex("floatValue")));
     }
     row.aShort = cursor.getShort(cursor.getColumnIndex("shortValue"));
     if (!cursor.isNull(cursor.getColumnIndex("shortValue"))) {
         row.niceShort = Short.valueOf(cursor.getShort(cursor.getColumnIndex("shortValue")));
     }
     row.byteArray = cursor.getBlob(cursor.getColumnIndex("byteArrayValue"));
     return row;
   }
 
   public static List<Pojo> toList(Cursor cursor) {
     List<Pojo> list = new ArrayList<>();
     while (cursor.moveToNext()) {
          list.add(toSingleRow(cursor));
     }
     return list;
   }
   
   public static ContentValues toContentValues(Pojo pojo) {
       final ContentValues contentValues = new ContentValues();
       contentValues.put("_id", pojo.id);
       contentValues.put("timeStamp", pojo.anInt);
       contentValues.put("value", pojo.niceValue);
       contentValues.put("boolValue", pojo.aBoolean);
       contentValues.put("boolValue", pojo.niceBoolean);
       contentValues.put("doubleValue", pojo.aDouble);
       contentValues.put("doubleValue", pojo.niceDouble);
       contentValues.put("longValue", pojo.aLong);
       contentValues.put("longValue", pojo.niceLong);
       contentValues.put("floatValue", pojo.aFloat);
       contentValues.put("floatValue", pojo.niceFloat);
       contentValues.put("shortValue", pojo.aShort);
       contentValues.put("shortValue", pojo.niceShort);
       contentValues.put("byteArrayValue", pojo.byteArray);
       return contentValues;
     }
 }
 ```
 
 As you can see it generates the Boilerplate code to convert the ```Cursor``` to the fields in the ```POJO``` as well as back to ```ContentValues``` and that's pretty easy just add the ```@Field(columnName = "dbcolumn")``` annotation and you are done:
 
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
 
 * Create your POJO, the fields need to be protected or package private
    * Since it is generating code the generated code needs access to the fields
 * Add the @Field annotation to the fields that should be filled and specify the column name
 * Simply call ```PojoConverter.toSingleRow(cursor)``` or ```PojoConverter.toList(cursor)``` to convert your ```Cursor``` to ```POJO```
 * It also supports the way from Pojo to ```ContentValues``` simple call ```PojoConverter.toContentValues(pojo)```
 
 **A tiny note**: If you use boxed versions of primitives e.g. ```java.lang.Integer``` it also supports nullable columns, that means if a column is **null** in database also the field will be **null**

## Gradle Setup

In order to make SlimOrm work in your project you need to add the SlimOrm dependency and the SlimOrm Annotation processor

```groovy

repositories {
    jcenter()
}
dependencies {
    implementation('io.dominikschulz:slimorm-annotations:1.0')
    annotationProcessor('io.dominikschulz:slimorm-processor:1.0')
}

```