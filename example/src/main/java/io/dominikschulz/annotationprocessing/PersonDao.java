package io.dominikschulz.annotationprocessing;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PersonDao {
    private DbHelper dbHelper;

    public PersonDao(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void insert(Person person) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        final ContentValues contentValues = PersonConverter.toContentValues(person);
        db.insert(PersonDBContract.TABLE, null, contentValues);

        db.close();
    }

    public ArrayList<Person> getPersonList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + PersonDBContract.TABLE;

        ArrayList<Person> studentList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            studentList.addAll(PersonConverter.toList(cursor));
        }

        cursor.close();
        db.close();
        return studentList;

    }

}