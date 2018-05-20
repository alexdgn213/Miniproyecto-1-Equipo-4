package android.example.com.miniproyecto1_equipo4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        articulos = new ArrayList<>();
        inicializarAdaptador();
    }

    /**
     * Elimina un articulo del carrito
     * @param articulo articulo que se desea eliminar
     */
    public void eliminarArticulo(Articulo articulo){

    }

    /**
     *  Agrega un nuevo producto o aumenta la cantidad en caso de estar presente
     * @param articulo articulo que se desea agregar
     */
    private void agregarArticulo(ArticuloComprar articulo){

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
        startActivity(intent);
    }
}