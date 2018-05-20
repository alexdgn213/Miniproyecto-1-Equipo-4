package android.example.com.miniproyecto1_equipo4;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 *  Adaptador para agregar las compras realizadas al RecyclerView
 *
 *  @autor Alexander Garcia, Marco Lozano, Jorge Pinto
 */
public class ComprasAdapter extends RecyclerView.Adapter<ComprasAdapter.ArticuloViewHolder> {


    public static class ArticuloViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nombreArticulo;
        TextView medidaArticulo;
        TextView cantidadArticulo;
        TextView precioArticulo;
        ImageView fotoArticulo;
        ImageView botonELiminar;


        ArticuloViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            nombreArticulo = (TextView)itemView.findViewById(R.id.nombre_articulo);
            medidaArticulo = (TextView)itemView.findViewById(R.id.medida_articulo);
            cantidadArticulo = (TextView)itemView.findViewById(R.id.cantidad_articulo);
            precioArticulo = (TextView)itemView.findViewById(R.id.precio_articulo);
            fotoArticulo = (ImageView)itemView.findViewById(R.id.foto_articulo);
            botonELiminar = (ImageView)itemView.findViewById(R.id.boton_eliminar);

        }
    }

    List<Articulo> articulos;
    MainActivity mainActivity;

    ComprasAdapter(List<Articulo> articulos, MainActivity mainActivity){
        this.articulos = articulos;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ArticuloViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_compra, viewGroup, false);
        ArticuloViewHolder avh = new ArticuloViewHolder(v);
        return avh;
    }


    @Override
    public void onBindViewHolder(ArticuloViewHolder articuloViewHolder, final int i) {
        articuloViewHolder.nombreArticulo.setText(articulos.get(i).nombre);
        articuloViewHolder.cantidadArticulo.setText(articulos.get(i).cantidad);
        articuloViewHolder.precioArticulo.setText(articulos.get(i).precio);
        articuloViewHolder.fotoArticulo.setImageResource(articulos.get(i).foto);
        articuloViewHolder.botonELiminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.eliminarArticulo(articulos.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

}