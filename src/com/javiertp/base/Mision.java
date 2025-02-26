package com.javiertp.base;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "misiones", schema = "flotaespacial")
public class Mision {
    private int id;
    private String descripcion;
    private NaveEspacial nave;
    private String estado;

    public Mision() {
    }

    public Mision(String nombre, NaveEspacial nave, String estado) {
        this.descripcion = nombre;
        this.nave = nave;
        this.estado = estado;
    }

    @Id
    @Column(name = "id_mision")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @ManyToOne
    @JoinColumn(name = "id_nave", referencedColumnName = "id_nave", nullable = false)
    public NaveEspacial getNave() {
        return nave;
    }

    public void setNave(NaveEspacial nave) {
        this.nave = nave;
    }

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mision mision = (Mision) o;
        return id == mision.id &&
                Objects.equals(descripcion, mision.descripcion) &&
                Objects.equals(estado, mision.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, estado);
    }

    @Override
    public String toString() {
        return "Mision{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", nave=" + nave +
                '}';
    }
}
