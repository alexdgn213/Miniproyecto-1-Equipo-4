package example.com.remindme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Tarea> tareaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TareasAdapter tareasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nuevaTarea(View view) {
        Intent intent = new Intent(this,TareaActivity.class);
        startActivity(intent);
    }
}
