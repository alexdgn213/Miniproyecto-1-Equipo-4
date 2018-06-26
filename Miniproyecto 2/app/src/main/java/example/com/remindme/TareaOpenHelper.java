package example.com.remindme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class TareaOpenHelper extends SQLiteOpenHelper {

    // It's a good idea to always define a log tag like this.
    private static final String TAG = TareaOpenHelper.class.getSimpleName();
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

    /**
     *constructor
     * @param context
     */
    public TareaOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * funcion que consulta todas las tareas
     * @return todas las tareas de la base de datos
     */
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

    /**
     * funcion que consulta todas las tareas finalizadas
     * * @return todas las tareas de la base de datos
     */
    public Tarea[] traerTareasCompletadas() {
        String query = "SELECT * FROM " + WORD_LIST_TABLE +
                " WHERE Finalizado = " + 1 +
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

    /**
     * funcion que consulta todas las tareas no finalizadas
     * * @return todas las tareas de la base de datos
     */
    public Tarea[] traerTareasNoCompletadas() {
        String query = "SELECT * FROM " + WORD_LIST_TABLE +
                " WHERE Finalizado = " + 0 +
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

    /**
     * funcion que inserta entidad en la base de datos
     * @param tarea parametro insertar
     * @param fechaFin parametro a insertar
     * @return el id de la taera insertada
     */

    public long count() {
        if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
        return DatabaseUtils.queryNumEntries(mReadableDB, WORD_LIST_TABLE);
    }

    public long insert(String tarea){
        long newId = 0;
        ContentValues values = new ContentValues();
        Date currentTime = Calendar.getInstance().getTime();
        values.put(KEY_WORD, tarea);
        values.put(INIT_DATE, String.valueOf(currentTime));
     //   values.put(END_DATE, String.valueOf(fechaFin));
        values.put(ENDED, 0);
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(WORD_LIST_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());

        }
        return newId;
    }

    /**
     * Funcion para actualizar una entidad de la base de datos en funcion del id
     * @param id parametro de busqueda en la base de datos
     * @param finalizado parametro a actualizar
     * @param fechaFin parametro a actualizar
     * @return devuelve la cantidad de filas modificadas
     */
    public int update(int id, int finalizado ,String fechaFin ){
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(END_DATE, fechaFin);
            values.put(ENDED, finalizado);
            mNumberOfRowsUpdated = mWritableDB.update(WORD_LIST_TABLE,
                    values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;

    }

    public int delete(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {mWritableDB = getWritableDatabase();}
            deleted = mWritableDB.delete(WORD_LIST_TABLE, //table name
                    KEY_ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "DELETE EXCEPTION! " + e.getMessage());        }
        return deleted;
    }

    /**
     * Trae una unica entidad
     * @param id parametro de busqueda
     * @return la entidad buscada
     */
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
