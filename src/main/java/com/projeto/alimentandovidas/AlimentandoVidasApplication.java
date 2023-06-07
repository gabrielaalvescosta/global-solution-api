package com.projeto.alimentandovidas;

import com.projeto.alimentandovidas.repository.AcaoSocialRepository;
import com.projeto.alimentandovidas.repository.OrganizacaoRepository;
import com.projeto.alimentandovidas.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class AlimentandoVidasApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlimentandoVidasApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner demo(AcaoSocialRepository acaoSocialRepository,
								  OrganizacaoRepository organizacaoRepository,
								  UsuarioRepository usuarioRepository) {
		return (args) -> {
			//acaoSocialRepository.save(new AcaoSocial(null, organizacaoRepository.getOrganizacaoById(or), null, null, null, null, null));


		// pedidoRepository.save(new Pedido(null, LocalDateTime.now(), pizzaRepository.findById(1L).get(),
		//			clienteRepository.findAll().get(0), null));

		};
	}

}
