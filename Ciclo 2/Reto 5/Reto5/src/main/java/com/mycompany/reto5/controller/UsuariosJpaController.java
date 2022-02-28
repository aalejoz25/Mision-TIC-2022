/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.controller;

import com.mycompany.reto5.controller.exceptions.IllegalOrphanException;
import com.mycompany.reto5.controller.exceptions.NonexistentEntityException;
import com.mycompany.reto5.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.reto5.model.Sesiones;
import com.mycompany.reto5.model.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alvar
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) throws PreexistingEntityException, Exception {
        if (usuarios.getSesionesList() == null) {
            usuarios.setSesionesList(new ArrayList<Sesiones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Sesiones> attachedSesionesList = new ArrayList<Sesiones>();
            for (Sesiones sesionesListSesionesToAttach : usuarios.getSesionesList()) {
                sesionesListSesionesToAttach = em.getReference(sesionesListSesionesToAttach.getClass(), sesionesListSesionesToAttach.getIdSesion());
                attachedSesionesList.add(sesionesListSesionesToAttach);
            }
            usuarios.setSesionesList(attachedSesionesList);
            em.persist(usuarios);
            for (Sesiones sesionesListSesiones : usuarios.getSesionesList()) {
                Usuarios oldUsuarioOfSesionesListSesiones = sesionesListSesiones.getUsuario();
                sesionesListSesiones.setUsuario(usuarios);
                sesionesListSesiones = em.merge(sesionesListSesiones);
                if (oldUsuarioOfSesionesListSesiones != null) {
                    oldUsuarioOfSesionesListSesiones.getSesionesList().remove(sesionesListSesiones);
                    oldUsuarioOfSesionesListSesiones = em.merge(oldUsuarioOfSesionesListSesiones);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarios(usuarios.getUsuario()) != null) {
                throw new PreexistingEntityException("Usuarios " + usuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getUsuario());
            List<Sesiones> sesionesListOld = persistentUsuarios.getSesionesList();
            List<Sesiones> sesionesListNew = usuarios.getSesionesList();
            List<String> illegalOrphanMessages = null;
            for (Sesiones sesionesListOldSesiones : sesionesListOld) {
                if (!sesionesListNew.contains(sesionesListOldSesiones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Sesiones " + sesionesListOldSesiones + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Sesiones> attachedSesionesListNew = new ArrayList<Sesiones>();
            for (Sesiones sesionesListNewSesionesToAttach : sesionesListNew) {
                sesionesListNewSesionesToAttach = em.getReference(sesionesListNewSesionesToAttach.getClass(), sesionesListNewSesionesToAttach.getIdSesion());
                attachedSesionesListNew.add(sesionesListNewSesionesToAttach);
            }
            sesionesListNew = attachedSesionesListNew;
            usuarios.setSesionesList(sesionesListNew);
            usuarios = em.merge(usuarios);
            for (Sesiones sesionesListNewSesiones : sesionesListNew) {
                if (!sesionesListOld.contains(sesionesListNewSesiones)) {
                    Usuarios oldUsuarioOfSesionesListNewSesiones = sesionesListNewSesiones.getUsuario();
                    sesionesListNewSesiones.setUsuario(usuarios);
                    sesionesListNewSesiones = em.merge(sesionesListNewSesiones);
                    if (oldUsuarioOfSesionesListNewSesiones != null && !oldUsuarioOfSesionesListNewSesiones.equals(usuarios)) {
                        oldUsuarioOfSesionesListNewSesiones.getSesionesList().remove(sesionesListNewSesiones);
                        oldUsuarioOfSesionesListNewSesiones = em.merge(oldUsuarioOfSesionesListNewSesiones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuarios.getUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Sesiones> sesionesListOrphanCheck = usuarios.getSesionesList();
            for (Sesiones sesionesListOrphanCheckSesiones : sesionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Sesiones " + sesionesListOrphanCheckSesiones + " in its sesionesList field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
