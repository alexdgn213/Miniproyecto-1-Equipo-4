package android.example.com.miniproyecto1_equipo4;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 *  Clase que representa un articulo en venta
 *
 *  @autor Alexander Garcia, Marco Lozano, Jorge Pinto
 */
class Articulo implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}