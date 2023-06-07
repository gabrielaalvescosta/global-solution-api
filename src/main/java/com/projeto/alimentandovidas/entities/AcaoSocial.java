package com.projeto.alimentandovidas.entities;

import com.projeto.alimentandovidas.controller.AcaoSocialController;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Table(name = "acao_social")
public class AcaoSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id", referencedColumnName = "id")
    @Fetch(FetchMode.JOIN)
    private Organizacao organizacao;

    @Column(name = "descricao_completa")
    private String descricaoCompleta;

    @Column(name = "local")
    private String local;

    @Column(name = "horario_funcionamento")
    private String horarioFuncionamento;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "publicoPermitido")
    private String publicoPermitido;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

}
