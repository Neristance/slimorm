package dominikschulz.io.annotationprocessing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "example.db";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + PersonDBContract.TABLE + "("
            + PersonDBContract.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + PersonDBContract.COLUMN_NAME + " TEXT, "
            + PersonDBContract.COLUMN_AGE + " INTEGER, "
            + PersonDBContract.COLUMN_EMAIL + " TEXT )";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
 