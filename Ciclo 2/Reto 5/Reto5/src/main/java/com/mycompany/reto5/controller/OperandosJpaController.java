/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.controller;

import com.mycompany.reto5.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.reto5.model.Operaciones;
import com.mycompany.reto5.model.Operandos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alvar
 */
public class OperandosJpaController implements Serializable {

    public OperandosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Operandos operandos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operaciones idOperacion = operandos.getIdOperacion();
            if (idOperacion != null) {
                idOperacion = em.getReference(idOperacion.getClass(), idOperacion.getIdOperacion());
                operandos.setIdOperacion(idOperacion);
            }
            em.persist(operandos);
            if (idOperacion != null) {
                idOperacion.getOperandosList().add(operandos);
                idOperacion = em.merge(idOperacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Operandos operandos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operandos persistentOperandos = em.find(Operandos.class, operandos.getIdOperando());
            Operaciones idOperacionOld = persistentOperandos.getIdOperacion();
            Operaciones idOperacionNew = operandos.getIdOperacion();
            if (idOperacionNew != null) {
                idOperacionNew = em.getReference(idOperacionNew.getClass(), idOperacionNew.getIdOperacion());
                operandos.setIdOperacion(idOperacionNew);
            }
            operandos = em.merge(operandos);
            if (idOperacionOld != null && !idOperacionOld.equals(idOperacionNew)) {
                idOperacionOld.getOperandosList().remove(operandos);
                idOperacionOld = em.merge(idOperacionOld);
            }
            if (idOperacionNew != null && !idOperacionNew.equals(idOperacionOld)) {
                idOperacionNew.getOperandosList().add(operandos);
                idOperacionNew = em.merge(idOperacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = operandos.getIdOperando();
                if (findOperandos(id) == null) {
                    throw new NonexistentEntityException("The operandos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operandos operandos;
            try {
                operandos = em.getReference(Operandos.class, id);
                operandos.getIdOperando();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The operandos with id " + id + " no longer exists.", enfe);
            }
            Operaciones idOperacion = operandos.getIdOperacion();
            if (idOperacion != null) {
                idOperacion.getOperandosList().remove(operandos);
                idOperacion = em.merge(idOperacion);
            }
            em.remove(operandos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Operandos> findOperandosEntities() {
        return findOperandosEntities(true, -1, -1);
    }

    public List<Operandos> findOperandosEntities(int maxResults, int firstResult) {
        return findOperandosEntities(false, maxResults, firstResult);
    }

    private List<Operandos> findOperandosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Operandos.class));
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

    public Operandos findOperandos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Operandos.class, id);
        } finally {
            em.close();
        }
    }

    public int getOperandosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Operandos> rt = cq.from(Operandos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
