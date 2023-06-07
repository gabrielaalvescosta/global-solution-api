package com.projeto.alimentandovidas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlimentandoVidasApplication {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.
				createEntityManagerFactory("ALIMENTANDO-VIDAS");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.close();
		entityManagerFactory.close();
	}

}