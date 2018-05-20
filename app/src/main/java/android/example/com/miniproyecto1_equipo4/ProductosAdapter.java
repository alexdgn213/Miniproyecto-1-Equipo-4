package android.example.com.miniproyecto1_equipo4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 *  Adaptador para agregar los productos disponibles al RecyclerView
 *
 *  @autor Alexander Garcia, Marco Lozano, Jorge Pinto
 */
public class ProductosAdapter extends
        RecyclerView.Adapter<ProductosAdapter.WordViewHolder>{
    private LinkedList<ArticuloComprar> productsList;
    private LayoutInflater mInflater;
    private ProductosActivity productosActivity;

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        public TextView wordItemView;
        public TextView priceItemView;
        public ImageView photoItemView;
        public CardView card;
        ProductosAdapter mAdapter;

        public WordViewHolder(View itemView) {
            super(itemView);
        }

        public WordViewHolder(View itemView, ProductosAdapter adapter) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.nombre_articulo);
            priceItemView = (TextView) itemView.findViewById(R.id.precio_articulo);
            photoItemView = (ImageView) itemView.findViewById(R.id.foto_articulo);
            card = (CardView) itemView.findViewById(R.id.card_articulo);
            this.mAdapter = adapter;
        }
    }


    public ProductosAdapter(Context context, LinkedList<ArticuloComprar> wordList,ProductosActivity productosActivity) {
        mInflater = LayoutInflater.from(context);
        this.productsList = wordList;
        this.productosActivity=productosActivity;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.card_articulo, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, final int position) {
        ArticuloComprar mCurrent = productsList.get(position);
        holder.wordItemView.setText(productsList.get(position).nombre);
        holder.priceItemView.setText(productsList.get(position).precio);
        holder.photoItemView.setImageResource(productsList.get(position).foto);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productosActivity.agregaraCarrito(productsList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }


}
