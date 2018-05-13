package android.example.com.miniproyecto1_equipo4;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ArticuloViewHolder> {


    public static class ArticuloViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView nombreArticulo;
        TextView medidaArticulo;
        TextView cantidadArticulo;
        TextView precioArticulo;
        ImageView fotoArticulo;

        ArticuloViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            nombreArticulo = (TextView)itemView.findViewById(R.id.nombre_articulo);
            medidaArticulo = (TextView)itemView.findViewById(R.id.medida_articulo);
            cantidadArticulo = (TextView)itemView.findViewById(R.id.cantidad_articulo);
            precioArticulo = (TextView)itemView.findViewById(R.id.precio_articulo);
            fotoArticulo = (ImageView)itemView.findViewById(R.id.foto_articulo);
        }
    }

    List<Articulo> articulos;

    AdapterRecyclerView(List<Articulo> articulos){
        this.articulos = articulos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ArticuloViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ArticuloViewHolder avh = new ArticuloViewHolder(v);
        return avh;
    }


    @Override
    public void onBindViewHolder(ArticuloViewHolder articuloViewHolder, int i) {
        articuloViewHolder.nombreArticulo.setText(articulos.get(i).nombre);
        articuloViewHolder.medidaArticulo.setText(articulos.get(i).medida);
        articuloViewHolder.cantidadArticulo.setText(articulos.get(i).cantidad);
        articuloViewHolder.precioArticulo.setText(articulos.get(i).precio);
        articuloViewHolder.fotoArticulo.setImageResource(articulos.get(i).foto);

    }

    @Override
    public int getItemCount() {
        return articulos.size();
    }

}