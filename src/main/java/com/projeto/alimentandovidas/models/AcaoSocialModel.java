package com.projeto.alimentandovidas.models;
import com.projeto.alimentandovidas.entities.Organizacao;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class AcaoSocialModel {

    public Long id;
    private Organizacao organizacao;
    private String descricaoCompleta;
    private String local;
    private String horarioFuncionamento;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String publicoPermitido;
    private LocalDateTime dataCadastro;

}
