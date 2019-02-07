# SlimOrm
SlimOrm is a small annotation based library that generates converters form ```Cursor``` to ```POJOs``` as well as ```POJOs``` to ```ContentValues```.
A lot of people still using the old fashioned SqliteDatabase which comes with the Android Framework but can't easily switch to libaries like
[Room](https://developer.android.com/topic/libraries/architecture/room.html), [Realm](https://realm.io/), [GreenDao](http://greenrobot.org/greendao/), [ObjectBox](http://objectbox.io/) etc.
That is where this tiny library should help to at least reduce the boilerplate code you have to write when using ```Cursor``` and make your code more readable.

## What it does

SlimOrm does one simple thing: It reduces the boilerplate code you need to write when using cursors in Android by using annotations.
It generates for you a converter class which looks like this:

```java
public class PojoConverter {
  /**
   * Converts the {@code cursor} in its current position to an Pojo, 
   * make sure the cursor is in the correct position
   * @param cursor to convert values from
   * @returns Pojo with values converted from {@code cursor} */
  public Pojo toSingleRow(Cursor cursor) {
    Pojo row = new Pojo();
    row.id = CursorUtils.readString(cursor, "_id");
    row.anInt = CursorUtils.readInt(cursor, "timeStamp");
    row.niceValue = CursorUtils.readBoxedInt(cursor, "value");
    row.anBoolean = CursorUtils.readBoolean(cursor, "boolValue");
    row.niceBoolean = CursorUtils.readBoxedBoolean(cursor, "boolValue");
    row.aDouble = CursorUtils.readDouble(cursor, "doubleValue");
    row.niceDouble = CursorUtils.readBoxedDouble(cursor, "doubleValue");
    row.aLong = CursorUtils.readLong(cursor, "longValue");
    row.niceLong = CursorUtils.readBoxedLong(cursor, "longValue");
    row.aFloat = CursorUtils.readFloat(cursor, "floatValue");
    row.niceFloat = CursorUtils.readBoxedFloat(cursor, "floatValue");
    row.aShort = CursorUtils.readShort(cursor, "shortValue");
    row.niceShort = CursorUtils.readBoxedShort(cursor, "shortValue");
    row.byteArray = CursorUtils.readBlob(cursor, "byteArrayValue");
    return row;
  }

  /**
   * Converts the {@code cursor} in its current position to an Pojo, 
   * make sure the cursor is in the correct position
   * @param cursor to convert values from
   * @returns Pojo with values converted from {@code cursor} */
  public static Pojo parseToSingleRow(Cursor cursor) {
    Pojo row = new Pojo();
    row.id = CursorUtils.readString(cursor, "_id");
    row.anInt = CursorUtils.readInt(cursor, "timeStamp");
    row.niceValue = CursorUtils.readBoxedInt(cursor, "value");
    row.anBoolean = CursorUtils.readBoolean(cursor, "boolValue");
    row.niceBoolean = CursorUtils.readBoxedBoolean(cursor, "boolValue");
    row.aDouble = CursorUtils.readDouble(cursor, "doubleValue");
    row.niceDouble = CursorUtils.readBoxedDouble(cursor, "doubleValue");
    row.aLong = CursorUtils.readLong(cursor, "longValue");
    row.niceLong = CursorUtils.readBoxedLong(cursor, "longValue");
    row.aFloat = CursorUtils.readFloat(cursor, "floatValue");
    row.niceFloat = CursorUtils.readBoxedFloat(cursor, "floatValue");
    row.aShort = CursorUtils.readShort(cursor, "shortValue");
    row.niceShort = CursorUtils.readBoxedShort(cursor, "shortValue");
    row.byteArray = CursorUtils.readBlob(cursor, "byteArrayValue");
    return row;
  }

  /**
   * Converts the {@code cursor} to {@code List<Pojo>}, 
   * make sure the cursor is in the correct initial position
   * @param cursor to convert values from
   * @returns {@code List<Pojo>} with values converted from {@code cursor} */
  public List<Pojo> toList(Cursor cursor) {
    List<Pojo> list = new ArrayList<>();
    while (cursor.moveToNext()) {
         list.add(toSingleRow(cursor));
    }
    return list;
  }

  /**
   * Converts the {@code cursor} to {@code List<Pojo>}, 
   * make sure the cursor is in the correct initial position
   * @param cursor to convert values from
   * @returns {@code List<Pojo>} with values converted from {@code cursor} */
  public static List<Pojo> parseToList(Cursor cursor) {
    List<Pojo> list = new ArrayList<>();
    while (cursor.moveToNext()) {
         list.add(parseToSingleRow(cursor));
    }
    return list;
  }

  /**
   * Converts the provided pojo to ContentValues
   * @param pojo to convert values from
   * @returns {@code ContentValues} with values converted from {@code Pojo} */
  public ContentValues toContentValues(Pojo pojo) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("_id", pojo.id);
    contentValues.put("timeStamp", pojo.anInt);
    contentValues.put("value", pojo.niceValue);
    contentValues.put("boolValue", pojo.anBoolean);
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

  /**
   * Converts the provided pojo to ContentValues
   * @param pojo to convert values from
   * @returns {@code ContentValues} with values converted from {@code Pojo} */
  public static ContentValues parseToContentValues(Pojo pojo) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("_id", pojo.id);
    contentValues.put("timeStamp", pojo.anInt);
    contentValues.put("value", pojo.niceValue);
    contentValues.put("boolValue", pojo.anBoolean);
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
     boolean anBoolean;


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

On top you get the integrated ``CursorUtils``` which do read the values from the cursor and handle non existing columns by return a default value for example **0** or **null** for boxed types.
Those are added to reduce duplicated code on multi usage and support partial selections, however this has the downside that you need to be aware of default values for columns that are not in the selection or not in the cursor.

 ## Usage

 * Create your POJO, the fields need to be protected or package private
    * Since it is generating code the generated code needs access to the fields
 * Add the @Field annotation to the fields that should be filled and specify the column name
 * Simply call ```PojoConverter.parseToSingleRow(cursor)``` or ```PojoConverter.parseToList(cursor)``` to convert your ```Cursor``` to ```POJO```
 * It also supports the way from Pojo to ```ContentValues``` simple call ```PojoConverter.parseToContentValues(pojo)```
 * It is also ready for easy testing, so not final and provides both static as well as instance methods

 **A tiny note**: If you use boxed versions of primitives e.g. ```java.lang.Integer``` it also supports nullable columns, that means if a column is **null** in database also the field will be **null**.
 In case there is a column missing in the ```Cursor``` the POJO will have a default value of **0** or **null**.

## Gradle Setup

In order to make SlimOrm work in your project you need to add the SlimOrm dependency and the SlimOrm Annotation processor

```groovy

repositories {
    jcenter()
}
dependencies {
    implementation('io.dominikschulz:slimorm-annotations:1.2.1')
    annotationProcessor('io.dominikschulz:slimorm-processor:1.2.1')
}

```
