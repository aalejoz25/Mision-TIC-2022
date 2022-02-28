/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.controller;

import com.mycompany.reto5.controller.exceptions.IllegalOrphanException;
import com.mycompany.reto5.controller.exceptions.NonexistentEntityException;
import com.mycompany.reto5.model.Base;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.reto5.model.Operaciones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alvar
 */
public class BaseJpaController implements Serializable {

    public BaseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Base base) {
        if (base.getOperacionesList() == null) {
            base.setOperacionesList(new ArrayList<Operaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Operaciones> attachedOperacionesList = new ArrayList<Operaciones>();
            for (Operaciones operacionesListOperacionesToAttach : base.getOperacionesList()) {
                operacionesListOperacionesToAttach = em.getReference(operacionesListOperacionesToAttach.getClass(), operacionesListOperacionesToAttach.getIdOperacion());
                attachedOperacionesList.add(operacionesListOperacionesToAttach);
            }
            base.setOperacionesList(attachedOperacionesList);
            em.persist(base);
            for (Operaciones operacionesListOperaciones : base.getOperacionesList()) {
                Base oldBaseOfOperacionesListOperaciones = operacionesListOperaciones.getBase();
                operacionesListOperaciones.setBase(base);
                operacionesListOperaciones = em.merge(operacionesListOperaciones);
                if (oldBaseOfOperacionesListOperaciones != null) {
                    oldBaseOfOperacionesListOperaciones.getOperacionesList().remove(operacionesListOperaciones);
                    oldBaseOfOperacionesListOperaciones = em.merge(oldBaseOfOperacionesListOperaciones);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Base base) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Base persistentBase = em.find(Base.class, base.getIdBase());
            List<Operaciones> operacionesListOld = persistentBase.getOperacionesList();
            List<Operaciones> operacionesListNew = base.getOperacionesList();
            List<String> illegalOrphanMessages = null;
            for (Operaciones operacionesListOldOperaciones : operacionesListOld) {
                if (!operacionesListNew.contains(operacionesListOldOperaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operaciones " + operacionesListOldOperaciones + " since its base field is not nullable.");
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
            base.setOperacionesList(operacionesListNew);
            base = em.merge(base);
            for (Operaciones operacionesListNewOperaciones : operacionesListNew) {
                if (!operacionesListOld.contains(operacionesListNewOperaciones)) {
                    Base oldBaseOfOperacionesListNewOperaciones = operacionesListNewOperaciones.getBase();
                    operacionesListNewOperaciones.setBase(base);
                    operacionesListNewOperaciones = em.merge(operacionesListNewOperaciones);
                    if (oldBaseOfOperacionesListNewOperaciones != null && !oldBaseOfOperacionesListNewOperaciones.equals(base)) {
                        oldBaseOfOperacionesListNewOperaciones.getOperacionesList().remove(operacionesListNewOperaciones);
                        oldBaseOfOperacionesListNewOperaciones = em.merge(oldBaseOfOperacionesListNewOperaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = base.getIdBase();
                if (findBase(id) == null) {
                    throw new NonexistentEntityException("The base with id " + id + " no longer exists.");
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
            Base base;
            try {
                base = em.getReference(Base.class, id);
                base.getIdBase();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The base with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Operaciones> operacionesListOrphanCheck = base.getOperacionesList();
            for (Operaciones operacionesListOrphanCheckOperaciones : operacionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Base (" + base + ") cannot be destroyed since the Operaciones " + operacionesListOrphanCheckOperaciones + " in its operacionesList field has a non-nullable base field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(base);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Base> findBaseEntities() {
        return findBaseEntities(true, -1, -1);
    }

    public List<Base> findBaseEntities(int maxResults, int firstResult) {
        return findBaseEntities(false, maxResults, firstResult);
    }

    private List<Base> findBaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Base.class));
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

    public Base findBase(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Base.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Base> rt = cq.from(Base.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
