package example.com.remindme;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarea {

    private String titulo;
    private String descripcion;
    private Date fechainicio;
    private Date fechafin;


    public Tarea(String titulo, String descripcion) { //Como necesito la fecha de inicio se la agrego automatica.
        this.titulo = titulo;
        this.descripcion = descripcion;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        this.fechainicio = date;
    }




}
