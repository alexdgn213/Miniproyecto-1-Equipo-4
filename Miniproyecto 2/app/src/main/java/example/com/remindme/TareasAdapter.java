package example.com.remindme;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public TareasAdapter(List<Tarea> tareaList){
        this.tareaList = tareaList;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tarea,parent,false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = tareaList.get(position);
        holder.titulo.setText(tarea.getTitulo());
        holder.fecha.setText((CharSequence) tarea.getFechainicio());

    }

    @Override
    public int getItemCount() {
        return tareaList.size();
    }



}