package com.example.hotelSpring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class DAO<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    Class<T> type;

    public T findById(int id) {
        return entityManager.find(type, id);
    }

    public T save(T item)  {
        entityManager.persist(item);
        return item;
    }

    public boolean delete(T item) {
        entityManager.remove(item);
        return true;
    }

    public T update(T item) {
        entityManager.merge(item);
        return item;
    }

}
