package com.projeto.alimentandovidas.controller;

import com.projeto.alimentandovidas.exception.RestNotFoundException;
import com.projeto.alimentandovidas.entities.Organizacao;
import com.projeto.alimentandovidas.repository.AcaoSocialRepository;
import com.projeto.alimentandovidas.repository.OrganizacaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;

@RestController
@Slf4j
@RequestMapping("/alimentandovidas/api/")
public class OrganizacaoController {
    @Autowired
    OrganizacaoRepository organizacaoRepository;

    @Autowired
    AcaoSocialRepository acaoSocialRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping("/organizacoes")
    @Operation(
            summary = "Lista de organizações",
            description = "Retorna uma lista paginada de todas organizações, ou apenas com mesmo estado"
    )
    public ResponseEntity<List<EntityModel<Organizacao>>> indexOrganizacoes() {
        List<Organizacao> organizacoes = organizacaoRepository.getAllOrganizacao();

        if (organizacoes.isEmpty()) {
            log.info("Nenhuma organização encontrada.");
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Organizacao>> entityModels = organizacoes.stream()
                .map(organizacao -> EntityModel.of(organizacao,
                        (Iterable<Link>) linkTo(methodOn(OrganizacaoController.class).show(organizacao.getId())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(entityModels);
    }

    @GetMapping("/organizacao/{id}")
    @Operation(
            summary = "Detalhes da organização",
            description = "Retorna os dados de uma organização com o Id especificado"
    )
    public EntityModel<Organizacao> show(@PathVariable Long id){
        log.info("buscando organizacao com id " + id);
        var organizacao = organizacaoRepository.getOrganizacaoById(id);
        return organizacao.toModel();
    }

    @PostMapping("/cadastrar")
    @Operation(
            summary = "Cadastro de organização",
            description = "Realiza o cadastro de uma nova organização"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "organização cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "erro na validação dos dados da requisição")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid Organizacao organizacao){
        log.info("realizando cadastro da organizacao: " + organizacao);
        organizacao.setStatus("ATIVO");
        organizacao.setDataCadastro(LocalDateTime.now());
        organizacaoRepository.insertOrganizacao(organizacao);
        return ResponseEntity
                .created(organizacao.toModel().getRequiredLink("self").toUri())
                .body(organizacao.toModel());
    }

    @PutMapping("/atualizar/{id}")
    @Operation(
            summary = "Atualização da organização",
            description = "Atualiza os dados de uma organização com o ID especificado"
    )
    public EntityModel<Organizacao> update(@PathVariable Long id, @RequestBody @Valid Organizacao organizacao){
        log.info("alterando organizacao com id " + id);
        Organizacao organizacaoExistente = organizacaoRepository.getOrganizacaoById(id);

        if (!organizacaoExistente.getCnpj().equals(organizacao.getCnpj())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O CNPJ não pode ser alterado");
        }

        organizacao.setStatus("ATIVO");
        organizacao.setDataCadastro(organizacaoExistente.getDataCadastro());
        organizacao.setId(id);
        organizacaoRepository.insertOrganizacao(organizacao);

        return organizacao.toModel();
    }
}

