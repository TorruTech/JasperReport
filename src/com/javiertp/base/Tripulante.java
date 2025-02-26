package com.javiertp.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tripulantes", schema = "flotaespacial")
public class Tripulante {
    private int id;
    private String nombre;
    private String rango;
    private NaveEspacial nave;

    public Tripulante() {
    }

    public Tripulante(String nombre, String rango, NaveEspacial nave) {
        this.nombre = nombre;
        this.rango = rango;
        this.nave = nave;
    }

    @Id
    @Column(name = "id_tripulante")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre_tripulante")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "rango")
    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    @ManyToOne
    @JoinColumn(name = "id_nave", referencedColumnName = "id_nave", nullable = false)
    public NaveEspacial getNave() {
        return nave;
    }

    public void setNave(NaveEspacial nave) {
        this.nave = nave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tripulante that = (Tripulante) o;
        return id == that.id &&
                Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "Tripulante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rango='" + rango + '\'' +
                ", nave=" + nave +
                '}';
    }
}
