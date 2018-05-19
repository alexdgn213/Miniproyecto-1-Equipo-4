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

    private final LinkedList<Articulo_Comprar> mWordList = new LinkedList<>();
    private int mCount = 0;
    private RecyclerView mRecyclerView;
    private ProductosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarData(mWordList);
        setContentView(R.layout.activity_productos);
       /* for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + mCount++);
            Log.d("WordList", mWordList.getLast());
        }*/
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

    private void inicializarData(LinkedList mWordList){
        mWordList.add(new Articulo_Comprar("Harina","Precio: 20,00Bs",R.drawable.harina));
        mWordList.add(new Articulo_Comprar("Nutella","Precio: 99,99$",R.drawable.nutella));
        mWordList.add(new Articulo_Comprar("Pizza","Precio: 20Bs",R.drawable.pizza));
        mWordList.add(new Articulo_Comprar("Agua Minalba","Precio: 4,00Bs",R.drawable.agua));
        mWordList.add(new Articulo_Comprar("Capitan Crunch","Precio: 79,99$",R.drawable.capn));
        mWordList.add(new Articulo_Comprar("Nestea","Precio: 60,00Bs",R.drawable.tea));
        mWordList.add(new Articulo_Comprar("Toddy","Precio: 33,00Bs",R.drawable.toddy));
        mWordList.add(new Articulo_Comprar("Huevos","Precio: 20,00Bs",R.drawable.huevos));
        mWordList.add(new Articulo_Comprar("Coca-Cola","Precio: 15,00Bs",R.drawable.cocacola));


    }

}
