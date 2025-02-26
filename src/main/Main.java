package main;

import com.formdev.flatlaf.FlatLightLaf;
import com.javiertp.base.mvc.Controlador;
import com.javiertp.base.mvc.Modelo;
import com.javiertp.base.mvc.Vista;

public class Main {
    public static void main(String[] args) {

        FlatLightLaf.setup();

        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
