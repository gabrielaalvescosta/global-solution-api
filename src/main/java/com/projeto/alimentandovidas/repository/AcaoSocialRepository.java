package com.projeto.alimentandovidas.repository;

import com.projeto.alimentandovidas.entities.AcaoSocial;
import com.projeto.alimentandovidas.entities.Organizacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.time.LocalDateTime;
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

    public void insertAcaoSocialPorIdOrganizacao(AcaoSocial acaoSocial, Long organizacaoId) {
        String jpql = "INSERT INTO AV_ACAO_SOCIAL (organizacao, descricaoCompleta, local, horarioFuncionamento, dataInicio, dataFim, publicoPermitido, dataCadastro) " +
                "SELECT o, :descricaoCompleta, :local, :horarioFuncionamento, :dataInicio, :dataFim, :publicoPermitido, :dataCadastro " +
                "FROM AV_ORGANIZACAO o WHERE o.id = :organizacaoId";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("organizacaoId", organizacaoId);
        query.setParameter("descricaoCompleta", acaoSocial.getDescricaoCompleta());
        query.setParameter("local", acaoSocial.getLocal());
        query.setParameter("horarioFuncionamento", acaoSocial.getHorarioFuncionamento());
        query.setParameter("dataInicio", acaoSocial.getDataInicio());
        query.setParameter("dataFim", acaoSocial.getDataFim());
        query.setParameter("publicoPermitido", acaoSocial.getPublicoPermitido());
        query.setParameter("dataCadastro", LocalDateTime.now());

        entityManager.getTransaction().begin();
        try {
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void updateAcaoSocialPorIdOrganizacao(AcaoSocial acaoSocial, Long organizacaoId) {
        String jpql = "UPDATE AV_ACAO_SOCIAL a SET a.descricaoCompleta = :descricaoCompleta, a.local = :local, " +
                "a.horarioFuncionamento = :horarioFuncionamento, a.dataInicio = :dataInicio, a.dataFim = :dataFim, " +
                "a.publicoPermitido = :publicoPermitido, a.dataCadastro = :dataCadastro " +
                "WHERE a.organizacao.id = :organizacaoId";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("organizacaoId", organizacaoId);
        query.setParameter("descricaoCompleta", acaoSocial.getDescricaoCompleta());
        query.setParameter("local", acaoSocial.getLocal());
        query.setParameter("horarioFuncionamento", acaoSocial.getHorarioFuncionamento());
        query.setParameter("dataInicio", acaoSocial.getDataInicio());
        query.setParameter("dataFim", acaoSocial.getDataFim());
        query.setParameter("publicoPermitido", acaoSocial.getPublicoPermitido());
        query.setParameter("dataCadastro", LocalDateTime.now());

        entityManager.getTransaction().begin();
        try {
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteAcaoSocialPorIdOrganizacao(Long organizacaoId) {
        String jpql = "DELETE FROM AV_ACAO_SOCIAL a WHERE a.organizacao.id = :organizacaoId";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("organizacaoId", organizacaoId);

        entityManager.getTransaction().begin();
        try {
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}