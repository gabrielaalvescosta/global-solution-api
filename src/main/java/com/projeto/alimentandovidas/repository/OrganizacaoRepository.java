package com.projeto.alimentandovidas.repository;

import com.projeto.alimentandovidas.model.Organizacao;
import jakarta.persistence.EntityManager;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
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

    public Organizacao getOrganizacaoById(int id) {
        Organizacao organizacao = entityManager.find(Organizacao.class, id);
        if (organizacao == null) {
            return null;
        }
        return organizacao;
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

    public void deleteCustomer(Customer customer) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteCustomerById(int id) {
        entityManager.getTransaction().begin();
        try {
            Customer customer = entityManager.find(Customer.class, id);
            if (customer != null) {
                entityManager.remove(customer);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}