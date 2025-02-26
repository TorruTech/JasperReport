package com.javiertp.base.mvc;

import com.javiertp.base.Mision;
import com.javiertp.base.NaveEspacial;
import com.javiertp.base.Tripulante;
import util.HibernateUtil;
import java.util.List;

public class Modelo {

    public void conectar() {
        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSession();
    }

    public void desconectar() {
        HibernateUtil.closeSessionFactory();
    }

    public List<NaveEspacial> obtenerNaves() {
        return HibernateUtil.getCurrentSession().createQuery("FROM NaveEspacial").getResultList();
    }

    public List<Mision> obtenerMisiones() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Mision").getResultList();
    }

    public List<Tripulante> obtenerTripulantes() {
        return HibernateUtil.getCurrentSession().createQuery("FROM Tripulante").getResultList();
    }

    public void guardarNaveEspacial(NaveEspacial naveEspacial) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(naveEspacial);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void guardarMision(Mision mision) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(mision);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void guardarTripulante(Tripulante tripulante) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().saveOrUpdate(tripulante);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarNaveEspacial(NaveEspacial naveEspacial) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(naveEspacial);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarMision(Mision mision) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(mision);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void modificarTripulante(Tripulante tripulante) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().update(tripulante);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarNaveEspacial(NaveEspacial naveEspacial) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(naveEspacial);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarMision(Mision mision) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(mision);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    public void eliminarTripulante(Tripulante tripulante) {
        HibernateUtil.getCurrentSession().beginTransaction();
        HibernateUtil.getCurrentSession().delete(tripulante);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }
}
