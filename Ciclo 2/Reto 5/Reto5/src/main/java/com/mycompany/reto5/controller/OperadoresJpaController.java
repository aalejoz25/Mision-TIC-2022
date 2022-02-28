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
import com.mycompany.reto5.model.Operaciones;
import com.mycompany.reto5.model.Operadores;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alvar
 */
public class OperadoresJpaController implements Serializable {

    public OperadoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Operadores operadores) {
        if (operadores.getOperacionesList() == null) {
            operadores.setOperacionesList(new ArrayList<Operaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Operaciones> attachedOperacionesList = new ArrayList<Operaciones>();
            for (Operaciones operacionesListOperacionesToAttach : operadores.getOperacionesList()) {
                operacionesListOperacionesToAttach = em.getReference(operacionesListOperacionesToAttach.getClass(), operacionesListOperacionesToAttach.getIdOperacion());
                attachedOperacionesList.add(operacionesListOperacionesToAttach);
            }
            operadores.setOperacionesList(attachedOperacionesList);
            em.persist(operadores);
            for (Operaciones operacionesListOperaciones : operadores.getOperacionesList()) {
                Operadores oldOperadoresOfOperacionesListOperaciones = operacionesListOperaciones.getOperadores();
                operacionesListOperaciones.setOperadores(operadores);
                operacionesListOperaciones = em.merge(operacionesListOperaciones);
                if (oldOperadoresOfOperacionesListOperaciones != null) {
                    oldOperadoresOfOperacionesListOperaciones.getOperacionesList().remove(operacionesListOperaciones);
                    oldOperadoresOfOperacionesListOperaciones = em.merge(oldOperadoresOfOperacionesListOperaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Operadores operadores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operadores persistentOperadores = em.find(Operadores.class, operadores.getIdOperador());
            List<Operaciones> operacionesListOld = persistentOperadores.getOperacionesList();
            List<Operaciones> operacionesListNew = operadores.getOperacionesList();
            List<String> illegalOrphanMessages = null;
            for (Operaciones operacionesListOldOperaciones : operacionesListOld) {
                if (!operacionesListNew.contains(operacionesListOldOperaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operaciones " + operacionesListOldOperaciones + " since its operadores field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Operaciones> attachedOperacionesListNew = new ArrayList<Operaciones>();
            for (Operaciones operacionesListNewOperacionesToAttach : operacionesListNew) {
                operacionesListNewOperacionesToAttach = em.getReference(operacionesListNewOperacionesToAttach.getClass(), operacionesListNewOperacionesToAttach.getIdOperacion());
                attachedOperacionesListNew.add(operacionesListNewOperacionesToAttach);
            }
            operacionesListNew = attachedOperacionesListNew;
            operadores.setOperacionesList(operacionesListNew);
            operadores = em.merge(operadores);
            for (Operaciones operacionesListNewOperaciones : operacionesListNew) {
                if (!operacionesListOld.contains(operacionesListNewOperaciones)) {
                    Operadores oldOperadoresOfOperacionesListNewOperaciones = operacionesListNewOperaciones.getOperadores();
                    operacionesListNewOperaciones.setOperadores(operadores);
                    operacionesListNewOperaciones = em.merge(operacionesListNewOperaciones);
                    if (oldOperadoresOfOperacionesListNewOperaciones != null && !oldOperadoresOfOperacionesListNewOperaciones.equals(operadores)) {
                        oldOperadoresOfOperacionesListNewOperaciones.getOperacionesList().remove(operacionesListNewOperaciones);
                        oldOperadoresOfOperacionesListNewOperaciones = em.merge(oldOperadoresOfOperacionesListNewOperaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = operadores.getIdOperador();
                if (findOperadores(id) == null) {
                    throw new NonexistentEntityException("The operadores with id " + id + " no longer exists.");
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
            Operadores operadores;
            try {
                operadores = em.getReference(Operadores.class, id);
                operadores.getIdOperador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The operadores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Operaciones> operacionesListOrphanCheck = operadores.getOperacionesList();
            for (Operaciones operacionesListOrphanCheckOperaciones : operacionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Operadores (" + operadores + ") cannot be destroyed since the Operaciones " + operacionesListOrphanCheckOperaciones + " in its operacionesList field has a non-nullable operadores field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(operadores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Operadores> findOperadoresEntities() {
        return findOperadoresEntities(true, -1, -1);
    }

    public List<Operadores> findOperadoresEntities(int maxResults, int firstResult) {
        return findOperadoresEntities(false, maxResults, firstResult);
    }

    private List<Operadores> findOperadoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Operadores.class));
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

    public Operadores findOperadores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Operadores.class, id);
        } finally {
            em.close();
        }
    }

    public int getOperadoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Operadores> rt = cq.from(Operadores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
