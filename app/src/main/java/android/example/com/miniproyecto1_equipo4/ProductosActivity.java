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
        productsList.add(new ArticuloComprar("Cerveza","Precio: 17,00Bs",R.drawable.cerveza));
        productsList.add(new ArticuloComprar("Cafe","Precio: 13,00Bs",R.drawable.cafe));
        productsList.add(new ArticuloComprar("Club Social","Precio: 17,80Bs",R.drawable.clubsocial));
        productsList.add(new ArticuloComprar("Cotufa","Precio: 37,80Bs",R.drawable.cotufa));
        productsList.add(new ArticuloComprar("Gomitas","Precio: 6,80Bs",R.drawable.gomitas));
        productsList.add(new ArticuloComprar("Jugo de Manzana","Precio: 20,50Bs",R.drawable.jugo));
        productsList.add(new ArticuloComprar("Galleta Maria","Precio: 17,80Bs",R.drawable.maria));
        productsList.add(new ArticuloComprar("Cheerios","Precio: 87,80Bs",R.drawable.cheerios));
        productsList.add(new ArticuloComprar("Nesquik","Precio: 83,30Bs",R.drawable.nesquik));
        productsList.add(new ArticuloComprar("Fruity Pebbles","Precio: 90,80Bs",R.drawable.fruity));
        productsList.add(new ArticuloComprar("Zucaritas","Precio: 77,80Bs",R.drawable.zuc));
        productsList.add(new ArticuloComprar("Tea","Precio: 17,80Bs",R.drawable.te));

    }

    /**
     * Retorna un articulo a la activity anterior para ser agregado al carrito
     * @param articulo : Articulo que se desea agregar.
     */
    public void agregaraCarrito(ArticuloComprar articulo){
        finish();
    }

}
