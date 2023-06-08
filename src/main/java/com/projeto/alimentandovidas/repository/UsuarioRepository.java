package com.projeto.alimentandovidas.repository;

import com.projeto.alimentandovidas.entities.AcaoSocial;
import com.projeto.alimentandovidas.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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

    public Optional<Usuario> GetUsuarioById(long id)
    {
        var jpql = "SELECT u FROM AV_USUARIO u WHERE id=:id";
        var query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("id",id);
        var usuario = query.getSingleResult();
        return Optional.ofNullable(usuario);
    }

    public Optional<Usuario> GetUsuarioByEmail(String email)
    {
        var jpql = "SELECT u FROM AV_USUARIO u WHERE email=:email";
        var query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("email", email);
        var usuario = query.getSingleResult();
        return Optional.ofNullable(usuario);
    }

    public void InsertUsuario(Usuario usuario) {
        String jpql = "INSERT INTO AV_USUARIO (email, senha) VALUES (:email, :senha)";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("email", usuario.getEmail());
        query.setParameter("senha", usuario.getSenha());
        entityManager.getTransaction().begin();
        try {
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }


    public void UpdateUsuario(Usuario usuario)
    {
        String jpql = "UPDATE AV_USUARIO u SET u.email = :email, u.senha = :senha WHERE u.id = :id";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("email", usuario.getEmail());
        query.setParameter("senha", usuario.getSenha());
        query.setParameter("id", usuario.getId());
        entityManager.getTransaction().begin();
        try {
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void deleteUsuarioById(long id) {

        String jpql = "DELETE FROM AV_USUARIO u WHERE u.id = :id";
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