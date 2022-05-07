package exa.ycf.expbdd_jv;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "etudiant";
    public static final String ETUDIANT_ID = "id";
    public static final String ETUDIANT_NAME = "name";
    public static final String ETUDIANT_NOTE = "note";
    private static final String DATABASE_NAME = "etudiant.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ETUDIANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ETUDIANT_NAME + " TEXT NOT NULL, " +
            ETUDIANT_NOTE + " REAL);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
