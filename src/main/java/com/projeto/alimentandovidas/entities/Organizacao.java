package com.projeto.alimentandovidas.entities;

import com.projeto.alimentandovidas.controller.OrganizacaoController;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "organizacao")
public class Organizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "organizacao")
    @Fetch(FetchMode.JOIN)
    @Column(name = "acao_social")
    private List<AcaoSocial> acaoSocialList;

    @Column(name = "status")
    private String status;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "site")
    private String site;

    @Column(name = "chave_pix")
    private String chavePix;


    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

}