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

import static example.com.remindme.TareaOpenHelper.ENDED;
import static example.com.remindme.TareaOpenHelper.END_DATE;
import static example.com.remindme.TareaOpenHelper.INIT_DATE;
import static example.com.remindme.TareaOpenHelper.KEY_WORD;
import static example.com.remindme.TareaOpenHelper.WORD_LIST_TABLE;

public class MainActivity extends AppCompatActivity {
    private List<Tarea> tareaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TareasAdapter tareasAdapter;
    private TareaOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDB = new TareaOpenHelper(this);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        tareasAdapter = new TareasAdapter(tareaList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tareasAdapter);

        inicializarTareas();
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
