package android.example.com.miniproyecto1_equipo4;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.LinkedList;

public class ProductosActivity extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<>();
    private int mCount = 0;
    private RecyclerView mRecyclerView;
    private ProductosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + mCount++);
            Log.d("WordList", mWordList.getLast());
        }
        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_productos);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ProductosAdapter(this, mWordList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        int i = getResources().getConfiguration().orientation;
        GridLayoutManager glm = new GridLayoutManager(this,2*i);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(glm);
    }

}
