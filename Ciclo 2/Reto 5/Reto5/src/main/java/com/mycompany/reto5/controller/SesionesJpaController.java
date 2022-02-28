/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.controller;

import com.mycompany.reto5.controller.exceptions.IllegalOrphanException;
import com.mycompany.reto5.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.reto5.model.Usuarios;
import com.mycompany.reto5.model.Operaciones;
import com.mycompany.reto5.model.Sesiones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alvar
 */
public class SesionesJpaController implements Serializable {

    public SesionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sesiones sesiones) {
        if (sesiones.getOperacionesList() == null) {
            sesiones.setOperacionesList(new ArrayList<Operaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuario = sesiones.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getUsuario());
                sesiones.setUsuario(usuario);
            }
            List<Operaciones> attachedOperacionesList = new ArrayList<Operaciones>();
            for (Operaciones operacionesListOperacionesToAttach : sesiones.getOperacionesList()) {
                operacionesListOperacionesToAttach = em.getReference(operacionesListOperacionesToAttach.getClass(), operacionesListOperacionesToAttach.getIdOperacion());
                attachedOperacionesList.add(operacionesListOperacionesToAttach);
            }
            sesiones.setOperacionesList(attachedOperacionesList);
            em.persist(sesiones);
            if (usuario != null) {
                usuario.getSesionesList().add(sesiones);
                usuario = em.merge(usuario);
            }
            for (Operaciones operacionesListOperaciones : sesiones.getOperacionesList()) {
                Sesiones oldSesionOfOperacionesListOperaciones = operacionesListOperaciones.getSesion();
                operacionesListOperaciones.setSesion(sesiones);
                operacionesListOperaciones = em.merge(operacionesListOperaciones);
                if (oldSesionOfOperacionesListOperaciones != null) {
                    oldSesionOfOperacionesListOperaciones.getOperacionesList().remove(operacionesListOperaciones);
                    oldSesionOfOperacionesListOperaciones = em.merge(oldSesionOfOperacionesListOperaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sesiones sesiones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sesiones persistentSesiones = em.find(Sesiones.class, sesiones.getIdSesion());
            Usuarios usuarioOld = persistentSesiones.getUsuario();
            Usuarios usuarioNew = sesiones.getUsuario();
            List<Operaciones> operacionesListOld = persistentSesiones.getOperacionesList();
            List<Operaciones> operacionesListNew = sesiones.getOperacionesList();
            List<String> illegalOrphanMessages = null;
            for (Operaciones operacionesListOldOperaciones : operacionesListOld) {
                if (!operacionesListNew.contains(operacionesListOldOperaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operaciones " + operacionesListOldOperaciones + " since its sesion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getUsuario());
                sesiones.setUsuario(usuarioNew);
            }
            List<Operaciones> attachedOperacionesListNew = new ArrayList<Operaciones>();
            for (Operaciones operacionesListNewOperacionesToAttach : operacionesListNew) {
                operacionesListNewOperacionesToAttach = em.getReference(operacionesListNewOperacionesToAttach.getClass(), operacionesListNewOperacionesToAttach.getIdOperacion());
                attachedOperacionesListNew.add(operacionesListNewOperacionesToAttach);
            }
            operacionesListNew = attachedOperacionesListNew;
            sesiones.setOperacionesList(operacionesListNew);
            sesiones = em.merge(sesiones);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getSesionesList().remove(sesiones);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getSesionesList().add(sesiones);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Operaciones operacionesListNewOperaciones : operacionesListNew) {
                if (!operacionesListOld.contains(operacionesListNewOperaciones)) {
                    Sesiones oldSesionOfOperacionesListNewOperaciones = operacionesListNewOperaciones.getSesion();
                    operacionesListNewOperaciones.setSesion(sesiones);
                    operacionesListNewOperaciones = em.merge(operacionesListNewOperaciones);
                    if (oldSesionOfOperacionesListNewOperaciones != null && !oldSesionOfOperacionesListNewOperaciones.equals(sesiones)) {
                        oldSesionOfOperacionesListNewOperaciones.getOperacionesList().remove(operacionesListNewOperaciones);
                        oldSesionOfOperacionesListNewOperaciones = em.merge(oldSesionOfOperacionesListNewOperaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sesiones.getIdSesion();
                if (findSesiones(id) == null) {
                    throw new NonexistentEntityException("The sesiones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sesiones sesiones;
            try {
                sesiones = em.getReference(Sesiones.class, id);
                sesiones.getIdSesion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sesiones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Operaciones> operacionesListOrphanCheck = sesiones.getOperacionesList();
            for (Operaciones operacionesListOrphanCheckOperaciones : operacionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sesiones (" + sesiones + ") cannot be destroyed since the Operaciones " + operacionesListOrphanCheckOperaciones + " in its operacionesList field has a non-nullable sesion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuarios usuario = sesiones.getUsuario();
            if (usuario != null) {
                usuario.getSesionesList().remove(sesiones);
                usuario = em.merge(usuario);
            }
            em.remove(sesiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sesiones> findSesionesEntities() {
        return findSesionesEntities(true, -1, -1);
    }

    public List<Sesiones> findSesionesEntities(int maxResults, int firstResult) {
        return findSesionesEntities(false, maxResults, firstResult);
    }

    private List<Sesiones> findSesionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sesiones.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Sesiones findSesiones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sesiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getSesionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sesiones> rt = cq.from(Sesiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
