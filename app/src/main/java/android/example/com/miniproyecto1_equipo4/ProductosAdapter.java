package android.example.com.miniproyecto1_equipo4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class ProductosAdapter extends
        RecyclerView.Adapter<ProductosAdapter.WordViewHolder>{
    private LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    public ProductosAdapter(Context context, LinkedList<String> wordList) {
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
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        public TextView wordItemView;
        ProductosAdapter mAdapter;

        public WordViewHolder(View itemView) {
            super(itemView);
        }
        public WordViewHolder(View itemView, ProductosAdapter adapter) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.tituloArticulo);
            this.mAdapter = adapter;
        }
    }
}
