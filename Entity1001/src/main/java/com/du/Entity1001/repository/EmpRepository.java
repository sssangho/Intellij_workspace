package com.du.Entity1001.repository;

import com.du.Entity1001.entity.Emp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmpRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Emp emp) {
        em.persist(emp);
    }

    public Emp findById(Long id){
        return em.find(Emp.class, id);
    }

    public List<Emp> findAll(){
        return em.createQuery("select e from Emp e", Emp.class).getResultList();
    }

    public void update(Emp emp){
        em.merge(emp);
    }

    public void delete(Long id){
        Emp emp = em.find(Emp.class, id);
        if (emp != null){
            em.remove(emp);
        }
    }
}
