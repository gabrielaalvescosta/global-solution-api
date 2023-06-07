package com.projeto.alimentandovidas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlimentandoVidasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlimentandoVidasApplication.class, args);
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TESTE");
			EntityManager entityManager = entityManagerFactory.createEntityManager();

			entityManager.close();
			entityManagerFactory.close();
		} catch (Exception e) {
			throw e;
		}
	}

}