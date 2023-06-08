package com.projeto.alimentandovidas.repository;

import com.projeto.alimentandovidas.entities.AcaoSocial;
import com.projeto.alimentandovidas.entities.Organizacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OrganizacaoRepository  {

    private EntityManager entityManager;

    public OrganizacaoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Organizacao> getAllOrganizacao() {
        String jpql = "SELECT o FROM AV_ORGANIZACAO o";
        TypedQuery<Organizacao> query = entityManager.createQuery(jpql, Organizacao.class)
                .setHint("javax.persistence.query.timeout", 60000);
        List<Organizacao> organizacoes = query.getResultList();
        return organizacoes;
    }

    public Organizacao getOrganizacaoById(long id) {
        String jpql = "SELECT o FROM AV_ORGANIZACAO o WHERE o.id = :id";
        TypedQuery<Organizacao> query = entityManager.createQuery(jpql, Organizacao.class);
        query.setParameter("id", id);
        List<Organizacao> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public Optional<AcaoSocial> getOrganizacaoByState(String estado)
    {
        String jpql = "SELECT a FROM AV_ORGANIZACAO a WHERE a.estado = :estado";
        TypedQuery<AcaoSocial> query = entityManager.createQuery(jpql, AcaoSocial.class);
        query.setParameter("estado", estado);
        try {
            AcaoSocial acaoSocial = query.getSingleResult();
            return Optional.ofNullable(acaoSocial);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    public void insertOrganizacao(Organizacao organizacao) {
        try {
            String jpql = "INSERT INTO Organizacao (id, acaoSocialList, status, cnpj, nomeFantasia, site, estado, cidade, telefone, descricao, chavePix, dataCadastro) " +
                    "VALUES (:id, :acaoSocialList, :status, :cnpj, :nomeFantasia, :site, :estado, :cidade, :telefone, :descricao, :chavePix, :dataCadastro)";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("id", organizacao.getId());
            query.setParameter("acaoSocialList", organizacao.getAcaoSocialList());
            query.setParameter("status", organizacao.getStatus());
            query.setParameter("cnpj", organizacao.getCnpj());
            query.setParameter("nomeFantasia", organizacao.getNomeFantasia());
            query.setParameter("site", organizacao.getSite());
            query.setParameter("estado", organizacao.getEstado());
            query.setParameter("cidade", organizacao.getCidade());
            query.setParameter("telefone", organizacao.getTelefone());
            query.setParameter("descricao", organizacao.getDescricao());
            query.setParameter("chavePix", organizacao.getChavePix());
            query.setParameter("dataCadastro", LocalDateTime.now());

            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void updateOrganizacaoById(Long id, Organizacao organizacao) {
        try {
            String jpql = "UPDATE AV_ORGANIZACAO SET nome = :nome, endereco = :endereco, status = :status, cnpj = :cnpj, nomeFantasia = :nomeFantasia, site = :site, estado = :estado, cidade = :cidade, telefone = :telefone, descricao = :descricao, chavePix = :chavePix, dataCadastro = :dataCadastro WHERE id = :id";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("status", organizacao.getStatus());
            query.setParameter("cnpj", organizacao.getCnpj());
            query.setParameter("nomeFantasia", organizacao.getNomeFantasia());
            query.setParameter("site", organizacao.getSite());
            query.setParameter("estado", organizacao.getEstado());
            query.setParameter("cidade", organizacao.getCidade());
            query.setParameter("telefone", organizacao.getTelefone());
            query.setParameter("descricao", organizacao.getDescricao());
            query.setParameter("chavePix", organizacao.getChavePix());
            query.setParameter("dataCadastro", organizacao.getDataCadastro());
            query.setParameter("id", id);

            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }


    public void deleteOrganizacaoById(long id) {

        String jpql = "DELETE FROM AV_ORGANIZACAO u WHERE u.id = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("id", id);
        entityManager.getTransaction().begin();
        try {
            int rowsAffected = query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}