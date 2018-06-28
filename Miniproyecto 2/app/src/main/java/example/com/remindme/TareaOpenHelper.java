package example.com.remindme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        Log.d(TAG, "Construct TareaOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(WORD_LIST_TABLE_CREATE);
    }

    /**
     * Busca una tarea y retorna todos sus valores
     * @param id id de la tarea deseada
     * @return Tarea con todos sus atributos
     */
    public Tarea traerTarea(int id) {
        String query = "SELECT  * FROM " + WORD_LIST_TABLE +
                " ORDER BY " + KEY_WORD + " ASC " +
                "LIMIT " + id + ",1";
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
            entry.setFechafin(cursor.getString(cursor.getColumnIndex(END_DATE)));
            entry.setCompletado(cursor.getInt(cursor.getColumnIndex(ENDED))==1);
        } catch (Exception e) {
            Log.d(TAG, "EXCEPTION! " + e);
        } finally {
            cursor.close();
            return entry;

        }
    }


    /**
     * Devuelve la cantidad de entradas en la base de datos
     * @return cantidad de entradas
     */
    public long count() {
        if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
        return DatabaseUtils.queryNumEntries(mReadableDB, WORD_LIST_TABLE);
    }


    /**
     * funcion que inserta entidad en la base de datos
     * @param tarea nombre de la tarea a insertar
     * @return el id de la tarera insertada
     */
    long insert(String tarea){
        long newId = 0;
        ContentValues values = new ContentValues();
        Date currentTime = Calendar.getInstance().getTime();
        values.put(KEY_WORD, tarea);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        values.put(INIT_DATE, df.format(currentTime));
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
     * @param word nuevo texto de la tarea
     * @return devuelve la cantidad de filas modificadas
     */
    public int update(int id, String word ){
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_WORD,word);
           // values.put(END_DATE, fechaFin);
            mNumberOfRowsUpdated = mWritableDB.update(WORD_LIST_TABLE,
                    values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;

    }

    /**
     * Funcion para actualizar una tarea completandola en funcion del id
     * @param id parametro de busqueda en la base de datos
     * @return devuelve la cantidad de filas modificadas
     */
    public int completar(int id){
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            Date currentTime = Calendar.getInstance().getTime();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
            values.put(END_DATE, df.format(currentTime));
            values.put(ENDED, 1);
            mNumberOfRowsUpdated = mWritableDB.update(WORD_LIST_TABLE,
                    values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;
    }


    /**
     * Metodo para eliminar una entrada de la BD buscando por id
     * @param id
     * @return
     */
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
     * Permite buscar dentro de la BD
     * @param searchString El titulo que se desea buscar
     * @return
     */
    public Cursor search(String searchString) {
        String[] columns = new String[]{KEY_WORD};
        searchString = "%" + searchString + "%";
        String where = KEY_WORD + " LIKE ?";
        String[]whereArgs = new String[]{searchString};

        Cursor cursor = null;

        try {        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
            cursor = mReadableDB.query(WORD_LIST_TABLE, columns, where, whereArgs, null, null, null);

        } catch (Exception e) {
            Log.d(TAG, "SEARCH EXCEPTION! " + e);
        }

        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TareaOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + WORD_LIST_TABLE);
        onCreate(db);
    }
}
