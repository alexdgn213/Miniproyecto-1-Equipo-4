package example.com.remindme;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tarea {

    private int id;
    private String titulo;
    private String fechainicio;
    private String fechafin;
    private boolean completado;


    public Tarea(){

    }


    public Tarea(String titulo) { //Como necesito la fecha de inicio se la agrego automatica.
        this.titulo = titulo;
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.fechainicio = date;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }
}
