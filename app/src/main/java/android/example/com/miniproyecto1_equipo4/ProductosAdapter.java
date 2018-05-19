package android.example.com.miniproyecto1_equipo4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class ProductosAdapter extends
        RecyclerView.Adapter<ProductosAdapter.WordViewHolder>{
    private LinkedList<Articulo_Comprar> mWordList;
    private LayoutInflater mInflater;

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        public TextView wordItemView;
        public TextView priceItemView;           /////////////////////////////////////////////////////
        public ImageView photoItemView;          /////////////////////////////////////////////////////
        ProductosAdapter mAdapter;

        public WordViewHolder(View itemView) {
            super(itemView);
        }

        public WordViewHolder(View itemView, ProductosAdapter adapter) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.nombre_articulo);
            priceItemView = (TextView) itemView.findViewById(R.id.precio_articulo);////////////////////
            photoItemView = (ImageView) itemView.findViewById(R.id.foto_articulo);/////////////////////
            this.mAdapter = adapter;
        }
    }


    public ProductosAdapter(Context context, LinkedList<Articulo_Comprar> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.card_articulo, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Articulo_Comprar mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mWordList.get(position).nombre);
        holder.priceItemView.setText(mWordList.get(position).precio);///////////////////////////////////////////////////////
        holder.photoItemView.setImageResource(mWordList.get(position).foto);////////////////////////////////////////////////

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }


}
