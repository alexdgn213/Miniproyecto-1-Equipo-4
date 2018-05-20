package android.example.com.miniproyecto1_equipo4;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  Activity que muestra los articulos comprados por el usuario
 *
 *  @autor Alexander Garcia, Marco Lozano, Jorge Pinto
 */
public class MainActivity extends AppCompatActivity {

    private List<android.example.com.miniproyecto1_equipo4.Articulo> articulos; //Lista de articulos
    private RecyclerView rv;
    public static final int TEXT_REQUEST = 1;
    public static final String EXTRA_LIST = "android.example.com.miniproyecto1_equipo4.extra.List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        articulos = new ArrayList<>();
        if (savedInstanceState != null) {
            articulos= savedInstanceState.getParcelableArrayList(EXTRA_LIST);
        }
        inicializarAdaptador();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_LIST, (ArrayList<? extends Parcelable>) articulos);
    }

    /**
     * Elimina un articulo del carrito
     * @param articulo articulo que se desea eliminar
     */
    public void eliminarArticulo(Articulo articulo){
        articulos.remove(articulo);
        inicializarAdaptador();
    }

    /**
     * Agrega un nuevo producto o aumenta la cantidad en caso de estar presente
     * @param nombre nombre del articulo a agregar
     * @param precio precio del articulo a agregar
     * @param imagen imagen del articculo a agregar
     */
    private void agregarArticulo(String nombre, String precio, int imagen){
        Articulo a = buscarArticulo(nombre);
        if(a==null){
            articulos.add(new Articulo(nombre,"","1",precio,imagen));
        }
        else{
            a.cantidad=String.valueOf(Integer.valueOf(a.cantidad)+1);
        }

        inicializarAdaptador();

    }

    /**
     * Busca un articulo dentro de la lista y lo retorna
     * @param nombre nombre del articulo a buscar
     * @return articulo deseado
     */
    private Articulo buscarArticulo(String nombre){
        for(Articulo a : articulos){
            if(a.nombre.equals(nombre)) return a;
        }
        return null;
    }

    /**
     * Carga los datos en el recyclerview
     */
    private void inicializarAdaptador(){
        ComprasAdapter adapter = new ComprasAdapter(articulos,this);
        rv.setAdapter(adapter);
    }

    /**
     * Inicia la activity de compra de un producto
     */
    public void comprar(View view) {
        Intent intent = new Intent(this,ProductosActivity.class);
        startActivityForResult(intent,TEXT_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == TEXT_REQUEST)
            if(resultCode == RESULT_OK){
                String nombreP = data.getExtras().getString(ProductosActivity.PRODUCT_REPLY);
                String precioP = data.getExtras().getString(ProductosActivity.PRICE_REPLY);
                int imagenP = data.getExtras().getInt(ProductosActivity.IMAGE_REPLY);
                agregarArticulo(nombreP,precioP,imagenP);
            }
    }
}