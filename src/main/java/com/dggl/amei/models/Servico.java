package com.dggl.amei.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "SERVICO")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 400)
    @Column(name = "DESCRICAO_SERVICO")
    private String descricaoServico;

    @NotBlank
    @Column(name = "VALOR_SERVICO")
    private BigDecimal valorServico;

    @Column(name = "CODIGO_CNAE")
    private String codigoCNAE;

    @ManyToOne
    @JoinColumn(name = "ITENS_ORDEM_ SERVICO", referencedColumnName = "id")
    private ItensOrdemServico itensServico;

    @ManyToOne
    @JoinColumn(name = "ITENS_ORCAMENTO", referencedColumnName = "id")
    private ItensOrcamento itensOrcamento;



}
