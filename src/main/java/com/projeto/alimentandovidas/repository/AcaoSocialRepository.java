package com.projeto.alimentandovidas.repository;

import com.projeto.alimentandovidas.entities.AcaoSocial;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AcaoSocialRepository  {
    private EntityManager entityManager;

    public AcaoSocialRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public List<AcaoSocial> GetAcaoSocial()
    {
        var jpql = "SELECT a FROM AV_ACAO_SOCIAL a";
        var query = entityManager.createQuery(jpql, AcaoSocial.class);
        return query.getResultList();
    }

    public Optional<AcaoSocial> GetAcaoSocialById(long id)
    {
        var jpql = "SELECT a FROM AV_ACAO_SOCIAL a WHERE id=:id";
        var query = entityManager.createQuery(jpql, AcaoSocial.class);
        query.setParameter("id",id);
        var acaoSocial = query.getSingleResult();
        return Optional.ofNullable(acaoSocial);
    }

    public void InsertAcaoSocial(AcaoSocial acaoSocial) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(acaoSocial);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }


    public void UpdateAcaoSocial(AcaoSocial acaoSocial)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(acaoSocial);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteAcaoSocialById(int id) {
        AcaoSocial acaoSocial = entityManager.find(AcaoSocial.class, id);
        entityManager.getTransaction().begin();
        try {
            if (acaoSocial != null) {
                entityManager.remove(acaoSocial);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}