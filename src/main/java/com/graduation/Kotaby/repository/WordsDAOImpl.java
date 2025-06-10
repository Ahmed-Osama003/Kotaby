package com.graduation.Kotaby.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordsDAOImpl implements WordsDAO {
    @PersistenceContext(unitName = "secondary")
    private final EntityManager entityManager;

    public WordsDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<String> getWordsByPage(int page) {
        TypedQuery<String> query = entityManager.createQuery(
                "SELECT w.wordText FROM Words w WHERE w.pageNumber = :pageNumber ORDER BY w.soraId, w.ayaOrder, w.wordOrder",
                String.class
        );
        query.setParameter("pageNumber", page);
        return query.getResultList();
    }
}
