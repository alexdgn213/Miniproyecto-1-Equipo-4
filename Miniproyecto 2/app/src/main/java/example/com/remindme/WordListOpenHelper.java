package example.com.remindme;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class WordListOpenHelper extends SQLiteOpenHelper {

    // It's a good idea to always define a log tag like this.
    private static final String TAG = WordListOpenHelper.class.getSimpleName();
    // has to be 1 first time or app will crash
    private static final int DATABASE_VERSION = 1;
    public static final String WORD_LIST_TABLE = "tarea";
    private static final String DATABASE_NAME = "tareas";
    // Column names...
    public static final String KEY_ID = "id";
    public static final String KEY_WORD = "nombre";
    public static final String INIT_DATE = "Fecha_creacion";
    public static final String END_DATE = "Fecha_finalizacion";
    public static final String ENDED = "Finalizado";
    // ... and a string array of columns.
    private static final String[] COLUMNS = { KEY_ID, KEY_WORD, INIT_DATE, END_DATE, ENDED};

    // Build the SQL query that creates the table.
    private static final String WORD_LIST_TABLE_CREATE =
            "CREATE TABLE " + WORD_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    // id will auto-increment if no value passed
                    KEY_WORD + " TEXT, " + INIT_DATE + " TEXT, " + END_DATE +
                    " TEXT, " + ENDED + " INT );";

    private SQLiteDatabase mWritableDB;

    private SQLiteDatabase mReadableDB;

    public WordListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Tarea[] traerTareas() {
        String query = "SELECT * FROM " + WORD_LIST_TABLE +
                " ORDER BY " + KEY_WORD + " ASC ";
        Cursor cursor = null;
        Tarea entry[]=null;

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();

            }
            cursor = mReadableDB.rawQuery(query, null);
            entry = new Tarea[cursor.getColumnCount()];
            cursor.moveToFirst();
            int i = 1;
            entry[0].setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry[0].setTitulo(cursor.getString(cursor.getColumnIndex(KEY_WORD)));
            entry[0].setFechainicio(cursor.getString(cursor.getColumnIndex(INIT_DATE)));

            while (cursor.moveToNext()) {
                entry[i].setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                entry[i].setTitulo(cursor.getString(cursor.getColumnIndex(KEY_WORD)));
                entry[i].setFechainicio(cursor.getString(cursor.getColumnIndex(INIT_DATE)));
                i++;
            }
        } catch (Exception e) {
            Log.d(TAG, "EXCEPTION! " + e);
        } finally {
            cursor.close();
            return entry;

        }
    }

    public Tarea traerTarea(int id) {
        String query = "SELECT * FROM " + WORD_LIST_TABLE +
                " WHERE id =" + id + " ";
        Cursor cursor = null;
        Tarea entry = new Tarea();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();

            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setTitulo(cursor.getString(cursor.getColumnIndex(KEY_WORD)));
            entry.setFechainicio(cursor.getString(cursor.getColumnIndex(INIT_DATE)));

        } catch (Exception e) {
            Log.d(TAG, "EXCEPTION! " + e);
        } finally {
            cursor.close();
            return entry;

        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WORD_LIST_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}