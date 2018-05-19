package android.example.com.miniproyecto1_equipo4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

/**
 *  Activity que muestra los productos disponibles para comprar
 *
 *  @autor Alexander Garcia, Marco Lozano, Jorge Pinto
 */
public class ProductosActivity extends AppCompatActivity {

    private final LinkedList<ArticuloComprar> productsList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private ProductosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inicializarData(productsList);
        setContentView(R.layout.activity_productos);
        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_productos);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ProductosAdapter(this, productsList,this);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        int i = getResources().getConfiguration().orientation;
        GridLayoutManager glm = new GridLayoutManager(this,2*i);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(glm);
    }

    /**
     * Inizializador que carga todos los productos en la lista
     * @param productsList : Lista de productos a llenar
     */
    private void inicializarData(LinkedList productsList){
        productsList.add(new ArticuloComprar("Harina","Precio: 20,00Bs",R.drawable.harina));
        productsList.add(new ArticuloComprar("Nutella","Precio: 99,99$",R.drawable.nutella));
        productsList.add(new ArticuloComprar("Pizza","Precio: 20Bs",R.drawable.pizza));
        productsList.add(new ArticuloComprar("Agua Minalba","Precio: 4,00Bs",R.drawable.agua));
        productsList.add(new ArticuloComprar("Capitan Crunch","Precio: 79,99$",R.drawable.capn));
        productsList.add(new ArticuloComprar("Nestea","Precio: 60,00Bs",R.drawable.tea));
        productsList.add(new ArticuloComprar("Toddy","Precio: 33,00Bs",R.drawable.toddy));
        productsList.add(new ArticuloComprar("Huevos","Precio: 20,00Bs",R.drawable.huevos));
        productsList.add(new ArticuloComprar("Coca-Cola","Precio: 15,00Bs",R.drawable.cocacola));
    }

    /**
     * Retorna un articulo a la activity anterior para ser agregado al carrito
     * @param articulo : Articulo que se desea agregar.
     */
    public void agregaraCarrito(ArticuloComprar articulo){
        finish();
    }

}
