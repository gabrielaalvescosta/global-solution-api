package com.projeto.alimentandovidas.models;

import com.projeto.alimentandovidas.entities.AcaoSocial;
import java.time.LocalDateTime;
import java.util.List;

public class OrganizacaoModel {

    public Long id;
    public List<AcaoSocial> acaoSocialList;
    public String status;
    public String cnpj;
    public String nomeFantasia;
    public String estado;
    public String cidade;
    public String telefone;
    public String descricao;
    public String chavePix;
    public LocalDateTime dataCadastro;

}
