package android.example.com.miniproyecto1_equipo4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewActivity extends Activity{

    TextView nombreArticulo;
    TextView medidaArticulo;
    TextView cantidadArticulo;
    TextView precioArticulo;
    ImageView fotoArticulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_activity);
        nombreArticulo = (TextView)findViewById(R.id.nombre_articulo);
        medidaArticulo = (TextView)findViewById(R.id.medida_articulo);
        cantidadArticulo = (TextView)findViewById(R.id.cantidad_articulo);
        precioArticulo = (TextView)findViewById(R.id.precio_articulo);
        fotoArticulo = (ImageView)findViewById(R.id.foto_articulo);

        nombreArticulo.setText("Emma Wilson");
        medidaArticulo.setText("23 years old");
        cantidadArticulo.setText("2");
        precioArticulo.setText("2");
       // fotoArticulo.setImageResource(R.drawable.nestle);
    }
}