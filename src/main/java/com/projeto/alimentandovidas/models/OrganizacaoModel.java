package com.projeto.alimentandovidas.models;

import com.projeto.alimentandovidas.entities.AcaoSocial;
import java.time.LocalDateTime;
import java.util.List;

public class OrganizacaoModel {

    private Long id;
    private List<AcaoSocial> acaoSocialList;
    private String status;
    private String cnpj;
    private String nomeFantasia;
    private String estado;
    private String cidade;
    private String telefone;
    private String descricao;
    private String chavePix;
    private LocalDateTime dataCadastro;

}
