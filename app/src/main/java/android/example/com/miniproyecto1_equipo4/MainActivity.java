package android.example.com.miniproyecto1_equipo4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

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

            inicializarData();
            inicializarAdaptador();
        }

        /** Inicializar data, llena la lista de articulos inicial*/

        private void inicializarData(){
            articulos = new ArrayList<>();
            articulos.add(new android.example.com.miniproyecto1_equipo4.Articulo("Harina PAN", "1 KG","Cantidad: x2","20,00Bs", R.drawable.harina));
          /*  articulos.add(new android.example.com.miniproyecto1_equipo4.Articulo("Agua Minalba", "1 L", "Cantidad: x3","4,00Bs",R.drawable.agua));
            articulos.add(new Articulo("Captain Crunch", "794 Grs", "Cantidad: x1", "79,99Bs",R.drawable.capn));
            articulos.add(new Articulo("Nutella","13 Oz","Cantidad x1","99,99Bs",R.drawable.nutella));
            articulos.add(new Articulo("Pizza","100gr","Cantidad x1","20Bs",R.drawable.pizza));
            articulos.add(new Articulo("Nestea","450gr","Cantidad x3","60Bs",R.drawable.tea));
            articulos.add(new Articulo("Toddy","400gr","Cantidad x1","33Bs",R.drawable.toddy));
            articulos.add(new Articulo("Huevos","300gr","Cantidad x30","20Bs",R.drawable.huevos));
            articulos.add(new Articulo("Coca-Cola","355ml","Cantidad x1","15Bs",R.drawable.cocacola));*/


        }
        private void inicializarAdaptador(){
            ComprasAdapter adapter = new ComprasAdapter(articulos);
            rv.setAdapter(adapter);
        }


    public void comprar(View view) {
        Intent intent = new Intent(this,ProductosActivity.class);
        startActivity(intent);
    }
}