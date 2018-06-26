package example.com.remindme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {

    private List<Tarea> tareaList;

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView fecha;

        TareaViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.textoTarea);
            fecha = (TextView) itemView.findViewById(R.id.textoFechaTope);
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
        Tarea tarea = mDB.query(position);
        holder.titulo.setText(tarea.getTitulo());
        holder.fecha.setText(tarea.getFechainicio());
        //Faltan los demas

    }

    @Override
    public int getItemCount() {
        return(int) mDB.count();
    }



}