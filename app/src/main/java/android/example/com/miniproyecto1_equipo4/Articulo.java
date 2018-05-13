package android.example.com.miniproyecto1_equipo4;

import java.util.ArrayList;
import java.util.List;


class Articulo {

    String nombre;
    String medida; //Litros, kilogramos
    String cantidad;
    String precio;
    int foto;

    Articulo(String nombre, String medida, String cantidad, String precio, int foto) {
        this.nombre = nombre;
        this.medida = medida;
        this.cantidad = cantidad;
        this.precio = precio;
        this.foto = foto;
    }
}