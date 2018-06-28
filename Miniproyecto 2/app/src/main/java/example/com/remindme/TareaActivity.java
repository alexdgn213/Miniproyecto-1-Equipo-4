package example.com.remindme;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TareaActivity extends AppCompatActivity {

    private static final String TAG = TareaActivity.class.getSimpleName();

    private static final int NO_ID = -99;
    private static final String NO_WORD = "";
    private EditText mEditWordView;
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    int mId= MainActivity.TAREA_ADD;
    private TextView mTextView;
    private TareaOpenHelper mDB;
    private TextView tvEstatus;
    private TextView tvFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);

        mEditWordView = (EditText) findViewById(R.id.textoDetalles);
        tvEstatus = findViewById(R.id.textoEstatus);
        tvFecha = findViewById(R.id.textoFechaCreado);

        // Get data sent from calling activity.
        Bundle extras = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (extras != null) {
            int id = extras.getInt(TareasAdapter.EXTRA_ID, NO_ID);
            String word = extras.getString(TareasAdapter.EXTRA_WORD, NO_WORD);
            String estatus = extras.getString(TareasAdapter.EXTRA_ESTATUS);
            String fecha = extras.getString(TareasAdapter.EXTRA_FECHA);
            if ((id != NO_ID) && (word != NO_WORD)) {
                mId = id;
                mEditWordView.setText(word);
                tvEstatus.setText(estatus);
                tvFecha.setText(fecha);
            }
        }

    }

    /**
     * Regresa a la activity anterior el id de la tarea a modificar y su nuevo texto
     * @param view
     */
    public void returnReply(View view) {
        String word = ((EditText) findViewById(R.id.textoDetalles)).getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, word);
        replyIntent.putExtra(TareasAdapter.EXTRA_ID, mId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

}
