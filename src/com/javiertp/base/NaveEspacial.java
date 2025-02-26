package com.javiertp.base;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "naves_espaciales", schema = "flotaespacial")
public class NaveEspacial {
    private int id;
    private String nombre;
    private String clase;
    private int capacidad;
    private List<Tripulante> tripulantes;
    private List<Mision> misiones;

    public NaveEspacial() {
    }

    public NaveEspacial(String nombre, String clase, int capacidad) {
        this.nombre = nombre;
        this.clase = clase;
        this.capacidad = capacidad;
    }

    @Id
    @Column(name = "id_nave")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre_nave")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "clase")
    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    @Basic
    @Column(name = "capacidad_tripulacion")
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @OneToMany(mappedBy = "nave", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(List<Tripulante> tripulantes) {
        this.tripulantes = tripulantes;
    }

    @OneToMany(mappedBy = "nave", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Mision> getMisiones() {
        return misiones;
    }

    public void setMisiones(List<Mision> misiones) {
        this.misiones = misiones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NaveEspacial that = (NaveEspacial) o;
        return id == that.id &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(clase, that.clase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, clase);
    }

    @Override
    public String toString() {
        return "NaveEspacial{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", clase='" + clase + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}
