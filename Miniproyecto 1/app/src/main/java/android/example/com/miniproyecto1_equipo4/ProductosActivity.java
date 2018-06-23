package android.example.com.miniproyecto1_equipo4;

import android.content.Intent;
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
    public static final String PRODUCT_REPLY = "android.example.com.miniproyecto1_equipo4.extra.Product";
    public static final String PRICE_REPLY = "android.example.com.miniproyecto1_equipo4.extra.Price";
    public static final String IMAGE_REPLY = "android.example.com.miniproyecto1_equipo4.extra.Image";

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
        productsList.add(new ArticuloComprar("Harina","Precio: 20,000Bs",R.drawable.harina));
        productsList.add(new ArticuloComprar("Nutella","Precio: 2,000,000Bs",R.drawable.nutella));
        productsList.add(new ArticuloComprar("Pizza","Precio: 2,000,000Bs",R.drawable.pizza));
        productsList.add(new ArticuloComprar("Agua Minalba","Precio: 4,000Bs",R.drawable.agua));
        productsList.add(new ArticuloComprar("Capitan Crunch","Precio: 50,000Bs",R.drawable.capn));
        productsList.add(new ArticuloComprar("Nestea","Precio: 60,000Bs",R.drawable.tea));
        productsList.add(new ArticuloComprar("Toddy","Precio: 33,000Bs",R.drawable.toddy));
        productsList.add(new ArticuloComprar("Huevos","Precio: 20,000Bs",R.drawable.huevos));
        productsList.add(new ArticuloComprar("Coca-Cola","Precio: 15,000Bs",R.drawable.cocacola));
        productsList.add(new ArticuloComprar("Cebolla","Precio: 25,000Bs",R.drawable.cebolla));
        productsList.add(new ArticuloComprar("Cilantro","Precio: 40,000Bs",R.drawable.cilantro));
        productsList.add(new ArticuloComprar("Fresa","Precio: 20.000Bs",R.drawable.fresas));
        productsList.add(new ArticuloComprar("Helado","Precio: 400,000Bs",R.drawable.helado));
        productsList.add(new ArticuloComprar("Leche","Precio: 50,000Bs",R.drawable.leche));
        productsList.add(new ArticuloComprar("Manzana","Precio: 65,000Bs",R.drawable.manzana));
        productsList.add(new ArticuloComprar("Naranja","Precio: 32,000Bs",R.drawable.naranja));
        productsList.add(new ArticuloComprar("Oreo","Precio: 800,000Bs",R.drawable.oreo));
        productsList.add(new ArticuloComprar("Patilla","Precio: 150,000Bs",R.drawable.patilla));
        productsList.add(new ArticuloComprar("Papa","Precio: 200,000Bs",R.drawable.papa));
        productsList.add(new ArticuloComprar("Mantequilla","Precio: 15,000Bs",R.drawable.mantequilla));
    }

    /**
     * Retorna un articulo a la activity anterior para ser agregado al carrito
     * @param articulo : Articulo que se desea agregar.
     */
    public void agregaraCarrito(ArticuloComprar articulo){
        Intent replyIntent = new Intent();
        replyIntent.putExtra(PRODUCT_REPLY,articulo.nombre);
        replyIntent.putExtra(PRICE_REPLY,articulo.precio);
        replyIntent.putExtra(IMAGE_REPLY,articulo.foto);
        setResult(RESULT_OK,replyIntent);
        finish();

    }

}
