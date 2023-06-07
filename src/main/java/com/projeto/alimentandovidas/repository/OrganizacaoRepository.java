package com.projeto.alimentandovidas.repository;

import com.projeto.alimentandovidas.entities.AcaoSocial;
import com.projeto.alimentandovidas.entities.Organizacao;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class OrganizacaoRepository  {

    private EntityManager entityManager;

    public OrganizacaoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Iterable<Organizacao> getAllOrganizacao() {
        String jpql = "SELECT o FROM AV_ORGANIZACAO o";
        var query = entityManager.createQuery(jpql, Organizacao.class)
                .setHint("jakarta.persistence.query.timeout", 60000);
        var organizacoes = query.getResultList();
        return organizacoes;
    }

    public Organizacao getOrganizacaoById(long id) {
        Organizacao organizacao = entityManager.find(Organizacao.class, id);
        if (organizacao == null) {
            return null;
        }
        return organizacao;
    }

    public Optional<AcaoSocial> getOrganizacaoByState(String estado)
    {
        var jpql = "SELECT a FROM AV_ACAO_SOCIAL a WHERE estado =:estado";
        var query = entityManager.createQuery(jpql, AcaoSocial.class);
        query.setParameter("estado", estado);
        var acaoSocial = query.getSingleResult();
        return Optional.ofNullable(acaoSocial);
    }


    public void insertOrganizacao(Organizacao organizacao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(organizacao);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void updateOrganizacao(Organizacao organizacao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(organizacao);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteOrganizacao(Organizacao organizacao) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(organizacao);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteOrganizacaoById(int id) {
        entityManager.getTransaction().begin();
        try {
            Organizacao organizacao = entityManager.find(Organizacao.class, id);
            if (organizacao != null) {
                entityManager.remove(organizacao);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}