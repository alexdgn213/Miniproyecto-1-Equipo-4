package example.com.remindme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {

    private List<Tarea> tareaList;

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView fecha;
        ImageView edit_button;
        ImageView delete_button;

        TareaViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.textoTarea);
            fecha = (TextView) itemView.findViewById(R.id.textoFechaTope);
            delete_button = (ImageView) itemView.findViewById(R.id.botonEliminar);
            edit_button = (ImageView) itemView.findViewById(R.id.botonEditar);
        }
    }

    private static final String TAG = TareasAdapter.class.getSimpleName();

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_WORD = "WORD";
    public static final String EXTRA_POSITION = "POSITION";

    private final LayoutInflater mInflater;
    TareaOpenHelper mDB;
    Context mContext;

    public TareasAdapter(Context context, TareaOpenHelper db){
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDB = db;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.card_tarea,parent,false); LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tarea,parent,false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = mDB.traerTarea(position);
        holder.titulo.setText(tarea.getTitulo());
        final TareaViewHolder h = holder;
        holder.fecha.setText(tarea.getFechainicio());
        holder.delete_button.setOnClickListener(new MyButtonOnClickListener(tarea.getId(), null)
        {


            @Override
            public void onClick(View v ) {
            // You have to get the position like this, you can't hold a reference
            Log.d (TAG + "onClick", "VHPos " + h.getAdapterPosition() + " ID " + id);
            int deleted = mDB.delete(id);
            if (deleted >= 0)
                notifyItemRemoved(h.getAdapterPosition());
        }
        });
        holder.edit_button.setOnClickListener(new MyButtonOnClickListener(
                tarea.getId(), tarea.getTitulo()) {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TareaActivity.class);

                intent.putExtra(EXTRA_ID, id);
                intent.putExtra(EXTRA_POSITION, h.getAdapterPosition());
                intent.putExtra(EXTRA_WORD, word);

                // Start an empty edit activity.
                ((Activity) mContext).startActivityForResult(intent, MainActivity.TAREA_EDIT);
            }
        })
        ;
        //Faltan los demas

    }

    @Override
    public int getItemCount() {
        return(int) mDB.count();
    }



}