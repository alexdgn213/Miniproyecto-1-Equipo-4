package example.com.remindme;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static example.com.remindme.WordListOpenHelper.ENDED;
import static example.com.remindme.WordListOpenHelper.END_DATE;
import static example.com.remindme.WordListOpenHelper.INIT_DATE;
import static example.com.remindme.WordListOpenHelper.KEY_WORD;
import static example.com.remindme.WordListOpenHelper.WORD_LIST_TABLE;

public class MainActivity extends AppCompatActivity {
    private List<Tarea> tareaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TareasAdapter tareasAdapter;
    private WordListOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDB = new WordListOpenHelper(this);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        tareasAdapter = new TareasAdapter(tareaList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tareasAdapter);

        inicializarTareas();
    }

    private void fillDatabaseWithData(SQLiteDatabase db, String tarea, Date fechaFin){
        // Create a container for the data.
        ContentValues values = new ContentValues();
        Date currentTime = Calendar.getInstance().getTime();
        values.put(KEY_WORD, tarea);
        values.put(INIT_DATE, String.valueOf(currentTime));
        values.put(END_DATE, String.valueOf(fechaFin));
        values.put(ENDED, 0);
        db.insert(WORD_LIST_TABLE, null, values);
    }


    private void inicializarTareas(){
        Tarea tarea = new Tarea("Esto es un titulo");
        tareaList.add(tarea);
        tarea = new Tarea("Debemos hacer la base de datos");
        tareaList.add(tarea);
    }


    public void nuevaTarea(View view) {
        Intent intent = new Intent(this,TareaActivity.class);
        startActivity(intent);
    }
}
