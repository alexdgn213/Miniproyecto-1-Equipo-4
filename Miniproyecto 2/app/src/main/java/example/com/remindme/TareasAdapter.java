package example.com.remindme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView fecha;
        ImageView edit_button;
        ImageView delete_button;
        ImageView complete_button;
        CardView fondo;
        LinearLayout contenedor;
        LinearLayout botones;
        boolean oculto;

        TareaViewHolder(View itemView) {
            super(itemView);
            fondo = itemView.findViewById(R.id.card);
            botones = itemView.findViewById(R.id.botones);
            titulo = (TextView) itemView.findViewById(R.id.textoTarea);
            fecha = (TextView) itemView.findViewById(R.id.textoFechaTope);
            delete_button = (ImageView) itemView.findViewById(R.id.botonEliminar);
            edit_button = (ImageView) itemView.findViewById(R.id.botonEditar);
            complete_button = itemView.findViewById(R.id.botonCompletar);
            contenedor = itemView.findViewById(R.id.fondo);
            oculto = true;
        }
    }

    private static final String TAG = TareasAdapter.class.getSimpleName();

    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_WORD = "WORD";
    public static final String EXTRA_POSITION = "POSITION";
    public static final String EXTRA_ESTATUS = "ESTATUS";
    public static final String EXTRA_FECHA = "FECHA";

    private final LayoutInflater mInflater;
    TareaOpenHelper mDB;
    Context mContext;
    Boolean completado;

    public TareasAdapter(Context context, TareaOpenHelper db, boolean completado){
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDB = db;
        this.completado = completado;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.card_tarea,parent,false); LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tarea,parent,false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TareaViewHolder holder, int position) {
        Tarea tarea = mDB.traerTarea(position);
        holder.titulo.setText(tarea.getTitulo());
        final TareaViewHolder h = holder;
        if(!tarea.isCompletado()) {
            holder.fecha.setText(tarea.getFechainicio());
        }
        else{
            holder.fecha.setText(tarea.getFechafin());
            holder.complete_button.setVisibility(View.GONE);
        }
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
        holder.complete_button.setOnClickListener(new MyButtonOnClickListener(tarea.getId(), null)
        {
            @Override
            public void onClick(View v ) {
            // You have to get the position like this, you can't hold a reference
            Log.d (TAG + "onClick", "VHPos " + h.getAdapterPosition() + " ID " + id);
            int updated = mDB.completar(id);
            if (updated >= 0)
                notifyDataSetChanged();
        }
        });
        String estatus;
        String fecha;
        if(tarea.isCompletado()){
            estatus = "Completada";
            fecha = tarea.getFechafin();
        }
        else{
            estatus= "Pendiente";
            fecha = tarea.getFechainicio();
        }
        holder.edit_button.setOnClickListener(new MyButtonOnClickListener(
                tarea.getId(), tarea.getTitulo(),estatus,fecha) {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TareaActivity.class);

                intent.putExtra(EXTRA_ID, id);
                intent.putExtra(EXTRA_WORD, word);
                intent.putExtra(EXTRA_POSITION, h.getAdapterPosition());
                intent.putExtra(EXTRA_ESTATUS,estado);
                intent.putExtra(EXTRA_FECHA,fecha);


                // Start an empty edit activity.
                ((Activity) mContext).startActivityForResult(intent, MainActivity.TAREA_EDIT);
            }
        });
        holder.fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.oculto){
                    holder.oculto=false;
                    holder.botones.setVisibility(View.VISIBLE);
                }
                else{
                    holder.oculto=true;
                    holder.botones.setVisibility(View.GONE);
                }
            }
        });
        if(completado){
            if(!tarea.isCompletado()){
                holder.contenedor.setVisibility(View.GONE);
                holder.contenedor.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
        }
        else{
            if(tarea.isCompletado()){
                holder.contenedor.setVisibility(View.GONE);
                holder.contenedor.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }

        }


    }

    @Override
    public int getItemCount() {
        return(int) mDB.count();
    }



}