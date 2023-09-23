package android.rain.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
//    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;
    private String dropTableQuery;//"DROP TABLE IF EXISTS users";
    private String createTableQuery;//createTableQuery = "CREATE TABLE IF NOT EXISTS users (account TEXT PRIMARY KEY, password TEXT)";


    public DatabaseHelper(Context context,String DATABASE_NAME,String createTableQuery,String dropTableQuery) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.createTableQuery=createTableQuery;
        this.dropTableQuery=dropTableQuery;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropTableQuery);
        onCreate(db);
    }
}