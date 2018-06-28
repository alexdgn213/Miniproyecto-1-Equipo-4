package example.com.remindme;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static example.com.remindme.TareaOpenHelper.ENDED;
import static example.com.remindme.TareaOpenHelper.END_DATE;
import static example.com.remindme.TareaOpenHelper.INIT_DATE;
import static example.com.remindme.TareaOpenHelper.KEY_WORD;
import static example.com.remindme.TareaOpenHelper.WORD_LIST_TABLE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int TAREA_EDIT = 1;
    public static final int TAREA_ADD = -1;

    private RecyclerView recyclerView;
    private TareasAdapter tareasAdapter;
    private TareaOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new TareaOpenHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        filtrar(false);
        FloatingActionButton fab = findViewById(R.id.botonNueva);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TareaActivity.class);
                startActivityForResult(intent, TAREA_EDIT);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAREA_EDIT) {
            if (resultCode == RESULT_OK) {
                String word = data.getStringExtra(TareaActivity.EXTRA_REPLY);

                // Update the database.
                if (!TextUtils.isEmpty(word)) {
                    int id = data.getIntExtra(TareasAdapter.EXTRA_ID, -99);

                    if (id == TAREA_ADD) {
                        mDB.insert(word);
                    } else if (id >= 0) {
                        mDB.update(id, word); //Aqui va fecha fin
                    }
                    // Update the UI.
                    tareasAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    /**
     * Inicia la activitie para una nueva tarea
     * @param view
     */
    public void nuevaTarea(View view) {
        Intent intent = new Intent(this,TareaActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_completed:
                filtrar(true);

                return true;
            case R.id.action_pending:
                filtrar(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Genera el adapter dentro del recycler view para solo mostrar tareas pendientes o completadas
     * @param completado booleano que indica si se desean ver tareas completadas(True) o pendientes(false)
     */
    public void filtrar(Boolean completado) {
        tareasAdapter = new TareasAdapter(this, mDB, completado);
        recyclerView.setAdapter(tareasAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
