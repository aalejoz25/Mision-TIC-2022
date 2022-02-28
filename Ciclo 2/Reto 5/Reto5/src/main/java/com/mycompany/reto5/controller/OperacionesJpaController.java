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
import com.mycompany.reto5.model.Base;
import com.mycompany.reto5.model.Operaciones;
import com.mycompany.reto5.model.Operadores;
import com.mycompany.reto5.model.Sesiones;
import com.mycompany.reto5.model.Operandos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alvar
 */
public class OperacionesJpaController implements Serializable {

    public OperacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Operaciones operaciones) {
        if (operaciones.getOperandosList() == null) {
            operaciones.setOperandosList(new ArrayList<Operandos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Base base = operaciones.getBase();
            if (base != null) {
                base = em.getReference(base.getClass(), base.getIdBase());
                operaciones.setBase(base);
            }
            Operadores operadores = operaciones.getOperadores();
            if (operadores != null) {
                operadores = em.getReference(operadores.getClass(), operadores.getIdOperador());
                operaciones.setOperadores(operadores);
            }
            Sesiones sesion = operaciones.getSesion();
            if (sesion != null) {
                sesion = em.getReference(sesion.getClass(), sesion.getIdSesion());
                operaciones.setSesion(sesion);
            }
            List<Operandos> attachedOperandosList = new ArrayList<Operandos>();
            for (Operandos operandosListOperandosToAttach : operaciones.getOperandosList()) {
                operandosListOperandosToAttach = em.getReference(operandosListOperandosToAttach.getClass(), operandosListOperandosToAttach.getIdOperando());
                attachedOperandosList.add(operandosListOperandosToAttach);
            }
            operaciones.setOperandosList(attachedOperandosList);
            em.persist(operaciones);
            if (base != null) {
                base.getOperacionesList().add(operaciones);
                base = em.merge(base);
            }
            if (operadores != null) {
                operadores.getOperacionesList().add(operaciones);
                operadores = em.merge(operadores);
            }
            if (sesion != null) {
                sesion.getOperacionesList().add(operaciones);
                sesion = em.merge(sesion);
            }
            for (Operandos operandosListOperandos : operaciones.getOperandosList()) {
                Operaciones oldIdOperacionOfOperandosListOperandos = operandosListOperandos.getIdOperacion();
                operandosListOperandos.setIdOperacion(operaciones);
                operandosListOperandos = em.merge(operandosListOperandos);
                if (oldIdOperacionOfOperandosListOperandos != null) {
                    oldIdOperacionOfOperandosListOperandos.getOperandosList().remove(operandosListOperandos);
                    oldIdOperacionOfOperandosListOperandos = em.merge(oldIdOperacionOfOperandosListOperandos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Operaciones operaciones) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Operaciones persistentOperaciones = em.find(Operaciones.class, operaciones.getIdOperacion());
            Base baseOld = persistentOperaciones.getBase();
            Base baseNew = operaciones.getBase();
            Operadores operadoresOld = persistentOperaciones.getOperadores();
            Operadores operadoresNew = operaciones.getOperadores();
            Sesiones sesionOld = persistentOperaciones.getSesion();
            Sesiones sesionNew = operaciones.getSesion();
            List<Operandos> operandosListOld = persistentOperaciones.getOperandosList();
            List<Operandos> operandosListNew = operaciones.getOperandosList();
            List<String> illegalOrphanMessages = null;
            for (Operandos operandosListOldOperandos : operandosListOld) {
                if (!operandosListNew.contains(operandosListOldOperandos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Operandos " + operandosListOldOperandos + " since its idOperacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (baseNew != null) {
                baseNew = em.getReference(baseNew.getClass(), baseNew.getIdBase());
                operaciones.setBase(baseNew);
            }
            if (operadoresNew != null) {
                operadoresNew = em.getReference(operadoresNew.getClass(), operadoresNew.getIdOperador());
                operaciones.setOperadores(operadoresNew);
            }
            if (sesionNew != null) {
                sesionNew = em.getReference(sesionNew.getClass(), sesionNew.getIdSesion());
                operaciones.setSesion(sesionNew);
            }
            List<Operandos> attachedOperandosListNew = new ArrayList<Operandos>();
            for (Operandos operandosListNewOperandosToAttach : operandosListNew) {
                operandosListNewOperandosToAttach = em.getReference(operandosListNewOperandosToAttach.getClass(), operandosListNewOperandosToAttach.getIdOperando());
                attachedOperandosListNew.add(operandosListNewOperandosToAttach);
            }
            operandosListNew = attachedOperandosListNew;
            operaciones.setOperandosList(operandosListNew);
            operaciones = em.merge(operaciones);
            if (baseOld != null && !baseOld.equals(baseNew)) {
                baseOld.getOperacionesList().remove(operaciones);
                baseOld = em.merge(baseOld);
            }
            if (baseNew != null && !baseNew.equals(baseOld)) {
                baseNew.getOperacionesList().add(operaciones);
                baseNew = em.merge(baseNew);
            }
            if (operadoresOld != null && !operadoresOld.equals(operadoresNew)) {
                operadoresOld.getOperacionesList().remove(operaciones);
                operadoresOld = em.merge(operadoresOld);
            }
            if (operadoresNew != null && !operadoresNew.equals(operadoresOld)) {
                operadoresNew.getOperacionesList().add(operaciones);
                operadoresNew = em.merge(operadoresNew);
            }
            if (sesionOld != null && !sesionOld.equals(sesionNew)) {
                sesionOld.getOperacionesList().remove(operaciones);
                sesionOld = em.merge(sesionOld);
            }
            if (sesionNew != null && !sesionNew.equals(sesionOld)) {
                sesionNew.getOperacionesList().add(operaciones);
                sesionNew = em.merge(sesionNew);
            }
            for (Operandos operandosListNewOperandos : operandosListNew) {
                if (!operandosListOld.contains(operandosListNewOperandos)) {
                    Operaciones oldIdOperacionOfOperandosListNewOperandos = operandosListNewOperandos.getIdOperacion();
                    operandosListNewOperandos.setIdOperacion(operaciones);
                    operandosListNewOperandos = em.merge(operandosListNewOperandos);
                    if (oldIdOperacionOfOperandosListNewOperandos != null && !oldIdOperacionOfOperandosListNewOperandos.equals(operaciones)) {
                        oldIdOperacionOfOperandosListNewOperandos.getOperandosList().remove(operandosListNewOperandos);
                        oldIdOperacionOfOperandosListNewOperandos = em.merge(oldIdOperacionOfOperandosListNewOperandos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = operaciones.getIdOperacion();
                if (findOperaciones(id) == null) {
                    throw new NonexistentEntityException("The operaciones with id " + id + " no longer exists.");
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
            Operaciones operaciones;
            try {
                operaciones = em.getReference(Operaciones.class, id);
                operaciones.getIdOperacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The operaciones with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Operandos> operandosListOrphanCheck = operaciones.getOperandosList();
            for (Operandos operandosListOrphanCheckOperandos : operandosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Operaciones (" + operaciones + ") cannot be destroyed since the Operandos " + operandosListOrphanCheckOperandos + " in its operandosList field has a non-nullable idOperacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Base base = operaciones.getBase();
            if (base != null) {
                base.getOperacionesList().remove(operaciones);
                base = em.merge(base);
            }
            Operadores operadores = operaciones.getOperadores();
            if (operadores != null) {
                operadores.getOperacionesList().remove(operaciones);
                operadores = em.merge(operadores);
            }
            Sesiones sesion = operaciones.getSesion();
            if (sesion != null) {
                sesion.getOperacionesList().remove(operaciones);
                sesion = em.merge(sesion);
            }
            em.remove(operaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Operaciones> findOperacionesEntities() {
        return findOperacionesEntities(true, -1, -1);
    }

    public List<Operaciones> findOperacionesEntities(int maxResults, int firstResult) {
        return findOperacionesEntities(false, maxResults, firstResult);
    }

    private List<Operaciones> findOperacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Operaciones.class));
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

    public Operaciones findOperaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Operaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getOperacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Operaciones> rt = cq.from(Operaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
