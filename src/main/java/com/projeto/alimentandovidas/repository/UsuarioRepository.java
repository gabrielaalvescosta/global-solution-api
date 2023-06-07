package com.projeto.alimentandovidas.repository;

import com.projeto.alimentandovidas.entities.AcaoSocial;
import com.projeto.alimentandovidas.entities.Usuario;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class UsuarioRepository {

    private EntityManager entityManager;

    public UsuarioRepository(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public List<Usuario> GetUsuarios()
    {
        var jpql = "SELECT u FROM AV_USUARIO u";
        var query = entityManager.createQuery(jpql, Usuario.class);
        return query.getResultList();
    }

    public Optional<Usuario> GetUsuarioById(int id)
    {
        var jpql = "SELECT u FROM AV_USUARIO u WHERE id=:id";
        var query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("id",id);
        var usuario = query.getSingleResult();
        return Optional.ofNullable(usuario);
    }

    public void InsertUsuario(Usuario usuario) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }


    public void UpdateUsuario(Usuario usuario)
    {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(usuario);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public void deleteUsuarioById(int id) {
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